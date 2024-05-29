package shop.ojtprojectdemoshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.ojtprojectdemoshop.model.entity.Role;
import shop.ojtprojectdemoshop.model.entity.RoleName;
import shop.ojtprojectdemoshop.model.entity.User;
import shop.ojtprojectdemoshop.repository.IUserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OjtProjectDemoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjtProjectDemoShopApplication.class, args);
	}

//	    @Bean
//    public CommandLineRunner runner(PasswordEncoder passwordEncoder, IUserRepository userRepository){
//        return args -> {
//            Role admin = new Role(null, RoleName.ROLE_ADMIN)  ;
//            Role user = new Role(null, RoleName.ROLE_USER)  ;
//            Role manager = new Role(null, RoleName.ROLE_MANAGER)  ;
//            Set<Role> set = new HashSet<>();
//            set.add(admin);
//            set.add(user);
//            set.add(manager);
//
//           User roleAdmin = User.builder()
//                   .fullName("ADMIN")
//                   .username("admin")
//                   .password(passwordEncoder.encode("admin"))
//                   .roles(set)
//                   .status(true)
//                   .build();
//           userRepository.save(roleAdmin);
//        };
//    }

}
