package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import net.minecraft.entity.Entity;

public class AutoJump extends Basil.module.Module{

	public AutoJump() {
		super("AutoJump", -1, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			if (mc.thePlayer.onGround && !mc.thePlayer.isSneaking() && !this.mc.gameSettings.keyBindSneak.pressed 
					&& mc.theWorld.getCollidingBoundingBoxes((Entity) mc.thePlayer, 
							mc.thePlayer.getEntityBoundingBox().offset(0.0D, -0.5D, 0.0D).expand(-0.001D, 0.0D, -0.001D)).isEmpty()) {
				mc.thePlayer.jump();
			}
		}
	}
	
}
