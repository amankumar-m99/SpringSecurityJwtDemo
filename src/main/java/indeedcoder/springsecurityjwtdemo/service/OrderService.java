package indeedcoder.springsecurityjwtdemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.Order;
import indeedcoder.springsecurityjwtdemo.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public Order create(Order order) {
		return repository.save(order);
	}

	public Order findById(int id) {
		return repository.findById(id).orElse(new Order(-1, "Not-found-by-id-"+id, new Date()));
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order update(Order order) {
		if(repository.doesExistsById(order.getId())) {
			return repository.save(order);
		}
		return new Order(-1, "Not-found", new Date());
	}
}
