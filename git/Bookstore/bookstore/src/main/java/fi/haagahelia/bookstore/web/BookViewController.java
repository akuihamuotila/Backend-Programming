package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Controller
@RequestMapping("/books") // HTML-näkymä
public class BookViewController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Näyttää kirjalistan
    @GetMapping("")
    public String showBooks(Model model) {
        model.addAttribute("kirjat", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "kirjat";
    }

    // Lisää kirjan
    @PostMapping("")
    public String addBook(@RequestParam String nimi,
                          @RequestParam String kirjailija,
                          @RequestParam int publicationYear,
                          @RequestParam Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        bookRepository.save(new Book(nimi, kirjailija, publicationYear, category));
        return "redirect:/books";
    }

    // Näyttää muokkauslomakkeen
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("kirja", bookRepository.findById(id).orElse(null));
        model.addAttribute("categories", categoryRepository.findAll());
        return "muokkaus"; // Thymeleaf-näkymä
    }

    // Päivittää kirjan tiedot
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam String nimi,
                             @RequestParam String kirjailija,
                             @RequestParam int publicationYear,
                             @RequestParam Long categoryId) {

        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            book.setNimi(nimi);
            book.setKirjailija(kirjailija);
            book.setPublicationYear(publicationYear);
            book.setCategory(category);
            bookRepository.save(book);
        }
        return "redirect:/books";
    }

    // Poistaa kirjan
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}