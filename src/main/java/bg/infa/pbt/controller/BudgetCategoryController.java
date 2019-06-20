package bg.infa.pbt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infa.pbt.controller.param.BudgetCategoryParams;
import bg.infa.pbt.controller.param.SimpleBooleanParam;
import bg.infa.pbt.service.BudgetCategoryService;

@RestController
@RequestMapping("api/category")
public class BudgetCategoryController {
	@Autowired
	private BudgetCategoryService budgetCategoryService;
	
	@PostMapping
	public void addBudgetCategory(@Valid @RequestBody BudgetCategoryParams params) {
		budgetCategoryService.addUserCategory(params);
	}
	
	@PutMapping
	public void updateBudgetCategory(@Valid @RequestBody BudgetCategoryParams params) {
		budgetCategoryService.updateUserCategory(params);
	}
	
	@DeleteMapping("/{name}")
	public void deleteBudgetCategory(@PathVariable("name") String budgetCategoryName) {
		budgetCategoryService.deleteUserCategory(budgetCategoryName);
	}
	
	@PutMapping("/{name}/enable")
	public void setEnabledState(@PathVariable("name") String budgetCategoryName, @Valid @RequestBody SimpleBooleanParam params) {
		budgetCategoryService.setEnableToUserBudgetCategory(budgetCategoryName, params.getIsTrue());
	}
	
	
}
