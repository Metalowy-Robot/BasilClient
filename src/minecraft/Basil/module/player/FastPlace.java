package Basil.module.player;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class FastPlace extends Basil.module.Module{

	public FastPlace() {
		super("FastPlace", -1, Category.PLAYER);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			mc.rightClickDelayTimer = 0;
		}
	}
}
