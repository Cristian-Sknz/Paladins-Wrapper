package me.skiincraft.api.paladins.objects.player;

import java.util.Arrays;

public enum Platform {

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
    Steam(5),

    /**
     * <p>PC platform containing all PC id portal</p>
     * <p>This platform does not exist in the Official API</p>
     */
    PC(Platform.HiRez, Platform.Steam, Platform.Discord, Platform.EpicGames),

    /**
     * <p>Console platform containing all Console id portal</p>
     * <p>This platform does not exist in the Official API</p>
     */
    Console(Platform.PS4, Platform.Xbox, Platform.Switch);

    private final int[] portalid;

    Platform(int... portalid) {
        this.portalid = portalid;
    }

    Platform(Platform... subplatform) {
        this.portalid = Arrays.stream(subplatform).map(Platform::getPortalId).flatMapToInt(Arrays::stream).toArray();
    }

    public static Platform getPlatformByName(String name) {
        return Arrays.stream(Platform.values())
                .filter(platform -> platform.name().equalsIgnoreCase(name))
                .findFirst().orElse(PC);
    }

    public boolean isSubplatform(Platform platform){
        return Arrays.asList(getSubplatform()).contains(platform);
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

    public Platform[] getSubplatform() {
        return Arrays.stream(values()).filter(platform -> Arrays.stream(platform.getPortalId()).anyMatch(id -> Arrays.stream(getPortalId()).anyMatch(id2 -> id2 == id))).toArray(Platform[]::new);
    }

    public boolean isConsole(){
        return Arrays.stream(Console.getPortalId()).anyMatch((value) -> Arrays.stream(portalid).anyMatch(value2 -> value2 == value));
    }

    public boolean isPC() {
        return Arrays.stream(PC.getPortalId()).anyMatch((value) -> Arrays.stream(portalid).anyMatch(value2 -> value2 == value));
    }

}
