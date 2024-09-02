package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.entity.Entity;

public class AutoSneak extends Basil.module.Module{

	public AutoSneak() {
		super("AutoSneak", Keyboard.KEY_M, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Increase", this, 1.1, 1, 2, false));
	}
	
	public void onUpdate() {
		if (isToggled()) {
			double sx = 0, sz = 0;
			sx = Math.max(0.5 * Math.abs(mc.thePlayer.motionX / 0.25), 0.1) * Client.sm.getSettingsByMod(this).get(0).getValDouble();
			sz = Math.max(0.5 * Math.abs(mc.thePlayer.motionZ / 0.25), 0.1) * Client.sm.getSettingsByMod(this).get(0).getValDouble();
			if (mc.thePlayer.onGround) {
				if (mc.theWorld.getCollidingBoundingBoxes((Entity) mc.thePlayer, 
							mc.thePlayer.getEntityBoundingBox().offset(0.0D, -0.5D, 0.0D).expand(-sx, 0.0D, -sz)).isEmpty()) {
					mc.gameSettings.keyBindSneak.pressed = true;
				}else {
					mc.gameSettings.keyBindSneak.pressed = false;
				}
			}
		}
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindSneak.pressed = false;
	}
}
