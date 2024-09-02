package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class Sneak extends Basil.module.Module{

	public Sneak() {
		super("Sneak", -1, Category.MOVEMENT);
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindSneak.pressed = false;
	}
	
	public void onUpdate() {
		if (isToggled()) {
			mc.gameSettings.keyBindSneak.pressed = true;
		}
	}
}
