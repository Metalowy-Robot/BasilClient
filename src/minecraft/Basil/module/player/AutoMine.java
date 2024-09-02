package Basil.module.player;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import net.minecraft.util.MovingObjectPosition;

public class AutoMine extends Basil.module.Module{

	public AutoMine() {
		super("AutoMine", -1, Category.PLAYER);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			MovingObjectPosition hover = Invoker.getObjectMouseOver();
			if (hover.typeOfHit != null) {
				if (hover.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					Invoker.setKeyBindAttackPressed(true);
				}else {
					Invoker.setKeyBindAttackPressed(false);
				}
			}else {
				Invoker.setKeyBindAttackPressed(false);
			}
		}
	}
}
