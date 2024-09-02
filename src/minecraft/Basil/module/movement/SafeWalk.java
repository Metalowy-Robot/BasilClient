package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class SafeWalk extends Basil.module.Module{
	
	public static boolean safeWalk = false;
	
	public SafeWalk() {
		super("SafeWalk", -1, Category.MOVEMENT);
	}
	
	public void onEnable() {
		safeWalk = true;
	}
	
	public void onUpdate() {
		if (isToggled()) {
			safeWalk = true;
		}
	}
	
	public void onDisable() {
		safeWalk = false;
	}
}
