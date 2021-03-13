package me.skiincraft.api.paladins.objects.champion;

import java.util.Arrays;

public enum Role {

    Damage(0, "Damage"), Flank(1, "Flank"), Tank(2, "Tank"), Support(3, "Support");

    private int id;
    private String englishName;

    Role(int id, String englishName) {
        this.id = id;
        this.englishName = englishName;
    }

    public int getId() {
        return id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public static Role getRoleByName(String englishName){
        return Arrays.stream(values())
                .filter(role -> role.getEnglishName()
                        .toLowerCase().charAt(0) == englishName.toLowerCase().charAt(0))
                .findFirst().orElse(null);
    }

}
