package pl.storex.storex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import pl.storex.storex.model.User;
import pl.storex.storex.service.UserRepository;

public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(User.builder()
                            .name("Seba")
                            .email("test@test.pl")
                            .password("123")
                    .build()));
            logger.info("Preloading " + repository.save(User.builder()
                            .name("test")
                            .password("123")
                            .email("test@test.pl")
                    .build()));
        };
    }
}
