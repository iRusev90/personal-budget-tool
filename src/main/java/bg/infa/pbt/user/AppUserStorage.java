package bg.infa.pbt.user;

import java.util.HashMap;

public class AppUserStorage {
	private static HashMap<String, AppUser> usersByName = new HashMap<>();
	
	
	public static void addUser(AppUser appUser) {
		usersByName.put(appUser.getUsername(), appUser);
	}
	
	public static AppUser getAppUserByUsername(String username) {
		return usersByName.get(username);
	}
}
