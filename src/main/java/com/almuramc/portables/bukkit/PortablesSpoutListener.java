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
package com.almuramc.portables.bukkit;

import java.util.HashMap;
import java.util.UUID;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PortablesSpoutListener implements Listener {
	private static final HashMap<UUID, Boolean> SHIFT_HELD = new HashMap<UUID, Boolean>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		final SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
		if (SHIFT_HELD.containsKey(player.getUniqueId())) {
			if (!SHIFT_HELD.get(player.getUniqueId())) {
				return;
			}
			final Block block = event.getClickedBlock();
			if (!(block.getState() instanceof Sign)) {
				return;
			}
			if (!PortablesPlugin.getHooks().getPermissions().has(player.getWorld(), player.getName(), "portables.sign.use")) {
				player.sendNotification("Portables", "Cannot edit signs!", Material.LAVA_BUCKET);
				return;
			}
			if (PortablesPlugin.getHooks().isResidenceEnabled()) {
				final ClaimedResidence res = Residence.getResidenceManager().getByLoc(block.getLocation());
				if (res != null) {
					if (!res.getPermissions().playerHas(player.getName(), "build", true)) {
						return;
					}
				}
			}
			player.openSignEditGUI((Sign) block.getState());
		}
	}

	@EventHandler
	public void onKeyPressed(KeyPressedEvent event) {
		if (event.getScreenType() != ScreenType.GAME_SCREEN) {
			return;
		}
		final Keyboard key = event.getKey();
		final UUID ident = event.getPlayer().getUniqueId();
		switch (key) {
			case KEY_LSHIFT:
				SHIFT_HELD.put(ident, true);
		}
	}

	@EventHandler
	public void onKeyReleased(KeyReleasedEvent event) {
		if (event.getScreenType() != ScreenType.GAME_SCREEN) {
			return;
		}
		final Keyboard key = event.getKey();
		final UUID ident = event.getPlayer().getUniqueId();
		switch (key) {
			case KEY_LSHIFT:
				SHIFT_HELD.put(ident, false);
		}
	}
}
