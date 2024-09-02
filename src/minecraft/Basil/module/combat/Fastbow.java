package Basil.module.combat;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.module.*;
import Basil.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.*;

public class Fastbow extends Module{

	public Fastbow() {
		super("Fastbow", Keyboard.KEY_B, Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if (!this.isToggled() || !(mc.thePlayer.getHealth() > 0
				&& (mc.thePlayer.onGround || mc.thePlayer.capabilities.isCreativeMode)
				&& mc.thePlayer.inventory.getCurrentItem() != null && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow
				&& mc.gameSettings.keyBindUseItem.pressed)) {
			return;
		}
		
		mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
		mc.thePlayer.inventory.getCurrentItem().getItem().onItemRightClick(mc.thePlayer.inventory.getCurrentItem(), mc.theWorld, mc.thePlayer);
		
		for (int i = 0; i < 20; i++) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
		}
		
		mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(Action.RELEASE_USE_ITEM, new BlockPos(0,0,0), EnumFacing.DOWN));
		mc.thePlayer.inventory.getCurrentItem().getItem().onPlayerStoppedUsing(mc.thePlayer.inventory.getCurrentItem(), mc.theWorld, mc.thePlayer, 10);
		
		super.onUpdate();
	}
}
