package pl.storex.storex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.storex.storex.user.model.UserDTO;
import pl.storex.storex.user.service.UserService;

public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase(UserService repository) {
        logger.info("Preloading {}", repository.save(UserDTO.builder()
                .name("Seba")
                .email("admin@test.pl")
                .password("123")
                .build()));
        logger.info("Preloading {}", repository.save(UserDTO.builder()
                .name("test")
                .password("123")
                .email("test@test.pl")
                .build()));
    }

}
