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

public class AutoRun extends Module{
	
	ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
	
	int hits, req, wait;
	boolean waiting = false;
	Timer t1 = new Timer();
	Entity source;
	
	public AutoRun() {
		super("AutoRun", -1, Category.COMBAT);
	}
	
	public void setup() {
		ArrayList<String> empty = new ArrayList<>();
		Client.sm.rSetting(new Setting("Combo Min", this, 3, 2, 10, true));
		Client.sm.rSetting(new Setting("Combo Max", this, 4, 2, 10, true));
		Client.sm.rSetting(new Setting("1 = 50ms", this, "null", empty));
		Client.sm.rSetting(new Setting("Duration Min", this, 60, 20, 200, true));
		Client.sm.rSetting(new Setting("Duration Max", this, 80, 20, 200, true));
	}
	
	public void onUpdate() {
		settings = Client.sm.getSettingsByMod(this);
		if (isToggled() &&  mc.thePlayer.hurtTime >= 9 && !waiting) {
			hits++;
			if (hits >= req) {
				hits = 0;
				req = (int) (settings.get(0).getValDouble() + Math.random() * (settings.get(1).getValDouble() - settings.get(0).getValDouble() + 1));
				wait = (int) (settings.get(3).getValDouble() + Math.random() * (settings.get(4).getValDouble() - settings.get(3).getValDouble()));
				waiting = true;
				t1.reset();
				((AimBot) Client.moduleManager.getModuleByName("Aimbot")).inverse();
			}
		}
		if (isToggled()) {
			if (waiting && t1.hasTimeElapsed(wait * 50, true)) {
				waiting = false;
				((AimBot) Client.moduleManager.getModuleByName("Aimbot")).inverse();
			}
		}
	}
	
	public void onEnable() {
		settings = Client.sm.getSettingsByMod(this);
		hits = 0;
		req = (int) (settings.get(0).getValDouble() + Math.random() * (settings.get(1).getValDouble() - settings.get(0).getValDouble() + 1));
	}
	
	public void entityAttackedByPlayer(Entity t) {
		hits = 0;
	}
}
