package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class Sprint extends Basil.module.Module{

	public Sprint() {
		super("Sprint", Keyboard.KEY_F, Category.MOVEMENT);
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(false);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			mc.gameSettings.keyBindSprint.pressed = true;
		}
	}
}
