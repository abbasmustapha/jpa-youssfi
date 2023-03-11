package com.mab.jpayoussfi;

import com.mab.jpayoussfi.entities.Role;
import com.mab.jpayoussfi.entities.User;
import com.mab.jpayoussfi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaYoussfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaYoussfiApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {

			User u = new User();
			u.setUserName("user1");
			u.setPassword("user1pwd");
			userService.addNewUser(u);

			User u2 = new User();
			u2.setUserName("user2");
			u2.setPassword("user2pwd");
			userService.addNewUser(u2);

			User u3 = new User();
			u3.setUserName("admin");
			u3.setPassword("adminpwd");
			userService.addNewUser(u3);

			Stream.of("STUDENT","USER","ADMIN").forEach(
					r->{
						Role r1 = new Role();
						r1.setRoleName(r);
						r1.setPassword("pwd_"+r.toString());
						r1.setDesc("Role " + r.toString());
						userService.addNewRole(r1);
					}
			);

			userService.addRoleToUser("STUDENT","user1");
			userService.addRoleToUser("USER","user2");
			userService.addRoleToUser("ADMIN","user2");

			try {
				User user = userService.authenticate("user2","user2pwd");
				System.out.println(user.getUserId());
				System.out.println(user.getUserName());
				System.out.println("Roles => ");
				user.getRoles().forEach(r -> {
					System.out.println("   - role :" +r.toString());
				});

			}
			catch (Exception e){
				e.printStackTrace();
			}

		};
	}

}
