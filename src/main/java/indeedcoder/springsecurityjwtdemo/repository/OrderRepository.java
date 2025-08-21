package indeedcoder.springsecurityjwtdemo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import indeedcoder.springsecurityjwtdemo.entity.Order;

@Component
public class OrderRepository extends HashMap<Integer, Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307889524554154062L;

	public Order save(Order order) {
		if (!containsKey(order.getId())) {
			order.setId(size());
		}
		put(order.getId(), order);
		return order;
	}

	public Order[] saveAll(Order... orders) {
		for (Order order : orders) {
			if (!containsKey(order.getId())) {
				order.setId(size());
			}
			put(order.getId(), order);
		}
		return orders;
	}

	public Optional<Order> findById(int id) {
		return Optional.ofNullable(get(id));
	}

	public List<Order> findAll() {
		return values().stream().toList();
	}

	public boolean doesExistsById(int id) {
		return containsKey(id);
	}

}
