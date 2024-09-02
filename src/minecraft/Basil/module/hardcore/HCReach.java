package Basil.module.hardcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class HCReach extends Module{
	
	static double currentReach = 1.0d;
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	public HCReach() {
		super("[HC] Reach", -1, Category.HARDCORE);
	}
	
	public void setup() {
		ArrayList<String> diffs2 = new ArrayList<>();
		diffs2.add("EASY");
		diffs2.add("MEDIUM");
		diffs2.add("HARD");
		diffs2.add("EXTREME");
		Client.sm.rSetting(new Setting("Difficulty", this, "EASY", diffs2));
		if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EASY")) {
			currentReach = 0.95;
		}
		if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("MEDIUM")) {
			currentReach = 0.9;
		}
		if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("HARD")) {
			currentReach = 0.85;
		}
		if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EXTREME")) {
			currentReach = 0.75;
		}
	}
	
	public void onUpdate() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EASY")) {
				currentReach = 0.95;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("MEDIUM")) {
				currentReach = 0.9;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("HARD")) {
				currentReach = 0.85;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EXTREME")) {
				currentReach = 0.75;
			}
		}
	}
	
	public void onDisable() {
		currentReach = 1.0d;
	}
	
	public static double getReach() {
		return currentReach;
	}
}
