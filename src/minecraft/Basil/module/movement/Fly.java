package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class Fly extends Basil.module.Module{

	public Fly() {
		super("Fly", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Speed", this, 1, 0.5, 5.0, false));
	}
	
	public void onDisable() {
		if (!mc.thePlayer.capabilities.isCreativeMode) {
			mc.thePlayer.capabilities.allowFlying = false;
			mc.thePlayer.capabilities.isFlying = false;
		}
		super.onDisable();
	}
	
	public void onUpdate() {
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			mc.thePlayer.capabilities.isFlying = true;
			
			if (mc.gameSettings.keyBindJump.isPressed()) {
				mc.thePlayer.motionY += settings.get(0).getValDouble() / 10;
			}
			
			if (mc.gameSettings.keyBindSneak.isPressed()) {
				mc.thePlayer.motionY -= settings.get(0).getValDouble() / 10;
			}
			
			if (mc.gameSettings.keyBindForward.isPressed()) {
				mc.thePlayer.capabilities.setFlySpeed((float) (settings.get(0).getValDouble() / 10));
			}
			
			super.onDisable();
		}
	}
	
}
