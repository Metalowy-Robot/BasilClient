package Basil.module.player;

import java.util.*;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import Basil.utils.*;
import Basil.utils.Timer;
import de.Hero.settings.Setting;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FreeCam extends Basil.module.Module{
	
	public static boolean capture = false;
	Timer t1 = new Timer();
	double x = 0, y = 0, z = 0, mx = 0, my = 0, mz = 0;
	boolean g = true, f = false;
	
	public FreeCam() {
		super("FreeCam", Keyboard.KEY_F6, Category.PLAYER);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Speed", this, 2, 0.5, 5, false));
	}
	
	public void onEnable() {
		capture = true;
		x = mc.thePlayer.posX;
		y = mc.thePlayer.posY;
		z = mc.thePlayer.posZ;
		mx = mc.thePlayer.motionX;
		my = mc.thePlayer.motionY;
		mz = mc.thePlayer.motionZ;
		g = mc.thePlayer.onGround;
		f = mc.thePlayer.capabilities.isFlying;
		mc.thePlayer.capabilities.allowFlying = true;
		t1.reset();
	}
	
	public void onUpdate() {
		ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
		if (isToggled()) {
			Invoker.setNoClip(true);
			mc.thePlayer.onGround = false;
			NetHandlerPlayClient.netManager.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, g));
			mc.thePlayer.capabilities.isFlying = true;
			if (mc.gameSettings.keyBindJump.isPressed()) {
				mc.thePlayer.motionY += 0.1f;
			}
			
			if (mc.gameSettings.keyBindSneak.isPressed()) {
				mc.thePlayer.motionY -= 0.1f;
			}
			
			if (mc.gameSettings.keyBindForward.isPressed()) {
				mc.thePlayer.capabilities.setFlySpeed((float) (settings.get(0).getValDouble() / 10));
			}
		}
	}
	
	public void onDisable() {
		Invoker.setNoClip(false);
		mc.thePlayer.setPositionAndUpdate(x, y, z);
		mc.thePlayer.capabilities.setFlySpeed(0.1f);
		mc.thePlayer.capabilities.isFlying = f;
		mc.thePlayer.capabilities.allowFlying = mc.thePlayer.capabilities.isCreativeMode;
		mc.thePlayer.motionX = mx;
		mc.thePlayer.motionY = my;
		mc.thePlayer.motionZ = mz;
		capture = false;
	}
}
