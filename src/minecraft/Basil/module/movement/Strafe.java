package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;

public class Strafe extends Basil.module.Module{
	
	public Strafe() {
		super("Strafe", -1, Category.MOVEMENT);
	}
	
	public void onFrame() {
		if (isToggled()) {
			if (mc.gameSettings.keyBindLeft.pressed == false && mc.gameSettings.keyBindRight.pressed == false) {
				if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
					mc.gameSettings.keyBindLeft.pressed = true;
				}else if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
					mc.gameSettings.keyBindRight.pressed = true;
				}
			}
			if (!Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
				mc.gameSettings.keyBindLeft.pressed = false;
			}else if (!Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
				mc.gameSettings.keyBindRight.pressed = false;
			}
		}
	}
}
