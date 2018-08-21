package solshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import solshop.user.model.UserDTO;
import solshop.user.service.UserService;

@SpringBootApplication
public class MainSpringBootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(MainSpringBootApplication.class);
    }

    public static void main(String[]args){
        SpringApplication.run(MainSpringBootApplication.class,args);
    }


    @Bean
    CommandLineRunner commandLineRunner (UserService us, PasswordEncoder encoder) {
        return args -> {

            us.saveAdmin(new UserDTO("admin@gmail.com","admin","admin"));
            us.saveUser(new UserDTO("user@gmail.com","user","user"));
        };
    }




}