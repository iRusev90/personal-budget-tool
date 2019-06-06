package bg.infa.pbt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infa.pbt.controller.param.UserRegistrationParams;
import bg.infa.pbt.dto.UserDto;
import bg.infa.pbt.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/current")
	public UserDto getCurrentUser() {
		return userService.getCurrentUser();
	}
	
	@PostMapping
	public void createUser(@Valid @RequestBody UserRegistrationParams userRegistrationParams) {
		userService.createUser(userRegistrationParams.getName(), userRegistrationParams.getPassword());
	}
}
