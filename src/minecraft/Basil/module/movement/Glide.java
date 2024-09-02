package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class Glide extends Basil.module.Module{

	public Glide() {
		super("Glide", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Fall Speed", this, 0.125, 0.05, 0.5, false));
		Client.sm.rSetting(new Setting("Air Control", this, 0.5, 0.0, 1.5, false));
	}
	
	public void onDisable() {
		if (!mc.thePlayer.capabilities.isCreativeMode) {
			mc.thePlayer.capabilities.allowFlying = false;
		}
		super.onDisable();
	}
	
	public void onUpdate() {
		double oldY = mc.thePlayer.motionY;
		float oldJ = mc.thePlayer.jumpMovementFactor;
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			if (mc.thePlayer.motionY < 0.0d && mc.thePlayer.isAirBorne && !mc.thePlayer.isInWater() && !mc.thePlayer.isOnLadder()) {
				if (!mc.thePlayer.isInLava()) {
					mc.thePlayer.motionY = -settings.get(0).getValDouble();
					mc.thePlayer.jumpMovementFactor *= settings.get(1).getValDouble();
				}
			}
		}else {
			mc.thePlayer.motionY = oldY;
			mc.thePlayer.jumpMovementFactor = oldJ;
		}
	}
	
}
