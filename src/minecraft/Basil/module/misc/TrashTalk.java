package Basil.module.misc;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.Invoker;
import de.Hero.settings.Setting;

public class TrashTalk extends Module{

	public TrashTalk() {
		super("TrashTalk", Keyboard.KEY_P, Category.MISC);
	}
	
	public void setup() {
		List langs = new ArrayList<String>();
		langs.add("EN");
		langs.add("PL");
		Client.sm.rSetting(new Setting("Language", this, "EN", (ArrayList<String>) langs));
	}
	
	public void onEnable() {
		String trash = "ez";
		int x = (int) (Math.random() * 100);
		ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
		if (settings.get(0).getValString().equalsIgnoreCase("EN")) {
			if (x <= 30) {
				trash = "ez";
			}else if (x <= 45) {
				trash = "Get a life";
			}else if (x <= 55) {
				trash = "L";
			}else if (x <= 65) {
				trash = "learn to play";
			}else if (x <= 75) {
				trash = "imagine being so bad";
			}else if (x <= 85) {
				trash = "you suck";
			}else if (x <= 90) {
				trash = "learn to play, you braindead ape";
			}else if (x <= 95) {
				trash = "E Z";
			}else {
				trash = "get rekt";
			}
		}else if (settings.get(0).getValString().equalsIgnoreCase("PL")) {
			if (x <= 30) {
				trash = "ez";
			}else if (x <= 45) {
				trash = "naucz sie grac";
			}else if (x <= 60) {
				trash = "Znajdz sobie zycie";
			}else if (x <= 70) {
				trash = "Odinstaluj minecrafta noobie";
			}else if (x <= 80) {
				trash = "Czemu grasz?";
			}else if (x <= 90) {
				trash = "Pierwszy raz widzisz klawiature?";
			}else {
				trash = "Ale bot";
			}
		}
		Invoker.sendChatMessage(trash);
		this.toggle();
	}
}
