package fi.haagahelia.modelandview.domain;

public class Book {
    private Long id;
    private String nimi;
    private String kirjailija;

    private static Long counter = 0L;

    public Book(String nimi, String kirjailija) {
        this.id = ++counter;
        this.nimi = nimi;
        this.kirjailija = kirjailija;
    }

    public Long getId() {
        return id;
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