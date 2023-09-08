package libraryHibernate.repositories;

import libraryHibernate.models.Book;
import libraryHibernate.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);
    List<Book> findByTitleStartingWith(String title);
}
