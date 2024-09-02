package Basil.module.player;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import Basil.utils.Timer;
import net.minecraft.util.MovingObjectPosition;

public class AntiAfk extends Basil.module.Module{

	public AntiAfk() {
		super("AntiAFK", -1, Category.PLAYER);
	}
	
	Timer t = new Timer();
	
	public void onUpdate() {
		if (isToggled()) {
			mc.gameSettings.keyBindForward.pressed = true;
			if (t.hasTimeElapsed((long) (Math.random() * 500 + 250), true)) {
				if (Math.random() <= 0.5) {
					mc.thePlayer.rotationYaw = (float) (mc.thePlayer.prevRotationYaw + 45 + Math.random() * 45);
				}else {
					mc.thePlayer.rotationYaw = (float) (mc.thePlayer.prevRotationYaw - 45 + Math.random() * 45);
				}
			}
		}
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindForward.pressed = false;
	}
}
