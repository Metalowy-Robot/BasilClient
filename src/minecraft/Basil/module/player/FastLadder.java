package Basil.module.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import de.Hero.settings.Setting;

public class FastLadder extends Basil.module.Module{

	public FastLadder() {
		super("FastLadder", -1, Category.PLAYER);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Speed", this, 1, 1, 5, false));
	}
	
	public void onUpdate() {
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			
			if (mc.thePlayer.isOnLadder() && Keyboard.isKeyDown(Invoker.getForwardCode())) {
				mc.thePlayer.motionY = settings.get(0).getValDouble() / 10;
			}else if (mc.thePlayer.isOnLadder()){
				mc.thePlayer.motionY = -settings.get(0).getValDouble() / 10;
			}
		}
	}
}
