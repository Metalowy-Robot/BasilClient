package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;

public class Dolphin extends Basil.module.Module{

	public Dolphin() {
		super("Dolphin", -1, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if (isToggled()) {
			if (mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY += 0.04;
			}
		}
	}
	
}
