package indeedcoder.springsecurityjwtdemo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import indeedcoder.springsecurityjwtdemo.entity.Order;
import indeedcoder.springsecurityjwtdemo.entity.User;
import indeedcoder.springsecurityjwtdemo.service.OrderService;
import indeedcoder.springsecurityjwtdemo.service.UserService;

@Component
public class Driver implements CommandLineRunner{

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Seeding data.....");
		for(int i=0; i<10; i++) {
			List<String> roles = null;
			if(i %2 == 0) {
				roles = List.of("ADMIN");
			}
			else {
				roles = List.of("USER");
			}
			userService.create(new User(null, "User"+i, "User"+i, roles));
			orderService.create(new Order(null, "Order"+i, new Date()));
		}
		System.out.println("Data Seeded.");
	}

}
