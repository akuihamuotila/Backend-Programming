package fi.haagahelia.bookstore.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository käyttäjäTietovarasto;

    public CustomUserDetailsService(UserRepository käyttäjäTietovarasto) {
        this.käyttäjäTietovarasto = käyttäjäTietovarasto;
    }

    @Override
    public UserDetails loadUserByUsername(String käyttäjätunnus) throws UsernameNotFoundException {
        Optional<User> käyttäjä = käyttäjäTietovarasto.findByKäyttäjätunnus(käyttäjätunnus);
        if (käyttäjä.isEmpty()) {
            throw new UsernameNotFoundException("Käyttäjää ei löydy");
        }

        User user = käyttäjä.get();
        return org.springframework.security.core.userdetails.User.withUsername(user.getKäyttäjätunnus())
                .password(user.getSalasana())
                .roles(user.getRooli())
                .build();
    }
}