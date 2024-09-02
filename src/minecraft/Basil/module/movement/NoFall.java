package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Basil.module.Module{

	public NoFall() {
		super("NoFall", Keyboard.KEY_N, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			if (mc.thePlayer.fallDistance > 2f) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			}
			
			super.onUpdate();
		}
	}
	
}
