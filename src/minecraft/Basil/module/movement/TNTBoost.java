package Basil.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.Timer;
import de.Hero.settings.Setting;
import net.minecraft.util.DamageSource;

public class TNTBoost extends Basil.module.Module{
	
	boolean waiting = false;
	Timer t1 = new Timer();
	
	public TNTBoost() {
		super("TNT Boost", -1, Category.MOVEMENT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Horizontal", this, 1.5, 1, 2, false));
		Client.sm.rSetting(new Setting("Vertical", this, 1.5, 1, 2, false));
	}
	
	public void onUpdate() {
		if (isToggled()) {
			if (waiting && t1.hasTimeElapsed(50, true)) {
				ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
				mc.thePlayer.motionX *= settings.get(0).getValDouble();
				mc.thePlayer.motionY *= settings.get(1).getValDouble();
				mc.thePlayer.motionZ *= settings.get(0).getValDouble();
				waiting = false;
			}
		}
	}
	
	public void playerHitBy(DamageSource s) {
		if (isToggled()) {
			if (s.isExplosion()) {
				waiting = true;
				t1.reset();
			}
		}
	}
}
