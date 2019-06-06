package bg.infa.pbt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import bg.infa.pbt.dto.UserDto;

@Component
public class UserDetailsToUserDtoConverter implements Converter<UserDetails, UserDto> {

	@Override
	public UserDto convert(UserDetails from) {
		UserDto userDto = new UserDto();
		userDto.setName(from.getUsername());
		return userDto;
	}

}
