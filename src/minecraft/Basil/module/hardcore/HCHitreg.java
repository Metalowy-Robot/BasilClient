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

public class HCHitreg extends Module{
	
	static double currentHitreg = 1.0d;
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	public HCHitreg() {
		super("[HC] Hitreg", -1, Category.HARDCORE);
	}
	
	public void setup() {
		ArrayList<String> diffs2 = new ArrayList<>();
		diffs2.add("EASY");
		diffs2.add("MEDIUM");
		diffs2.add("HARD");
		diffs2.add("EXTREME");
		Client.sm.rSetting(new Setting("Difficulty ", this, "EASY", diffs2));
	}
	
	public void onUpdate() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EASY")) {
				currentHitreg = 15;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("MEDIUM")) {
				currentHitreg = 25;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("HARD")) {
				currentHitreg = 40;
			}
			if (Client.sm.getSettingsByMod(this).get(0).getValString().equalsIgnoreCase("EXTREME")) {
				currentHitreg = 60;
			}
		}
	}
	
	public void onDisable() {
		currentHitreg = 10;
	}
	
	public static double getHitreg() {
		return currentHitreg;
	}
}
