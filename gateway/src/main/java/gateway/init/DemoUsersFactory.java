package gateway.init;

import gateway.DemoPasswordEncoder;
import gateway.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoUsersFactory {

	@PersistenceContext
	private EntityManager entityManager;

	private final DemoPasswordEncoder passwordEncoder;

	public DemoUsersFactory(DemoPasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}

	public List<User> defaultUsers() {
		List<User> users = new ArrayList<User>();
		System.out.println(" Password encoder "+passwordEncoder.encode("password"));
		users.add(addUser("admin",passwordEncoder.encode("password"), "Alan", "Wollenstein"));
		users.add(addUser("susan",passwordEncoder.encode("password"), "Susan", "Jones"));
		users.add(addUser("bill",passwordEncoder.encode("password"), "Billy", "Smith"));
		users.add(addUser("ben",passwordEncoder.encode("password"), "Benjamin", "Thomas"));
        System.out.println(" users to be added "+users.size());
		return users;
    }
	
	
	public User addUser(String username,String password,String firstname,String surname) {
		User user = new User(username,password ,firstname,surname,
				Date.from(LocalDate.parse( "2019-01-10" ).plusDays( 10 ).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		return user;
	}



	
}
