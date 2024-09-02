package Basil.module.player;

import java.util.*;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.network.Packet;

public class FakeLag extends Basil.module.Module{
	
	public static boolean capture = false;
	public static Queue<Packet> packets = new LinkedList<Packet>();
	public static Queue<Long> packetsTime = new LinkedList<Long>();

	public FakeLag() {
		super("FakeLag", -1, Category.PLAYER);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Delay Min", this, 200, 50, 700, true));
		Client.sm.rSetting(new Setting("Delay Max", this, 350, 50, 700, true));
	}
	
	public void onFrame() {
		if (isToggled()) {
			while (!packetsTime.isEmpty()) {
				long nextTime = packetsTime.peek();
				if (nextTime <= System.currentTimeMillis()) {
					packetsTime.remove();
					Packet packet = packets.remove();
					net.minecraft.client.network.NetHandlerPlayClient.netManager.sendPacket(packet);
				}else {
					break;
				}
			}
		}
	}
	
	public void onEnable() {
		capture = true;
	}
	
	public void onDisable() {
		capture = false;
		while (!packetsTime.isEmpty()) {
			packetsTime.remove();
			Packet packet = packets.remove();
			net.minecraft.client.network.NetHandlerPlayClient.netManager.sendPacket(packet);
		}
	}
}
