package me.skiincraft.api.paladins.objects.player;

import me.skiincraft.api.paladins.objects.miscellany.Language;

import java.util.Locale;

/**
 * <h1>Region</h1>
 * <p>Is the region of the game servers</p>
 */
public enum Region {
	
	Brazil(new Locale("pt", "BR"), Language.Portuguese), Asia(null, null), Australia(null, Language.English), Europe(null, Language.English), North_America(Locale.US, Language.English);

	private Locale locale;
	private Language language;

	Region(Locale locale, Language language){
		this.locale = locale;
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}
	public Locale getLocale() {
		return locale;
	}
}
