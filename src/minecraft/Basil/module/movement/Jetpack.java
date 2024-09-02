package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class Jetpack extends Basil.module.Module{

	public Jetpack() {
		super("Jetpack", -1, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if (isToggled()) {
			if (mc.gameSettings.keyBindJump.pressed) {
				mc.thePlayer.jump();
			}

			super.onDisable();
		}
	}
	
}
