package me.skiincraft.api.paladins.enums;

import java.util.Arrays;

public enum Platform {

	/**
	 * <p>PC platform containing all PC id portal</p>
	 * <p>This platform does not exist in the Official API</p>
	 */
	PC(1, 5, 25, 28),

	/**
	 * <p>PS4 platform for Playstation players</p>
	 */
	PS4(9),

	/**
	 * <p>Xbox platform for Microsoft Xbox players</p>
	 */
	Xbox(10),

	/**
	 * <p>Switch platform for Nintendo Switch players</p>
	 */
	Switch(22),

	/**
	 * <p>Discord platform for PC players playing through Discord</p>
	 */
	Discord(25),

	/**
	 * <p>EpicGames platform for PC players playing through Epic Games launcher</p>
	 */
	EpicGames(28),

	/**
	 * <p>HiRez platform for PC players playing through Hirez launcher</p>
	 */
	HiRez(1),

	/**
	 * <p>Stream platform for PC players playing through Steam launcher</p>
	 */
	Steam(5);

	private final int[] portalid;
	Platform(int... portalid){
		this.portalid = portalid;
	}
	
	public static Platform getPlatformByName(String name) {
		return Arrays.stream(Platform.values())
				.filter(platform -> platform.name().equalsIgnoreCase(name))
				.findFirst().orElse(PC);
	}
	
	public static Platform getPlatformByPortalId(int portalId) {
		return Arrays.stream(values())
				.filter(platform -> {
			for (int portal : platform.getPortalId()) {
				if (portal == portalId) {
					return true;
				}
			}
			return false;
		}).findFirst().orElse(PC);
	}
	
	public int[] getPortalId() {
		return portalid;
	}
	
}
