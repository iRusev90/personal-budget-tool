package bg.infa.pbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import bg.infa.pbt.controller.param.UserUpdateParams;
import bg.infa.pbt.converter.AppConversionService;
import bg.infa.pbt.dto.UserDto;
import bg.infa.pbt.exception.UserNameTakenException;
import bg.infa.pbt.user.AppUser;
import bg.infa.pbt.user.AppUserStorage;

@Service
public class UserService {
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	private AppConversionService appConversionService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AppUser getCurrentAppUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return AppUserStorage.getAppUserByUsername(userDetails.getUsername());
		}
		return null;
	}
	
	public UserDto getCurrentUserDto() {
		AppUser appUser = getCurrentAppUser();
		if (appUser != null) {
			return appConversionService.convert(appUser, UserDto.class);
		}
		return null;
	}
	
	public void createUser(String username, String password) {
		if (inMemoryUserDetailsManager.userExists(username)) {
			throw new UserNameTakenException(username);
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		AppUser appUser = new AppUser(username, encodedPassword);
		inMemoryUserDetailsManager.createUser(appUser);
		AppUserStorage.addUser(appUser);
	}

	public void updateUser(UserUpdateParams updateParams) {
		AppUser appUser = getCurrentAppUser();
		appUser.setName(updateParams.getName());
		appUser.setAge(updateParams.getAge());
		appUser.setGender(updateParams.getGender());
		appUser.setInterests(updateParams.getInterests());
		
	}

	public void disableUser() {
		AppUser appUser = getCurrentAppUser();
		appUser.setEnabled(false);
		inMemoryUserDetailsManager.updateUser(appUser);
	}
}
