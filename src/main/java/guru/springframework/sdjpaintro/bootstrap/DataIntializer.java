package guru.springframework.sdjpaintro.bootstrap;


import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Ci sono vari modi per implementare
// questa funzionalità all'avvio di SpringBoot
// Qui utilizzaimo l'interfaccia CommandLineRunner
// Importante definire il Bean altrimenti
// non viene iniettato nel context
@Component
public class DataIntializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataIntializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");

        System.out.println("Id: " + bookDDD.getId() );
        // Il valore del campo id sarà valorizzato
        // in automatico da SpringData come previsto
        // nella strategy del campo ID della classe Book
        // L'oggetto Book viene persistito in
        // un database H2 in memoria usato di default
        // da Spring Boot
        Book savedDDD = bookRepository.save(bookDDD);

        System.out.println("Id: " + savedDDD.getId() );

        Book bookSIA = new Book("Spring In Action", "234234", "Oriely");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });
    }
}
