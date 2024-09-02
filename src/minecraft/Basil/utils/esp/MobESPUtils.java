package Basil.utils.esp;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class MobESPUtils {
	
	static float lw;
	
	public static void entityESPBox(Entity entity, int mode) {
		
		Minecraft mc = Minecraft.getMinecraft();
		
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(lw);
		GL11.glColor4d(0, 0, 1, 0.15F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
		if (mode == 0) {
			GL11.glColor4d(
					1 - mc.thePlayer.getDistanceSqToEntity(entity) / 100,
					mc.thePlayer.getDistanceSqToEntity(entity) / 100,
					0, 0.5F
					);
		}
		if (mode == 1) {
			GL11.glColor4d(0, 0.3F, 1, 0.5F);
		}
		if (mode == 2) {
			GL11.glColor4d(1, 1, 0, 0.5F);
		}
		if (mode == 3) {
			GL11.glColor4d(0.7F, 0, 0, 0.5F);
		}
		if (mode == 4) {
			GL11.glColor4d(0, 1, 0, 0.5F);
		}
		
		RenderManager rm = mc.getRenderManager();
		RenderGlobal.func_181561_a(
				new AxisAlignedBB(
						entity.boundingBox.minX
							- 0.05
							- rm.renderPosX,
						entity.boundingBox.minY
							- 0.05
							- rm.renderPosY,
						entity.boundingBox.minZ
							- 0.05
							- rm.renderPosZ,
						entity.boundingBox.maxX
							+ 0.05
							- rm.renderPosX,
						entity.boundingBox.maxY
							+ 0.1
							- rm.renderPosY,
						entity.boundingBox.maxZ
							+ 0.05
							- rm.renderPosZ
						)
				);
		
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
