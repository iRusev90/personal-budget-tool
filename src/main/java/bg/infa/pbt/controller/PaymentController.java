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

import bg.infa.pbt.controller.param.PaymentParam;
import bg.infa.pbt.service.PaymentService;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public void createUserPayment(@Valid @RequestBody PaymentParam params) {
		paymentService.createUserPayment(params);
	}
	
	@PutMapping("/{id}")
	public void updateUserPayment(@PathVariable("id") int paymentId, @Valid @RequestBody PaymentParam params) {
		paymentService.updateUserPayment(paymentId, params);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserPayment(@PathVariable("id") int paymentId) {
		paymentService.deleteUserPayment(paymentId);
	}
}
