package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.Rotation;
import de.Hero.settings.Setting;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AimBot extends Module{
	
	EntityLivingBase best;
	public boolean inversed = false;

	public AimBot() {
		super("Aimbot", Keyboard.KEY_G, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Range", this, 8, 4, 25, false));
		Client.sm.rSetting(new Setting("Speed Min", this, 20, 10, 100, true));
		Client.sm.rSetting(new Setting("Speed Max", this, 40, 10, 100, true));
		Client.sm.rSetting(new Setting("Priority FOV", this, 45, 5, 180, true));
		Client.sm.rSetting(new Setting("LERP", this, 0.6, 0.05, 1.5, false));
		Client.sm.rSetting(new Setting("FOV", this, 45, 5, 180, true));
	}
	
	public void inverse() {
		inversed = !inversed;
	}
	
	@Override
	public void onFrame() {
		if (!this.isToggled() || !(mc.thePlayer.getHealth() > 0)) {
			return;
		}
		
		ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
		
		boolean f = false;
		best = null;
		
		for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
			Object theObject = entities.next();
			if (theObject instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) theObject;
				if (entity instanceof EntityPlayerSP) {
					continue;
				}
				if (!(entity instanceof EntityPlayer)) {
					continue;
				}
				if (mc.thePlayer.getDistanceToEntity(entity) > settings.get(0).getValDouble() * 2) {
					continue;
				}
				if (Math.abs(Rotation.getYawDistToEntity(entity)) > Math.min(settings.get(5).getValDouble(), settings.get(3).getValDouble())) {
					
					continue;
				}
				
				if (!f || mc.thePlayer.getDistanceToEntity(entity) < mc.thePlayer.getDistanceToEntity(best)) {
					best = entity;
					f = true;
				}
			}
		}
		
		if (best == null) {
			f = false;
			try {
				best = (EntityLivingBase) mc.theWorld.loadedEntityList.get(0);
			}catch(Exception e) {
				best = null;
			}
			
			for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
				Object theObject = entities.next();
				if (theObject instanceof EntityLivingBase) {
					EntityLivingBase entity = (EntityLivingBase) theObject;
					if (entity instanceof EntityPlayerSP) {
						continue;
					}
					if (!(entity instanceof EntityPlayer)) {
						continue;
					}
					if (mc.thePlayer.getDistanceToEntity(entity) > settings.get(0).getValDouble() * 2) {
						continue;
					}
					if (Math.abs(Rotation.getYawDistToEntity(entity)) > settings.get(5).getValDouble()) {
						continue;
					}
					
					if (!f || mc.thePlayer.getDistanceToEntity(entity) < mc.thePlayer.getDistanceToEntity(best)) {
						best = entity;
						f = true;
					}
				}
			}
		}
		
		if (f && best.isEntityAlive() && best != null) {
			float yaw = Rotation.getRotationTo(best)[0];
			float pitch = Rotation.getRotationTo(best)[1];
			float pYaw = mc.thePlayer.rotationYaw;
			
			yaw += Math.random() * 6 - 2;
			pitch += Math.random() * 6 - 2;
			
			if (inversed) {
			    yaw = (yaw > 0) ? -(180 - yaw) : 180 + yaw;
			}

			float yawDist = yaw - pYaw;

			if (yawDist > 180) {
			    yawDist -= 360;
			} else if (yawDist < -180) {
			    yawDist += 360;
			}

			float proximityFactor = Math.abs(yawDist) / 90.0f;
			proximityFactor = (float) Math.max(0.06f / (settings.get(4).getValDouble() * 2), proximityFactor);
			float s = (float) (settings.get(1).getValDouble() / mc.getDebugFPS() * 5 * (proximityFactor / settings.get(4).getValDouble()));

			if (Math.abs(s) < Math.abs(yawDist)) {
			    yawDist = Math.signum(yawDist) * s;
			}
			float newYaw = pYaw + yawDist;

			if (newYaw > 180) {
			    newYaw -= 360;
			} else if (newYaw < -180) {
			    newYaw += 360;
			}

			float pitchDist = Rotation.getPitchDistToEntity(best);
			if (Math.abs(s) < Math.abs(pitchDist)) {
			    pitchDist = Math.signum(pitchDist) * s;
			}
			float newPitch = mc.thePlayer.rotationPitch + pitchDist;

			newPitch = Math.max(-90, Math.min(90, newPitch));

			float d0 = (float) Math.sqrt(yawDist * yawDist + pitchDist * pitchDist);

			if (Math.abs(d0) > 20) {
			    float dy = newPitch - newYaw;
			    float dp = newYaw - newPitch;
			    float d1 = (float) Math.max(1, Math.sqrt(dy * dy + dp * dp));
			    dy /= d1;
			    dp /= d1;

			    float ry = (float) (Math.random() * 30 - 15);
			    float rp = (float) (Math.random() * 30 - 15);

			    newYaw += dy * ry * 4;
			    newPitch += dp * rp * 4;
			    
			    newYaw += Math.random() * 6 - 2;
			    newPitch += Math.random() * 6 - 2;

			    newPitch = Math.max(-90, Math.min(90, newPitch));
			}

			if (newYaw > 180) {
			    newYaw -= 360;
			} else if (newYaw < -180) {
			    newYaw += 360;
			}


			mc.thePlayer.rotationYaw = newYaw;
			mc.thePlayer.rotationPitch = newPitch;



		}
		super.onUpdate();
	}
}
