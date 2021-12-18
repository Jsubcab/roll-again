package rollagain.main.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rollagain.main.entities.Permissions;
import rollagain.main.entities.Users;
import rollagain.main.services.PermissionsService;
import rollagain.main.services.UsersService;


@Configuration
public class UsersConfig
{

    @Bean
    CommandLineRunner commandLineRunnerUsers(final UsersService userService) {
        return args ->{
            userService.addNewUser(new Users(
                "Jorge",
                "123456",
                "Barcelona",
                "082211",
                123123213,
                "jorge@email.com"));

            userService.addNewUser(new Users(
                "Jose",
                "12345",
                "Madrid",
                "28035",
                989898989,
                "jose@email.com"
            ));
        };
    }
}
