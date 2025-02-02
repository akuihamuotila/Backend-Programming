package fi.haagahelia.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books") // Hakee kaikki kirjat ja näyttää ne sivulla
    public String naytaKirjat(Model malli) {
        malli.addAttribute("kirjat", repository.findAll());
        return "kirjat";
    }

    @PostMapping("/books") // Lisää uuden kirjan tietokantaan
    public String lisaaKirja(@RequestParam String nimi, @RequestParam String kirjailija, @RequestParam int publicationYear) {
        repository.save(new Book(nimi, kirjailija, publicationYear));
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}") // Näyttää muokkauslomakkeen valitulle kirjalle
    public String naytaMuokkauslomake(@PathVariable Long id, Model malli) {
        malli.addAttribute("kirja", repository.findById(id).orElse(null));
        return "muokkaus";
    }

    @PostMapping("/books/edit/{id}") // Päivittää kirjan tiedot
    public String muokkaaKirjaa(@PathVariable Long id, @RequestParam String nimi, @RequestParam String kirjailija, @RequestParam int publicationYear) {
        Book muokattava = repository.findById(id).orElse(null);
        if (muokattava != null) {
            muokattava.setNimi(nimi);
            muokattava.setKirjailija(kirjailija);
            muokattava.setPublicationYear(publicationYear);
            repository.save(muokattava);
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}") // Poistaa kirjan tietokannasta
    public String poistaKirja(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/books";
    }
}