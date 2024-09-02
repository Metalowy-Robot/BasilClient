package Basil.module;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.utils.FileManager;
import de.Hero.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class Module {

	protected Minecraft mc = Minecraft.getMinecraft();
	private String name;
	private int key;
	private boolean toggled;
	private Category category;
	private boolean lst = false;
	private boolean rec = false;
	private int recKey = 0;
	
	public Module(String s, int k, Category c) {
		name = s;
		key = k;
		category = c;
		toggled = false;
		setup();
		_addSetKey();
		_updateKey();
	}
	
	public void _addSetKey() {
		ArrayList<String> empty = new ArrayList();
		Client.sm.rSetting(new Setting("Keybind", this, "null", empty));
	}
	
	public void _updateKey() {
		ArrayList<Setting> a = Client.sm.getSettingsByMod(this);
		if (this.getKey() != -1) {
			a.get(a.size() - 1).name = Keyboard.getKeyName(this.getKey());
		}else {
			a.get(a.size() - 1).name = "NONE";
		}
		if (Client.fmDone)
			Client.fm.saveMods();
	}
	
	public void toggle() {
		toggled = !toggled;
		if (toggled) {
			this.onEnable();
		}else {
			this.onDisable();
		}
		if (Client.fmDone)
			Client.fm.saveMods();
	}
	
	public void playerHitBy(DamageSource s) {
		
	}
	
	public void entityAttacked(Entity t) {
		
	}
	
	public void entityAttackedByPlayer(Entity t) {
		
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onUpdate() {
		
	}
	
	public void onRender() {
		
	}
	
	public void onFrame() {
		
	}
	
	public void setup() {
		
	}

	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public Category getCategory() {
		return category;
	}
	
	public void enableOnStartUp() {
		toggled = true;
		try {
			toggle();
			onEnable();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
