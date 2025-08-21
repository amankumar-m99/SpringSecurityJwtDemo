package indeedcoder.springsecurityjwtdemo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import indeedcoder.springsecurityjwtdemo.entity.Order;
import indeedcoder.springsecurityjwtdemo.entity.Person;
import indeedcoder.springsecurityjwtdemo.service.OrderService;
import indeedcoder.springsecurityjwtdemo.service.PersonService;

@Component
public class Driver implements CommandLineRunner{

	@Autowired
	private PersonService personService;

	@Autowired
	private OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Seeding data.....");
		for(int i=0; i<10; i++) {
			personService.create(new Person(null, "Person"+i));
			orderService.create(new Order(null, "Order"+i, new Date()));
		}
		System.out.println("Data Seeded.");
	}

}
