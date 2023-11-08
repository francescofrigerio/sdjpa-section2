package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repository.BookRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Start  DataJpaTest Comment
// So this will bring up, bring up Hibernate, obviously.
// But the H2 in-memory database will get auto-configured for us,
// as well as the entities and spring data JPA.
// So all that gets brought up for us.
// So again, what that allows us to do is our repository will now be available in the,
// in the context
// again it is a spring boot Annotation is bringing up a minimal context
// for testing the spring data repository.
@DataJpaTest
// End DataJpaTest Comment
// Start TestMethodOrder Comment
// Another important thing, we need to make sure that our tester
// initializing in the proper order.
// So you might go, well, maybe they're really not running in the proper order.
// And JUnit 5 , what we can do is we can make sure that our tests are being forced
// in that order
// by using the @TestMethodOrder.
// by using the (methodorderer.OrderAnnotation.class)
// and then we can say here, this first test, we want to say @Order ( 1 )
// lets be first one to run
// this should be the second one to run.
// And that should be the second one.
// So now we are going to ensure that these two tests are running
// an order, so we're expecting this first
// test to run.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// End TestMethodOrder Comment
// Start ComponentScan Comment
//  the Data JPA Test has a very minimal configuration.
// so not configure initialize method
// But what we can do is bring into that configuration.
// with component scan (@ComponentScan) like so
// and we can say base packages and that is going to take
//
// And what we can do here is that data initializer
// What we do here to do is grab the package name.
// With that, I'm going to just copy that.
// And so now what this is telling us to do is to do a component scan
// of the bootstrap package and that
// package.
// And because it's the main CommandLneRunner,
// that run method is going to get executed.
//If in bootstrap, we had other spring configuration components,
// those would get picked up also.
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
// End ComponentScan Comment
public class SpringBootJpaTestSlice {

    // Chiediamo a SpringBoot di iniettarlo
    @Autowired
    BookRepository bookRepository;

    // Non facendo la commit non ha impatto
    // sugli altri test
    @Test
    void testJpaTestSpliceLessons27() {
        long countBefore = bookRepository.count();

        System.out.println(" countBefore " + countBefore);
        // persistiamo una nuova Entity nel database
        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();
        System.out.println(" countAfter " + countAfter);

        assertThat(countBefore).isLessThan(countAfter);
    }

    // start COMMENTO ALLA COMMIT
    // the default behavior of Spring Boot
    // on a test context is to run this transactional context
    // and roll it back and then run this in a transactional context and roll it back.
    // And typically, I'm going to say 99 percent of the time.
    // That's exactly the behavior that you want.
    // You don't want your test population data to other tests that typically
    // will cause bad things to happen.
    // And there's actually two different ways that we can address this.
    // One is to use the annotation @Rollback=false
    // And pass out of false value to it, So we can say roll back equals false.
    // And now if we run our test.
    // This is going to cause it not to roll back so that this behavior
    // that we want and again,
    // And again, 99 percent of the time you do want to roll back.
    // There are edge cases where this is handy to have. More going into
    // this so that you understand the transactional
    // context.
    // So rollback is handy.
    // And later versions of Spring Boot, or actually, I think this Spring Framework 5,
    // if I remember
    // correctly now, specifically Spring Boot. We can say commit.
    // So commit and roll back falls the same commits just really convenience annotation.
    //        Because if we come in here, we look at commit once to doing rollback equals false.
    // So but.
    // I think that's a little more cleaner.
    // And it also shows the intention saying that, Hey, I want this to go ahead and commit.
    // What's happening here is that we are actually committing that data
    // to the database in a transaction
    // in the first test and then the second test is looking
    // for that data be persistent and that is being
    // done through the commit transaction.
    // e' MOLTO RARO farlo perchè possiamo
    // alterare il contenuto del db
    // Potrebbe avere senso farlo per modificare
    // runtime una variabile che gestisce il livello di trace
    // stabilito da un campo sul DB
    // END COMMENTO ALLA COMMIT
    @Commit
    @Order(1)
    @Test
    void testJpaTestSpliceLessons28() {
        long countBefore = bookRepository.count();
        // Questo fallisce se lasciamo lo stesso codice
        // della lezione 28 perchè evidentemente
        // non eseguiamo il bootstrap del db
        // e la count da come risultato = 0
        // Dopo aver aggiunto l'esecuzione dell'inizializzazione
        // con l'annotazione COmponentScan modifico lo count da 0 a 2
        //assertThat(countBefore).isEqualTo(0);
        System.out.println(" countBefore " + countBefore);
        assertThat(countBefore).isEqualTo(2);

        // persistiamo una nuova Entity nel database
        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();
        System.out.println(" countAfter " + countAfter);

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        // Dopo aver aggiunto l'esecuzione dell'inizializzazione
        // con l'annotazione COmponentScan modifico lo count da 1 a 3
        //assertThat(countBefore).isEqualTo(1);
        assertThat(countBefore).isEqualTo(3);

    }




}
