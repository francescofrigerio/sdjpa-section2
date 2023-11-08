package guru.springframework.sdjpaintro.domain;

// javax.persistence è deprecato
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Objects;

// Viene aggiunta una primary key
// e un attributo id
@Entity
public class Book {



    @Id
    // Attenzione che database
    // differenti gestiscono l'eccezzione
    // in modo differente
    // Qui vogliamo dire a SpringBoot
    // come gestire il campo attraverso
    // l'attributo strategy con diverse
    // opzioni : auto, identity, sequence, and table.
    // And you can use string values.
    // You can use numeric values like longs
    // and integers, and you can actually
    // use UUIDs as well.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;

    // The important part to remember is
    // if I have not defined a constructor.
    // Java, by default, is providing me
    // a no argument constructor.
    // But if I do provide a constructor like this,
    // then that default constructor
    // is not being provided anymore.
    // So I do need to have a no argument constructor
    // to keep hibernate happy because
    // that's what it's looking
    public Book() {
    }

    public Book(String title, String isbn, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // NELLA CREAZIONE AUTOMATIVA
    // DEI METODI equals and hashcode
    // CHE SOVRASCRIVONO QUELLI DI DEFAULT
    // ci sono diverse opzioni
    // getters and setters (qui no)
    // seleziono i campi da controllare
    // gli altri non selezionati possono
    // essere diversi
    // selezione valori nulli(qui no
    // perche' il campo id l'unico preso
    // in considerazione è valorizzato dal DB)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // cast dell'oggetto a Book
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
