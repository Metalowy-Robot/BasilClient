package Basil.module.render;

import java.util.ArrayList;

import Basil.Client;
import Basil.module.*;
import Basil.module.Module;
import Basil.utils.esp.*;
import de.Hero.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class ESP extends Module{

	public ESP() {
		super("ESP", -1, Category.RENDER);
	}
	
	public void setup() {
		Client.sm.rSetting(new Setting("Friendly Mob", this, false));
		Client.sm.rSetting(new Setting("Hostile Mob", this, false));
		Client.sm.rSetting(new Setting("Player", this, false));
		Client.sm.rSetting(new Setting("Chest", this, false));
		Client.sm.rSetting(new Setting("Line Width", this, 2, 1, 8, true));
	}
	
	public void onRender() {
		if (isToggled()) {
			ArrayList<Setting> settings = Client.sm.getSettingsByMod(this);
			MobESPUtils.setLineWidth((float) settings.get(4).getValDouble());
			ChestESPUtils.setLineWidth((float) settings.get(4).getValDouble());
			if (settings.get(0).getValBoolean()) {
				for (Object o : mc.theWorld.loadedEntityList) {
					if (o instanceof EntityAnimal) {
						MobESPUtils.entityESPBox((Entity)o, 0);
					}
				}
			}
			if (settings.get(1).getValBoolean()) {
				for (Object o : mc.theWorld.loadedEntityList) {
					if (o instanceof EntityMob) {
						MobESPUtils.entityESPBox((Entity)o, 0);
					}
				}
			}
			if (settings.get(2).getValBoolean()) {
				for (Object o : mc.theWorld.loadedEntityList) {
					if (o instanceof EntityPlayer && o != mc.thePlayer) {
						MobESPUtils.entityESPBox((Entity)o, 0);
					}
				}
			}
			if (settings.get(3).getValBoolean()) {
				for (Object o : mc.theWorld.loadedTileEntityList) {
					if (o instanceof TileEntityChest) {
						ChestESPUtils.blockESPBox(((TileEntityChest)o).getPos());
					}
				}
			}
		}
	}

}
