package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            // Lisätään kategoriat
            Category scifi = new Category("Scifi");
            Category fantasy = new Category("Fantasy");
            Category satire = new Category("Satire");

            categoryRepository.save(scifi);
            categoryRepository.save(fantasy);
            categoryRepository.save(satire);

            // Lisätään esimerkkikirjat ja asetetaan niille kategoriat
            bookRepository.save(new Book("Dune", "Frank Herbert", 1965, scifi));
            bookRepository.save(new Book("The Silmarillion", "J.R.R. Tolkien", 1977, fantasy));
            bookRepository.save(new Book("Animal Farm", "George Orwell", 1945, satire));
        };
    }
}