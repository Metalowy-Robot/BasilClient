package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class BlinkBack extends Basil.module.Module{
	
	public BlinkBack() {
		super("BlinkBack", -1, Category.MOVEMENT);
	}
	
	public void onEnable() {
		toggle();
		mc.thePlayer.setPositionAndUpdate(
				((Blink)Client.moduleManager.getModuleByName("Blink")).lastX, 
				((Blink)Client.moduleManager.getModuleByName("Blink")).lastY, 
				((Blink)Client.moduleManager.getModuleByName("Blink")).lastZ
			);
		((Blink)Client.moduleManager.getModuleByName("Blink")).packets = new ArrayList<C03PacketPlayer>();
		Client.moduleManager.getModuleByName("Blink").setToggled(false);
	}
}
