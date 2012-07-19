/*
 * This file is part of Workbench.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Workbench is licensed under the Almura Development License.
 *
 * Workbench is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License.
 *
 * Workbench is distributed in the hope that it will be useful,
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
package com.almuramc.workbench.bukkit.command;

import java.util.logging.Level;

import com.almuramc.workbench.bukkit.WorkbenchPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommands implements CommandExecutor {
	private final WorkbenchPlugin plugin;

	public WorkbenchCommands(WorkbenchPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getName().equalsIgnoreCase("workbench")) {
			if (!(commandSender instanceof Player)) {
				plugin.getLogger().log(Level.SEVERE, "");
				return true;
			}
			Player player = (Player) commandSender;
			if (player.hasPermission("workbench.use")) {
				player.openWorkbench(null, true);
				return true;
			}
		}
		return false;
	}
}
