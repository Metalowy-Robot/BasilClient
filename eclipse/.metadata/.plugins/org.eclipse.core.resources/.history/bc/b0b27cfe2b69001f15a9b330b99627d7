package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class Haze extends Basil.module.Module{
	
	public Haze() {
		super("Haze", -1, Category.MOVEMENT);
	}
	
	public void onFrame() {
		if (isToggled()) {
			mc.thePlayer.moveForward = (float) Math.sqrt(mc.thePlayer.moveStrafing * mc.thePlayer.moveStrafing + mc.thePlayer.moveForward * mc.thePlayer.moveForward);
			mc.thePlayer.moveStrafing = 0;
		}
	}
}
