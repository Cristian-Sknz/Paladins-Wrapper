package me.skiincraft.api.paladins.enums;

public enum Platform {
	PC(1, 5, 25, 28), PS4(9), Xbox(10), Switch(22),
	Discord(25), EpicGames(28), HiRez(1);

	private final int[] portalid;
	Platform(int... portalid){
		this.portalid = portalid;
	}
	
	public static Platform getPlatformByName(String name) {
		for (Platform a:values()) {
			if (a.name().equalsIgnoreCase(name)) {
				return a;	
			}
		}
		return Platform.PC;
	}
	
	public static Platform getPlatformByPortalId(int portalId) {
		for (Platform a:values()) {
			for (int portalIds : a.getPortalId()) {
				if (portalIds == portalId) {
					return a;	
				}
			}
		}
		return Platform.PC;
	}
	
	public int[] getPortalId() {
		return portalid;
	}
	
}
