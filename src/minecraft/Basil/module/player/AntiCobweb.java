package Basil.module.player;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import net.minecraft.util.MovingObjectPosition;

public class AntiCobweb extends Basil.module.Module{

	public AntiCobweb() {
		super("AntiCobweb", -1, Category.PLAYER);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			mc.thePlayer.isInWeb = false;
		}
	}
}
