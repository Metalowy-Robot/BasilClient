package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import net.minecraft.network.play.client.*;

public class Step extends Basil.module.Module{

	public Step() {
		super("Step", -1, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if (isToggled()) {
			if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
						mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ, mc.thePlayer.onGround));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
						mc.thePlayer.posX, mc.thePlayer.posY + 0.72, mc.thePlayer.posZ, mc.thePlayer.onGround));
				mc.thePlayer.stepHeight = 1f;
			}else {
				mc.thePlayer.stepHeight = 0.5f;
			}
		}else {
			mc.thePlayer.stepHeight = 0.5f;
		}
	}
	
}
