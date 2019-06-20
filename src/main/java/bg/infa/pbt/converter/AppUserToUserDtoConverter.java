package bg.infa.pbt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bg.infa.pbt.dto.UserDto;
import bg.infa.pbt.user.AppUser;

@Component
public class AppUserToUserDtoConverter implements Converter<AppUser, UserDto> {

	@Override
	public UserDto convert(AppUser from) {
		UserDto userDto = new UserDto();
		userDto.setName(from.getName());
		userDto.setUsername(from.getUsername());
		userDto.setAge(from.getAge());
		userDto.setGender(from.getGender());
		userDto.setInterests(from.getInterests());
		userDto.setBudgetCategories(from.getBudgetCategories());
		userDto.setBudgets(from.getMonthlyBudgets());
		userDto.setPayments(from.getPayments());
		return userDto;
	}

}
