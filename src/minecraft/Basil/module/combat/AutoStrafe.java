package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.module.movement.AutoWalk;
import Basil.utils.Invoker;
import Basil.utils.Timer;
import de.Hero.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.*;

public class AutoStrafe extends Module{
	
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	int swait = 0, times_left = 1, type = 0, dir = 0;
	Timer t1 = new Timer();
	
	public AutoStrafe() {
		super("AutoStrafe", -1, Category.COMBAT);
	}
	
	public void setup() {
		ArrayList<String> empty = new ArrayList<>();
		Client.sm.rSetting(new Setting("Times Min", this, 2, 1, 9, true));
		Client.sm.rSetting(new Setting("Times Max", this, 5, 1, 9, true));
		Client.sm.rSetting(new Setting("Short Weight", this, 7, 0, 15, true));
		Client.sm.rSetting(new Setting("Long Weight", this, 3, 0, 15, true));
		Client.sm.rSetting(new Setting("1 = 10ms", this, "null", empty));
		Client.sm.rSetting(new Setting("Short Duration Min", this, 50, 20, 200, true));
		Client.sm.rSetting(new Setting("Short Duration Max", this, 70, 20, 200, true));
		Client.sm.rSetting(new Setting("Long Duration Min", this, 350, 250, 1100, true));
		Client.sm.rSetting(new Setting("Long Duration Max", this, 450, 250, 1100, true));
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindLeft.pressed = false;
		mc.gameSettings.keyBindRight.pressed = false;
	}
	
	public void onUpdate() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			if (t1.hasTimeElapsed(swait * 10, true)) {
				times_left--;
				if (times_left == 0) {
					if ((int)(Math.random() * (settings.get(2).getValDouble() + settings.get(3).getValDouble())) < settings.get(2).getValDouble()) {
						type = 0;
					}else {
						type = 1;
					}
					System.out.println(type);
					System.out.println((int)(Math.random() * (settings.get(2).getValDouble() + settings.get(3).getValDouble())));
					times_left = (int) (settings.get(0).getValDouble() + (Math.random() * (settings.get(1).getValDouble() - settings.get(0).getValDouble())));
				}
				if (type == 0) {
					swait = (int) (settings.get(4).getValDouble() + (Math.random() * (settings.get(5).getValDouble() - settings.get(4).getValDouble())));
				}else {
					swait = (int) (settings.get(6).getValDouble() + (Math.random() * (settings.get(7).getValDouble() - settings.get(6).getValDouble())));
				}
				dir = 1 - dir;
				if (dir == 0) {
					mc.gameSettings.keyBindLeft.pressed = false;
					mc.gameSettings.keyBindRight.pressed = true;
				}else {
					mc.gameSettings.keyBindLeft.pressed = true;
					mc.gameSettings.keyBindRight.pressed = false;
				}
			}
		}
	}
}
