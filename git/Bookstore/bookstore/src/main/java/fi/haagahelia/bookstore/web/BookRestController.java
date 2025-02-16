package fi.haagahelia.bookstore.web;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private BookRepository repository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    // Hakee kaikki kirjat JSON-muodossa
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Hakee yhden kirjan ID:n perusteella JSON-muodossa
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = repository.findById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Lisää uuden kirjan JSONin kautta
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(repository.save(book));
    }

    // Päivittää kirjan tiedot
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return repository.findById(id).map(book -> {
            book.setNimi(bookDetails.getNimi());
            book.setKirjailija(bookDetails.getKirjailija());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setCategory(bookDetails.getCategory());
            return ResponseEntity.ok(repository.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Poistaa kirjan tietokannasta
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
