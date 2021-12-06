package rollagain.main.configs;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rollagain.main.entities.Categories;
import rollagain.main.repositories.CategoriesRepository;


@Configuration
public class CategoriesConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCategories(final CategoriesRepository repository) {
        return args -> {

            final Categories Ameritrash = new Categories(
                "Ameritrash".toLowerCase()
            );

            final Categories Eurogame = new Categories(
                "Eurogame".toLowerCase()
            );

            final Categories Filler = new Categories(
                "Filler".toLowerCase()
            );

            repository.saveAll(
                List.of(Ameritrash, Eurogame, Filler)
            );
        };
    }
}
