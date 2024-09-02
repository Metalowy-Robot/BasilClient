package Basil.module.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Invoker;
import de.Hero.settings.Setting;

public class AutoRespawn extends Basil.module.Module{
	
	public AutoRespawn() {
		super("AutoRespawn", -1, Category.PLAYER);
	}
	
	public void onUpdate() {
		if (isToggled()) {
			if (mc.thePlayer.isDead) {
				mc.thePlayer.respawnPlayer();
			}
		}
	}
}
