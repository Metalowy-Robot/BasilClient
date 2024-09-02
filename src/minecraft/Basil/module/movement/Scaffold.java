package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Scaffold extends Basil.module.Module{

	public Scaffold() {
		super("Scaffold", -1, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			Entity p = mc.thePlayer;
			BlockPos bp = new BlockPos(p.posX, p.getEntityBoundingBox().minY, p.posZ);
			
			if (mc.thePlayer.getHorizontalFacing() == EnumFacing.SOUTH) {
				place(bp.add(0, -1, -1), EnumFacing.NORTH);
			}
			if (mc.thePlayer.getHorizontalFacing() == EnumFacing.WEST) {
				place(bp.add(1, -1, 0), EnumFacing.EAST);
			}
			if (mc.thePlayer.getHorizontalFacing() == EnumFacing.EAST) {
				place(bp.add(-1, -1, 0), EnumFacing.WEST);
			}
			if (mc.thePlayer.getHorizontalFacing() == EnumFacing.NORTH) {
				place(bp.add(0, -1, 1), EnumFacing.SOUTH);
			}
		}
		
	}
	
	void place(BlockPos p, EnumFacing f) {
		EntityPlayerSP _p = mc.thePlayer;
		
		if (_p.getHeldItem() != null && _p.getHeldItem().getItem() instanceof ItemBlock) {
			_p.swingItem();
			mc.playerController.onPlayerRightClick(_p, mc.theWorld, _p.getHeldItem(), p, f, new Vec3(0.5, 0.5, 05));
			double x = p.getX() + 0.25 - _p.posX;
			double y = p.getY() + 0.25 - _p.posY;
			double z = p.getZ() + 0.25 - _p.posZ;
			double dist = MathHelper.sqrt_double(x * x + z * z);
			float yaw = (float) (Math.atan2(x, z) * 180 / Math.PI - 90);
			float pitch = (float) (Math.atan2(y, dist) * 180 / Math.PI);
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(yaw, pitch, _p.onGround));
		}
	}
}
