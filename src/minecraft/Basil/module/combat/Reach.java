package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.Invoker;
import de.Hero.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.*;

public class Reach extends Module{
	
	static double currentReach = 1.0d;
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	public Reach() {
		super("Reach", -1, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Reach", this, 1.05, 1.0, 2.0, false));
		currentReach = Client.sm.getSettingsByMod(this).get(0).getValDouble();
	}
	
	public void onUpdate() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			currentReach = settings.get(0).getValDouble();
		}
	}
	
	public void onDisable() {
		currentReach = 1.0d;
	}
	
	public static double getReach() {
		return currentReach;
	}
}
