package Basil.gui;

import org.lwjgl.opengl.Display;

import Basil.module.Category;
import Basil.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class GuiIngameHook extends GuiIngame{
	
	public Minecraft mc = Minecraft.getMinecraft();

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}
	
	public void renderGameOverlay(float p_175180_1_) {
		ScaledResolution sr = new ScaledResolution(mc);
		super.renderGameOverlay(p_175180_1_);
		
		if (!mc.gameSettings.showDebugInfo) {
			renderArrayList();
		}
	}
	
	private void renderArrayList() {
		ScaledResolution sr = new ScaledResolution(mc);
		
		int yCount = 0;
		int i = 0;
		long x = 0;
		for (Basil.module.Module m : Basil.module.ModuleManager.getModules()) {
			
			m.onRender();
			
			GlStateManager.scale(1.3, 1.3, 1);
			
			int color;
			
			switch (m.getCategory()) {
				case MOVEMENT:
					color = 0x42f575;
					break;
				case HARDCORE:
					color = 0xe412b7;
					break;
				case COMBAT:
					color = 0xe03400;
					break;
				case PLAYER:
					color = 0xd0ed2d;
					break;
				case RENDER:
					color = 0x1b70cc;
					break;
				case MISC:
					color = 0x9e21d9;
					break;
				default:
					color = 0x000000;
					break;
			}
			
			long offset = System.currentTimeMillis() / 30;
			offset += i * 8;
			
			int c1 = (int) (Math.abs((color % 256 + (offset)) % 512 - 256) / 2 + 128);
			int c2 = (int) (((Math.abs(((color % (256 * 256) - color % 256) / 256 + (offset)) % 512 - 256)) / 2 + 128) * 256);
			int c3 = (int) (((Math.abs(((color - color % (256 * 256)) / (256 * 256) + (offset)) % 512 - 256)) / 2 + 128) * 256 * 256);
			
			offset--;
			int nc1 = (int) (Math.abs((color % 256 + (offset)) % 512 - 256) / 2 + 128);
			int nc2 = (int) (((Math.abs(((color % (256 * 256) - color % 256) / 256 + (offset)) % 512 - 256)) / 2 + 128) * 256);
			int nc3 = (int) (((Math.abs(((color - color % (256 * 256)) / (256 * 256) + (offset)) % 512 - 256)) / 2 + 128) * 256 * 256);
			
			color = Math.min(c1, nc1) + Math.min(c2, nc2) + Math.min(c3, nc3);
			
			if (m.isToggled()) {
				drawRect(1, yCount + 1, (int) (Wrapper.fr.getStringWidth(m.getName()) + 2), yCount + 11, 0x80000000);
				Wrapper.fr.drawString(m.getName(), 2, yCount + 2, color);
				yCount += 10;
				i++;
				x++;
			}
			
			GlStateManager.scale(1 / 1.3, 1 / 1.3, 1);
		}
	}

}
