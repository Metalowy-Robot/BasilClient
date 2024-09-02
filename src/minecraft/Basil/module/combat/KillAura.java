package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.Rotation;
import Basil.utils.Timer;
import de.Hero.settings.Setting;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class KillAura extends Module{
	
	Timer t = new Timer();
	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("CPS", this, 10, 1, 50, true));
		Client.sm.rSetting(new Setting("Range", this, 3, 3, 6, false));
	}
	
	@Override
	public void onUpdate() {
		if (!this.isToggled() || !(mc.thePlayer.getHealth() > 0)) {
			return;
		}
		ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
		
		boolean f = false;
		EntityLivingBase best = (EntityLivingBase) mc.theWorld.loadedEntityList.get(0);
		
		if (t.hasTimeElapsed((long)(1000 / settings.get(0).getValDouble()), true)) {
			for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
				Object theObject = entities.next();
				if (theObject instanceof EntityLivingBase) {
					EntityLivingBase entity = (EntityLivingBase) theObject;
					if (entity instanceof EntityPlayerSP) {
						continue;
					}
					if (mc.thePlayer.getDistanceToEntity(entity) > settings.get(1).getValDouble() * 2) {
						continue;
					}
					
					if (!f || mc.thePlayer.getDistanceToEntity(entity) < mc.thePlayer.getDistanceToEntity(best)) {
						best = entity;
						f = true;
					}
					
				}
			}
			
			if (f && best.isEntityAlive()) {
				mc.thePlayer.sendQueue.addToSendQueue(
						new C03PacketPlayer.C05PacketPlayerLook(Rotation.getRotationTo(best)[0], Rotation.getRotationTo(best)[1] , mc.thePlayer.onGround)
						);
				mc.playerController.attackEntity(mc.thePlayer, best);
				mc.thePlayer.swingItem();
			}
		}
		super.onUpdate();
	}
}
