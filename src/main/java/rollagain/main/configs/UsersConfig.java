package rollagain.main.configs;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rollagain.main.entities.Users;
import rollagain.main.repositories.UserRepository;


@Configuration
public class UsersConfig
{
    @Bean
    CommandLineRunner commandLineRunner(final UserRepository repository) {
        return args -> {
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
                "pepe",
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
    }
}
