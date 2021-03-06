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
package com.almuramc.portables.common;

import java.util.HashMap;
import java.util.Map;

public enum Portables {
	FURNACE("furance"),
	WORKBENCH("workbench"),
	BREWING_STAND("brewingstand"),
	SIGN("sign"),
	ENCHANTMENT_TABLE("enchant");
	private final String identifier;
	private static final Map<String, Portables> stringMap = new HashMap<String, Portables>();

	static {
		for (Portables portable : Portables.values()) {
			stringMap.put(portable.getIdentifier(), portable);
		}
	}

	private Portables(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public static Portables get(String identifier) {
		return stringMap.get(identifier);
	}
}
