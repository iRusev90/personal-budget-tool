package bg.infa.pbt.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import bg.infa.pbt.converter.AppConversionService;
import bg.infa.pbt.dto.UserDto;
import bg.infa.pbt.exception.UserNameTakenException;
import bg.infa.pbt.security.SecurityRole;

@Service
public class UserService {
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	private AppConversionService appConversionService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDetails getCurrentUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return (UserDetails) authentication.getPrincipal();
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
		
		inMemoryUserDetailsManager.createUser(new UserDetails() {
			private static final long serialVersionUID = -7996208214249729423L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
					return isEnabled();
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return isEnabled();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return isEnabled();
			}
			
			@Override
			public String getUsername() {
				return username;
			}
			
			@Override
			public String getPassword() {
				return passwordEncoder.encode(password);
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + SecurityRole.USER));
			}
		});
	}
}
