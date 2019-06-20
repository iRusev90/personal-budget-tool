package bg.infa.pbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import bg.infa.pbt.converter.AppConversionService;
import bg.infa.pbt.dto.UserDto;
import bg.infa.pbt.exception.UserNameTakenException;
import bg.infa.pbt.user.AppUser;

@Service
public class UserService {
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	private AppConversionService appConversionService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AppUser getCurrentUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return (AppUser) authentication.getPrincipal();
		}
		return null;
	}
	
	public UserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return appConversionService.convert(userDetails, UserDto.class);
		}
		return null;
	}
	
	public void createUser(String username, String password) {
		if (inMemoryUserDetailsManager.userExists(username)) {
			throw new UserNameTakenException(username);
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		inMemoryUserDetailsManager.createUser(new AppUser(username, encodedPassword));
	}
}
