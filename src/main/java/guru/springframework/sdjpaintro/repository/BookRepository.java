package guru.springframework.sdjpaintro.repository;

    /* ID Value dell'oggetto libro
    L'unica differenza è che il nostro ID
    è di tipo Long e non di tipo ID
    public interface Repository<T, ID> {
    }
     POi abbiamo le interfacce che ereditano
     da reposotiry e dichiarano i metodi a livello Crud
     L'annotaziobe @NoRepositoryBean serve a dire
     che l'oggetto non verrà raccolto come un normale Bean
     ma generato come oggetto java qualsiasi.
     Quindi springdatajpa non generano un implementazione
     per qualcosa che è annotato in questo modo
     @NoRepositoryBean
     public interface CrudRepository<T, ID> extends Repository<T, ID> {
         dichiarazione dei metodi  find update ecc
    */

    /*
    @NoRepositoryBean
    public interface PagingAndSortingRepository<T, ID> extends Repository<T, ID> {
        dichiarazione dei metodi findAll with Sort
        oppure findAll with Pageable
    }
    */

    /*
    @NoRepositoryBean
    public interface JpaRepository<T, ID>
    extends ListCrudRepository<T, ID>,
    ListPagingAndSortingRepository<T, ID>,
    QueryByExampleExecutor<T> {
     */

// Some people will simply use CrudRepository
// if they're doing something simple
// and then that will make
// their code portable.
// So I've actually done projects where I started off in MongoDB and went to JPA or vice versa.
// And if you're using one of these lower
// level more common repositories,
// refactoring does become easier
// and simpler to change out persistence
// providers.

import guru.springframework.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// Estendiamo l'interfaccia JpaRepository
// specificando al posto dei tipo generici
// Un oggetto di dominio Book e come chiave un Long
// Non serve annotare questa classe
// perchè erfeditando da SpringDataJpa
// sarà raccolto in automatico tramite
// l'annotazione  NoRepositoryBean di SpringDataJpa
// C'è anche un annotazione RepositoryDefinition
// nel caso si voglia definire un repository di mia proprieta
// senza utilizzare l'interfaccia forntita da SpringDataJpa
public interface BookRepository extends JpaRepository<Book, Long >  {

    //Repository
    // Tasto dx + generate per vedere
    // le dichiarazione di Repository e
    // le sue implementazioni
    // CrudRepository
    // PagingAndSortingRepository
    // JpaRepository

}
