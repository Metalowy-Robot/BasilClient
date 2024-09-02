package de.Hero.clickgui.util;

import java.awt.Color;

//Deine Imports
import Basil.Client;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int)Client.sm.getSettingByName("GuiRed").getValDouble(), (int)Client.sm.getSettingByName("GuiGreen").getValDouble(), (int)Client.sm.getSettingByName("GuiBlue").getValDouble());
	}
}
