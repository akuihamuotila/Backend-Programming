package fi.haagahelia.bookstore.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@Component
public class DataLoader {

    private final UserRepository käyttäjäTietovarasto;
    private final PasswordEncoder salasanaKoodaus;

    public DataLoader(UserRepository käyttäjäTietovarasto, PasswordEncoder salasanaKoodaus) {
        this.käyttäjäTietovarasto = käyttäjäTietovarasto;
        this.salasanaKoodaus = salasanaKoodaus;
    }

    @PostConstruct
    public void lataaKäyttäjät() {
        if (käyttäjäTietovarasto.count() == 0) {
            User käyttäjä = new User();
            käyttäjä.setKäyttäjätunnus("käyttäjä");
            käyttäjä.setSalasana(salasanaKoodaus.encode("salasana"));
            käyttäjä.setSähköposti("user@example.com");
            käyttäjä.setRooli("USER");
            käyttäjäTietovarasto.save(käyttäjä);

            User admin = new User();
            admin.setKäyttäjätunnus("admin");
            admin.setSalasana(salasanaKoodaus.encode("admin123"));
            admin.setSähköposti("admin@example.com");
            admin.setRooli("ADMIN");
            käyttäjäTietovarasto.save(admin);
        }
    }
}