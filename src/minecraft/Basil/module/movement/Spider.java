package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;

public class Spider extends Basil.module.Module{

	public Spider() {
		super("Spider", -1, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if (isToggled()) {
			if (Invoker.isCollidedHorizontally()) {
				Invoker.setMotionY(0.2);
				
				float var6 = 0.015f;
				if (Invoker.getMotionX() < (double)-var6) {
					Invoker.setMotionX((double)-var6);
				}
				if (Invoker.getMotionX() > (double)-var6) {
					Invoker.setMotionX((double)-var6);
				}
				
				if (Invoker.getMotionZ() < (double)-var6) {
					Invoker.setMotionZ((double)-var6);
				}
				if (Invoker.getMotionZ() > (double)-var6) {
					Invoker.setMotionZ((double)-var6);
				}
				
				Invoker.setFallDistance(0);
				
				if (Invoker.getMotionY() < (double)var6) {
					Invoker.setMotionY((double)-var6);
				}
			}
		}
	}
	
}
