package Basil.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Basil.Client;
import Basil.module.ModuleManager;
import de.Hero.settings.Setting;

public class FileManager {
	
	File root = new File("Basil");
	File modules = new File(root, "modules.json");
	
	public void init() {
		if (!root.exists()) {
			root.mkdirs();
		}
		
		if (!modules.exists()) {
			saveMods();
		}else {
			loadMods();
		}
	}
	
	public void saveMods() {
		Client.fmDone = false;
		try {
			JsonObject json = new JsonObject();
			for (Basil.module.Module m : ModuleManager.getModules()) {
				ArrayList<Setting> setts = Basil.Client.sm.getSettingsByMod(m);
				JsonObject jmod = new JsonObject();
				jmod.addProperty("enabled", m.isToggled());
				jmod.addProperty("key", m.getKey());
				jmod.addProperty("settingCount", setts.size());
				JsonObject jsetts = new JsonObject();
				for (int i = 0; i < setts.size(); i++) {
					JsonObject jset = new JsonObject();
					jset.addProperty("type", getTypeFromSetting(setts.get(i)));
					if (setts.get(i).isCheck()) {
						jset.addProperty("value", setts.get(i).getValBoolean());
					}else if (setts.get(i).isCombo()) {
						jset.addProperty("value", setts.get(i).getValString());
					}else {
						jset.addProperty("value", setts.get(i).getValDouble());
					}
					jsetts.add("setting" + i, jset);
				}
				jmod.add("setts", jsetts);
				json.add(m.getName(), jmod);
			}
			PrintWriter save = new PrintWriter(new FileWriter(modules));
			save.println(JsonUtils.prettyGson.toJson(json));
			save.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Client.fmDone = true;
	}
	
	public void loadMods() {
		Client.fmDone = false;
		try {
			BufferedReader load = new BufferedReader(new FileReader(modules));
			JsonObject json = (JsonObject) JsonUtils.parser.parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				Basil.module.Module m = ModuleManager.getModuleByName(entry.getKey());
				if (m != null) {
					JsonObject jmod = (JsonObject) entry.getValue();
					m.setToggled(jmod.get("enabled").getAsBoolean());
					m.setKey(jmod.get("key").getAsInt());
					ArrayList<Setting> setts = Client.sm.getSettingsByMod(m);
					for (int i = 0; i < jmod.get("settingCount").getAsInt(); i++) {
						String type = ((JsonObject) ((JsonObject) jmod.get("setts")).get("setting" + i)).get("type").getAsString();
						if (type.equalsIgnoreCase("Check")) {
							setts.get(i).setValBoolean(((JsonObject) ((JsonObject) jmod.get("setts")).get("setting" + i)).get("value").getAsBoolean());
						}else if (type.equalsIgnoreCase("Combo")) {
							setts.get(i).setValString(((JsonObject) ((JsonObject) jmod.get("setts")).get("setting" + i)).get("value").getAsString());
						}else {
							setts.get(i).setValDouble(((JsonObject) ((JsonObject) jmod.get("setts")).get("setting" + i)).get("value").getAsDouble());
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		Client.fmDone = true;
	}
	
	public String getTypeFromSetting(Setting s) {
		if (s.isCheck()) {
			return "Check";
		}else if (s.isCombo()) {
			return "Combo";
		}else {
			return "Slider";
		}
	}
	
}
