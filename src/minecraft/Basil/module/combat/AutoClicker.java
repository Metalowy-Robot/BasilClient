package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.Invoker;
import Basil.utils.Timer;
import de.Hero.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.*;

public class AutoClicker extends Module{
	
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	int wait = 0;
	Timer t = new Timer();
	
	public AutoClicker() {
		super("AutoClicker", -1, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("CPS Min", this, 8, 6, 30, false));
		Client.sm.rSetting(new Setting("CPS Max", this, 11, 6, 30, false));
		Client.sm.rSetting(new Setting("Hold Only", this, true));
	}
	
	public void onFrame() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			if ((mc.gameSettings.keyBindAttack.pressed || !settings.get(2).getValBoolean()) && t.hasTimeElapsed(wait, true)) {
				Invoker.leftClick();
				wait = (int) (1000 / (settings.get(0).getValDouble() + 
						Math.random() * (
								settings.get(1).getValDouble() - settings.get(0).getValDouble()
						)));
			}
		}
	}
}
