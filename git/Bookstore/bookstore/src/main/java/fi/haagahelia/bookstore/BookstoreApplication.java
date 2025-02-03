package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            // Lisätään esimerkkikirjoja tietokantaan, kun sovellus käynnistyy
            repository.save(new Book("Dune", "Frank Herbert", 1965));
            repository.save(new Book("The Silmarillion", "J.R.R. Tolkien", 1977));
            repository.save(new Book("Animal Farm", "George Orwell", 1945));
        };
    }
}