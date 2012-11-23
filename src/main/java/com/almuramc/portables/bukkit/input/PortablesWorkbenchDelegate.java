/*
 * This file is part of Portables.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Portables is licensed under the Almura Development License.
 *
 * Portables is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Portables is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License. If not,
 * see <http://www.gnu.org/licenses/> for the GNU General Public License.
 */
package com.almuramc.portables.bukkit.input;

import com.almuramc.portables.bukkit.util.PortablesUtil;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

import org.bukkit.event.inventory.InventoryType;

public class PortablesWorkbenchDelegate implements BindingExecutionDelegate {
	@Override
	public void keyPressed(KeyBindingEvent keyBindingEvent) {
		SpoutPlayer player = keyBindingEvent.getPlayer();

		if (player.getOpenInventory().getType().equals(InventoryType.WORKBENCH)) {
			player.closeInventory();
			return;
		}

		if (!keyBindingEvent.getScreenType().equals(ScreenType.GAME_SCREEN)) {
			return;
		}

		PortablesUtil.openWorkbench(player);
	}

	@Override
	public void keyReleased(KeyBindingEvent keyBindingEvent) {
		//Do nothing
	}
}