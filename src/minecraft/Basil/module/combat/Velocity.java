package Basil.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Velocity extends Module{

	public Velocity() {
		super("Velocity", -1, Category.COMBAT);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Horizontal", this, 0.85, 0, 1, false));
		Client.sm.rSetting(new Setting("Vertical", this, 1.0, 0, 1, false));
	}
}
