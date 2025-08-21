package indeedcoder.springsecurityjwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indeedcoder.springsecurityjwtdemo.entity.Order;
import indeedcoder.springsecurityjwtdemo.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {

	private OrderService service;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}

	@PostMapping("/order")
	public Order create(Order order) {
		return service.create(order);
	}

	@GetMapping("/order/{id}")
	public Order findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/orders")
	public List<Order> findAll() {
		return service.findAll();
	}

	@PutMapping("/order")
	public Order update(Order order) {
		return service.update(order);
	}
}
