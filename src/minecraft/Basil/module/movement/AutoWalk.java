package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class AutoWalk extends Basil.module.Module{

	public AutoWalk() {
		super("AutoWalk", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Always Active", this, false));
	}
	
	public void onEnable() {
		mc.gameSettings.keyBindForward.pressed = true;
	}
	
	public void onUpdate() {
		if (isToggled() && Client.sm.getSettingsByMod(this).get(0).getValBoolean() == false) {
			mc.gameSettings.keyBindForward.pressed = true;
		}
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindForward.pressed = false;
	}
}
