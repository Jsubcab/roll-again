package rollagain.main.configs;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rollagain.main.entities.Permissions;
import rollagain.main.entities.Users;
import rollagain.main.repositories.PermissionsRepository;
import rollagain.main.repositories.UserRepository;


@Configuration
public class PermissionsConfig
{
    @Bean
    CommandLineRunner commandLineRunnerPermissions(final PermissionsRepository repository) {
        return args -> {
            final Permissions Admin = new Permissions(
                "admin"
            );

            final Permissions User = new Permissions(
                "user"
            );

            repository.saveAll(
                List.of(Admin, User)
            );
        };
    }
}
