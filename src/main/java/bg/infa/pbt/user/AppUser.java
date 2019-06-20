package bg.infa.pbt.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bg.infa.pbt.security.SecurityRole;

public class AppUser implements UserDetails {
	private static final long serialVersionUID = 4058782415187105703L;
	private boolean isEnabled;
	private String password;
	private String username;
	private  ArrayList<SimpleGrantedAuthority> grantedAuthorities;
	
	public AppUser(String username, String password) {
		grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + SecurityRole.USER));
		
		this.isEnabled = true;
		this.password = password;
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isEnabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnabled;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

}
