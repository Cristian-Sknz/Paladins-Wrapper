package me.skiincraft.api.paladins.enums;

/**
 * <h1>Language</h1>
 * <p>These are the languages available when requesting a request with language</p>
 */
public enum Language {

	English(1),
	German(2),
	French(3),
	Chinese(5),
	Spanish(7),
	Portuguese(10),
	Russian(11),
	Polish(12),
	Turkish(13);
	
	private int languagecode;

	Language(int languagecode) {
		this.setLanguagecode(languagecode);
	}

	public int getLanguagecode() {
		return languagecode;
	}

	private void setLanguagecode(int languagecode) {
		this.languagecode = languagecode;
	}
	
}
