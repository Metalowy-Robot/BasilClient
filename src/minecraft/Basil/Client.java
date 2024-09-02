package Basil;

import org.lwjgl.opengl.Display;

import Basil.alt.AltManager;
import Basil.module.ModuleManager;
import Basil.utils.FileManager;
import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;

public class Client {
	
	public static String name = "Basil", version = "1.8.8b1", creator = "MetalowyRobot";
	
	public static ModuleManager moduleManager;
	public static SettingsManager sm;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static FileManager fm;
	public static boolean fmDone = false;
	
	public static void startClient() {
		sm = new SettingsManager();
		moduleManager = new ModuleManager();
		clickGUI = new ClickGUI();
		altManager = new AltManager();
		fm = new FileManager();
		fm.init();
		fmDone = true;
		
		//BL
		moduleManager.getModuleByName("ClickGui").setToggled(false);
		moduleManager.getModuleByName("FreeCam").setToggled(false);
		moduleManager.getModuleByName("AimBot").setToggled(false);
		moduleManager.getModuleByName("Blink").setToggled(false);
		
		Display.setTitle(name + " " + version + " by " + creator);
	}
}
