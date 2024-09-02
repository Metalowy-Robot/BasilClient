package Basil.utils.esp;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class BlinkUtils {
	
	static float lw;
	
	public static void blockESPBox(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
		
		Minecraft mc = Minecraft.getMinecraft();
		
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(lw);
		GL11.glColor4d(0, 0, 1, 0.15F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
		GL11.glColor4d(0, 1, 0.3F, 0.5F);
		RenderManager rm = mc.getRenderManager();
		RenderGlobal.func_181561_a(new AxisAlignedBB(
				minx - rm.renderPosX - 0.05, 
				miny - rm.renderPosY - 0.05, 
				minz - rm.renderPosZ - 0.05, 
				maxx - rm.renderPosX + 0.1, 
				maxy - rm.renderPosY + 0.1, 
				maxz - rm.renderPosZ + 0.1
		));
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4d(1, 1, 1, 1);
		
	}
	
	public static void setLineWidth(float w) {
		lw = w;
	}
}
