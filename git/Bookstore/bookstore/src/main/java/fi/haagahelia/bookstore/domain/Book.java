package fi.haagahelia.bookstore.domain;

public class Book {
    private Long id;
    private String nimi;
    private String kirjailija;

    public Book(Long id, String nimi, String kirjailija) {
        this.id = id;
        this.nimi = nimi;
        this.kirjailija = kirjailija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }
}