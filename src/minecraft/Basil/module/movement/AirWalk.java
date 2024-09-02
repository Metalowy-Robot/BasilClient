package Basil.module.movement;

import org.lwjgl.input.Keyboard;

import Basil.module.Category;
import Basil.module.Module;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class AirWalk extends Basil.module.Module{
	
	BlockPos last;
	
	public AirWalk() {
		super("AirWalk", Keyboard.KEY_L, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY - 1, mc.thePlayer.posZ);
			
			if (mc.theWorld.getBlock(bp) == Blocks.air) {
				if (last != null) {
					mc.theWorld.setBlockToAir(last);
				}
				mc.theWorld.setBlockState(bp, Blocks.glass.getDefaultState());
				last = bp;
			}
		}
	}
	
	public void onDisable() {
		if (last != null) {
			mc.theWorld.setBlockToAir(last);
		}
	}
}
