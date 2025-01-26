package fi.haagahelia.bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.bookstore.domain.Book;

@Controller
public class BookController {

    private List<Book> kirjat = new ArrayList<>();
    private AtomicLong seuraavaId = new AtomicLong(1);

    @GetMapping("/books")
    public String naytaKirjat(Model malli) {
        malli.addAttribute("kirjat", kirjat);
        return "kirjat";
    }

    @PostMapping("/books")
    public String lisaaKirja(@RequestParam String nimi, @RequestParam String kirjailija) {
        kirjat.add(new Book(seuraavaId.getAndIncrement(), nimi, kirjailija));
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String naytaMuokkauslomake(@PathVariable Long id, Model malli) {
        Book muokattava = etsiKirja(id);
        if (muokattava != null) {
            malli.addAttribute("kirja", muokattava);
            return "muokkaus";
        }
        return "redirect:/books";
    }

    @PostMapping("/books/edit/{id}")
    public String muokkaaKirjaa(@PathVariable Long id, @RequestParam String nimi, @RequestParam String kirjailija) {
        Book muokattava = etsiKirja(id);
        if (muokattava != null) {
            muokattava.setNimi(nimi);
            muokattava.setKirjailija(kirjailija);
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String poistaKirja(@PathVariable Long id) {
        kirjat.removeIf(k -> k.getId().equals(id));
        return "redirect:/books";
    }

    private Book etsiKirja(Long id) {
        return kirjat.stream().filter(k -> k.getId().equals(id)).findFirst().orElse(null);
    }
}