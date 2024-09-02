package Basil.module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import Basil.Client;
import Basil.module.Category;
import Basil.module.Module;

public class ClickGui extends Module{

        public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<>();
        options.add("New");
        options.add("JellyLike");
        Client.sm.rSetting(new Setting("Design", this, "New", options));
        Client.sm.rSetting(new Setting("Sound", this, false));
        Client.sm.rSetting(new Setting("GuiRed", this, 252, 0, 255, true));
        Client.sm.rSetting(new Setting("GuiGreen", this, 207, 0, 255, true));
        Client.sm.rSetting(new Setting("GuiBlue", this, 3, 0, 255, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(Client.clickGUI);
        toggle();
    }
}