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

public class AutoWTap extends Module{
	
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	int wait = 0, lwait = 0;
	boolean w1 = false, w2 = false;
	Timer t1 = new Timer();
	Entity target;
	
	public AutoWTap() {
		super("AutoWTap", -1, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Delay Min", this, 15, 1, 180, true));
		Client.sm.rSetting(new Setting("Delay Max", this, 30, 1, 180, true));
		Client.sm.rSetting(new Setting("Duration Min", this, 10, 1, 100, true));
		Client.sm.rSetting(new Setting("Duration Max", this, 20, 1, 100, true));
		Client.sm.rSetting(new Setting("Keep Dist", this, false));
		Client.sm.rSetting(new Setting("Duration Min", this, 60, 1, 800, true));
		Client.sm.rSetting(new Setting("Duration Max", this, 100, 1, 800, true));
		Client.sm.rSetting(new Setting("Set To Keyboard", this , true));
	}
	
	public void onFrame() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			if (settings.get(4).getValBoolean()) {
				if (w1 && t1.hasTimeElapsed(wait, true)){
					w1 = false;
					mc.gameSettings.keyBindForward.pressed = false;
					w2 = true;
					wait = (int) (settings.get(2).getValDouble() + 
							Math.random() * (
									settings.get(3).getValDouble() - settings.get(2).getValDouble()
								));
					lwait = (int) (settings.get(5).getValDouble() + 
							Math.random() * (
									settings.get(6).getValDouble() - settings.get(5).getValDouble()
								));
					t1.reset();
				}else if (w2 && (mc.thePlayer.getDistanceToEntity(target) >= 4.3 || t1.hasTimeElapsed(lwait, false)) && t1.hasTimeElapsed(wait, false)) {
					w2 = false;
					mc.gameSettings.keyBindForward.pressed = true;
					t1.reset();
					if (settings.get(7).getValBoolean()) {
						mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());
					}
				}
					
			}else {
				if (t1.hasTimeElapsed(wait, true)){
					if (w1) {
						w1 = false;
						mc.gameSettings.keyBindForward.pressed = false;
						w2 = true;
						wait = (int) (settings.get(2).getValDouble() + 
								Math.random() * (
										settings.get(3).getValDouble() - settings.get(2).getValDouble()
									));
						t1.reset();
					}else if (w2) {
						w2 = false;
						mc.gameSettings.keyBindForward.pressed = true;
					}
				}
			}
		}
	}
	
	public void entityAttackedByPlayer(Entity t) {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled() && !(w1 || w2)) {
			wait = (int) (settings.get(0).getValDouble() + 
					Math.random() * (
							settings.get(1).getValDouble() - settings.get(0).getValDouble()
							));
			t1.reset();
			w1 = true;
			target = t;
		}
	}
}
