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

public class Hitreg extends Module{
	
	public static boolean hitreg = false;
	
	public Hitreg() {
		super("1.7 Hitreg", -1, Category.COMBAT);
	}
	
	public void onUpdate() {
		hitreg = isToggled();
	}
}
