package com.almuramc.portables.common;

import java.util.HashMap;
import java.util.Map;

public enum Portables {
	FURNACE("furance"),
	WORKBENCH("workbench"),
	BREWING_STAND("brewingstand");
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
