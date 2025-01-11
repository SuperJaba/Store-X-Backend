package pl.storex.storex;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.storex.storex.user.model.User;
import pl.storex.storex.user.model.UserDTO;
import pl.storex.storex.user.service.UserService;

@Component
@RequiredArgsConstructor
@Profile("local")
public class LoadDatabase implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
    private final UserService repository;
    private final UserService userService;

    public void initDatabase() {
        logger.info("Preloading {}", repository.save(UserDTO.builder()
                .name("Seba")
                .email("admin@test.pl")
                .password("123")
                .build()));
        logger.info("Preloading {}", repository.save(UserDTO.builder()
                .name("test")
                .password("123")
                .email("test2@test.pl")
                .build()));

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initDatabase();
    }
}
