package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class FastStop extends Basil.module.Module{
	
	public FastStop() {
		super("FastStop", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Speed", this, 75, 1, 500, true));
	}
	
	public void onUpdate() {
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			if (mc.gameSettings.keyBindForward.pressed == false && mc.gameSettings.keyBindBack.pressed == false && mc.gameSettings.keyBindLeft.pressed == false && mc.gameSettings.keyBindRight.pressed == false) {
				mc.thePlayer.motionX /= 1 + (settings.get(0).getValDouble() / 500);
				mc.thePlayer.motionZ /= 1 + (settings.get(0).getValDouble() / 500);
			}
		}
	}
}
