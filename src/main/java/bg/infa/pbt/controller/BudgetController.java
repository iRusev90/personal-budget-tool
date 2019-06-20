package bg.infa.pbt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infa.pbt.controller.param.BudgetParams;
import bg.infa.pbt.service.BudgetService;

@RestController
@RequestMapping("api/budget")
public class BudgetController {
	@Autowired
	private BudgetService budgetService;

	@PostMapping
	public void createUserBudget(@Valid @RequestBody BudgetParams params) {
		budgetService.createUserBudget(params);
	}
	
	@PutMapping("/{id}")
	public void editUserBudget(@PathVariable("id") int budgetId, @Valid @RequestBody BudgetParams params) {
		budgetService.editUserBudget(budgetId, params);
	}
	
	@PostMapping("/{id}/clone")
	public void cloneBudget(@PathVariable("id") int budgetId) {
		budgetService.cloneUserBudget(budgetId);
	}
}
