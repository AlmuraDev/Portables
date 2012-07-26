package com.almuramc.portables.bukkit.input;

import com.almuramc.portables.bukkit.util.PortablesUtil;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

import org.bukkit.event.inventory.InventoryType;

public class PortablesEnchantmentTableDelegate implements BindingExecutionDelegate {
	@Override
	public void keyPressed(KeyBindingEvent keyBindingEvent) {
		SpoutPlayer player = keyBindingEvent.getPlayer();

		if (player.getOpenInventory().getType().equals(InventoryType.ENCHANTING)) {
			player.closeInventory();
			return;
		}

		if (!keyBindingEvent.getScreenType().equals(ScreenType.GAME_SCREEN)) {
			return;
		}

		PortablesUtil.openEnchantmentTable(player);
	}

	@Override
	public void keyReleased(KeyBindingEvent keyBindingEvent) {
		//Do nothing
	}
}
