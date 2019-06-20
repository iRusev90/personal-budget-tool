package bg.infa.pbt.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infa.pbt.budget.BudgetCategory;
import bg.infa.pbt.budget.Payment;
import bg.infa.pbt.controller.param.PaymentParam;
import bg.infa.pbt.exception.ApplicationException;

@Service
public class PaymentService {
	@Autowired
	private UserService userService;
	@Autowired
	private BudgetCategoryService budgetCategoryService;

	public void createUserPayment(@Valid PaymentParam params) {
		BudgetCategory bc = budgetCategoryService.getBudetCategoryOrThrow(params.getBudgetCategoryName());
		Payment payment = new Payment(bc, params.getAmount(), params.getMonthNumber());
		userService.getCurrentAppUser().getPayments().add(payment);
	}

	public void updateUserPayment(int paymentId, @Valid PaymentParam params) {
		Payment payment = getPaymentOrThrowOnNull(paymentId);
		BudgetCategory bc = budgetCategoryService.getBudetCategoryOrThrow(params.getBudgetCategoryName());
		
		payment.setAmount(params.getAmount());
		payment.setBudgetCategory(bc);
		payment.setMonthNumber(params.getMonthNumber());
		
	}

	public void deleteUserPayment(int paymentId) {
		Payment payment = getPaymentOrThrowOnNull(paymentId);
		userService.getCurrentAppUser().getPayments().remove(payment);
		
	}
	
	private Payment getPaymentOrThrowOnNull(int paymentId) {
		Payment payment = getPaymentById(paymentId);
		if (payment == null) {
			throw new ApplicationException("no payment with id:" + paymentId);
		}
		
		return payment;
	}
	
	private Payment getPaymentById(int paymentId) {
		List<Payment> payments = userService.getCurrentAppUser().getPayments();
		Payment payment = payments.stream().filter(p -> {
			return p.getId() == paymentId;
		}).findFirst().get();
		
		return payment;
	}

}
