package bg.infa.pbt.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bg.infa.pbt.budget.BudgetCategory;
import bg.infa.pbt.budget.MonthlyBudget;
import bg.infa.pbt.security.SecurityRole;

public class AppUser implements UserDetails {
	private static final long serialVersionUID = 4058782415187105703L;
	private boolean isEnabled;
	private String password;
	private String username;
	private  ArrayList<SimpleGrantedAuthority> grantedAuthorities;
	
	private String name;
	private String age;
	private String gender;
	private String interests;
	private ArrayList<BudgetCategory> budgetCategories;
	private ArrayList<MonthlyBudget> monthlyBudgets;

	public AppUser(String username, String password) {
		grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + SecurityRole.USER));
		
		this.isEnabled = true;
		this.password = password;
		this.username = username;
		this.budgetCategories = new ArrayList<>();
		this.budgetCategories.addAll(BudgetCategory.getDefaultBudgetCategories());
		this.monthlyBudgets = new ArrayList<>();
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<BudgetCategory> getBudgetCategories() {
		return budgetCategories;
	}

	public void setBudgetCategories(ArrayList<BudgetCategory> budgetCategories) {
		this.budgetCategories = budgetCategories;
	}
	
	public ArrayList<MonthlyBudget> getMonthlyBudgets() {
		return monthlyBudgets;
	}

	public void setMonthlyBudgets(ArrayList<MonthlyBudget> monthlyBudgets) {
		this.monthlyBudgets = monthlyBudgets;
	}
	
	// ====== User details

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
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
