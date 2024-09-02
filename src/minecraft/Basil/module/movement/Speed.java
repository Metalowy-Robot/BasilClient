package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class Speed extends Basil.module.Module{
	
	public Speed() {
		super("Speed", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Speed", this, 1.1, 1, 1.5, false));
		Client.sm.rSetting(new Setting("Only Ground", this, true));
	}
	
	public void onUpdate() {
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			if (!settings.get(1).getValBoolean() || mc.thePlayer.onGround) {
				mc.thePlayer.motionX *= settings.get(0).getValDouble();
				mc.thePlayer.motionZ *= settings.get(0).getValDouble();
			}
		}
	}
}
