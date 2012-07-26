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
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License.
 *
 * Portables is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * the GNU Lesser Public License (for classes that fulfill the exception)
 * and the Almura Development License along with this program. If not, see
 * <http://www.gnu.org/licenses/> for the GNU General Public License and
 * the GNU Lesser Public License.
 */
package com.almuramc.portables.bukkit.command;

import java.util.logging.Level;

import com.almuramc.portables.bukkit.PortablesPlugin;
import com.almuramc.portables.bukkit.util.PortablesUtil;
import com.almuramc.portables.common.Portables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortablesCommands implements CommandExecutor {
	private PortablesPlugin plugin;

	public PortablesCommands(PortablesPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getName().equalsIgnoreCase("portables")) {
			if (strings.length == 0) {
				return false;
			}
			//handle reload first
			if (strings[0].equalsIgnoreCase("reload")) {
				PortablesPlugin.getCached().reload();
				commandSender.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.WHITE + " Reloaded!");
				return true;
			}
			Player player = commandSender instanceof Player ? (Player) commandSender : null;
			//Gotta be a player to open a portable
			if (player == null) {
				plugin.getLogger().log(Level.SEVERE, "Must be in-game to open a portable!");
				return true;
			}
			Portables portable = Portables.get(strings[0].toLowerCase());
			if (portable == null) {
				commandSender.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.WHITE + " Please specify which Portable item to open!");
				return true;
			}
			switch (portable) {
				case BREWING_STAND:
					break;
				case ENCHANTMENT_TABLE:
					PortablesUtil.openEnchantmentTable(player);
					break;
				case FURNACE:
					break;
				case WORKBENCH:
					PortablesUtil.openWorkbench(player);
					break;
				default:
					break;
			}
			return true;
		}
		return false;
	}
}
