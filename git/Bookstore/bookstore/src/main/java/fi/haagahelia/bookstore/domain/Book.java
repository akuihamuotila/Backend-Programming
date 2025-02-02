package fi.haagahelia.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimi;
    private String kirjailija;
    private int publicationYear; // Ei "year"

    public Book() {}

    public Book(String nimi, String kirjailija, int publicationYear) {
        this.nimi = nimi;
        this.kirjailija = kirjailija;
        this.publicationYear = publicationYear;
    }

    // Getterit ja setterit
    public Long getId() { return id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }

    public String getKirjailija() { return kirjailija; }
    public void setKirjailija(String kirjailija) { this.kirjailija = kirjailija; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
}