package fi.haagahelia.bookstore.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kayttaja")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String käyttäjätunnus;

    @Column(nullable = false)
    private String salasana;

    @Column(nullable = false)
    private String sähköposti;

    @Column(nullable = false)
    private String rooli;
}