package Helpers;

public class Adapter {
	public String translateAccessLvl(String accslvl) {
		
		String accessLevel = null;
		
		switch(accslvl) {
		case("TV Lover"):
			accessLevel = "Box Set";
			break;
		case("Video Lovers"):
			accessLevel = "Movie";
			break;
		case("Music Lovers"):
			accessLevel = "Music+Live Concert Videos";
			break;
		}
		return accessLevel;
	}
}
