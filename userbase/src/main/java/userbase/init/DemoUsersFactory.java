package userbase.init;

import userbase.domain.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoUsersFactory {

	
	static List<User> defaultUsers() {
		List<User> users = new ArrayList<User>();
		users.add(addUser("admin","password", "admin", "admin"));
		users.add(addUser("susan","password", "Susan", "Jones"));
		users.add(addUser("bill","password", "Billy", "Smith"));
		users.add(addUser("ben","password", "Benjamin", "Thomas"));
        System.out.println(" users to be added "+users.size());
		return users;
    }
	
	
	static User addUser(String username,String password,String firstname,String surname) {
		User user = new User(username,password ,firstname,surname,
				Date.from(LocalDate.parse( "2019-01-10" ).plusDays( 10 ).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		return user;
	}



	
}
