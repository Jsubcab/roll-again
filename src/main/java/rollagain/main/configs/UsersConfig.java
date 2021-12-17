package rollagain.main.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rollagain.main.entities.Users;
import rollagain.main.services.UsersService;


@Configuration
public class UsersConfig
{/*
    @Bean
    CommandLineRunner commandLineRunnerUsers(final UsersService userService) {

        return args -> {
            userService.addNewUser(new Users(
                "Jorge",
                "123456",
                "Terrassa",
                "082211",
                123123213,
                "pepe@phono.com"
            ));

            userService.addNewUser(new Users(
                "Jose",
                "12345",
                "Terrassa",
                "28035",
                989898989,
                "aloha@europa.com"
            ));

            userService.addNewUser(new Users(
                "Jesus",
                "123456",
                "Terrassa",
                "08200",
                937128391,
                "jesus@me.com"
            ));


                final Users Jorge = new Users(
                    "Jorge",
                    "123456",
                    "Terrassa",
                    "082211",
                    123123213,
                    "pepe@phono.com"
            );

            final Users Jose = new Users(
                "Jose",
                "12345",
                "Terrassa",
                "28035",
                989898989,
                "aloha@europa.com"
            );

            final Users Jesus = new Users(
                "Jesus",
                "123456",
                "Terrassa",
                "08200",
                937128391,
                "jesus@me.com"
            );

            repository.saveAll(
                List.of(Jorge,Jose, Jesus)
            );
        };
    }*/
}
