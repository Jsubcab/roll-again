package rollagain.main.users;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig
{
    @Bean
    CommandLineRunner commandLineRunner(final UserRepository repository) {
        return args -> {
                final User Jorge = new User(
                    "Jorge",
                    "123456",
                    "Terrassa",
                    "082211",
                    123123213,
                    "pepe@phono.com"
            );

            final User Jose = new User(
                "Jose",
                "pepe",
                "Terrassa",
                "28035",
                989898989,
                "aloha@europa.com"
            );

            repository.saveAll(
                List.of(Jorge,Jose)
            );
        };
    }
}
