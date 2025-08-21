package indeedcoder.springsecurityjwtdemo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private Integer id;
	private String name;
	private Date date;
}
