package fi.haagahelia.bookstore.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.käyttäjätunnus = ?1")
    Optional<User> findByKäyttäjätunnus(String käyttäjätunnus);
}