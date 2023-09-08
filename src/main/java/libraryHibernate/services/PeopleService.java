package libraryHibernate.services;

import libraryHibernate.models.Book;
import libraryHibernate.models.Person;
import libraryHibernate.repositories.BookRepository;
import libraryHibernate.repositories.PeopleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BookRepository bookRepository;

    public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll(Sort.by("birthYear"));
    }
    public Person findOne(int id){
        Optional<Person> findPerson=peopleRepository.findById(id);
        return findPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setPerson_id(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }


}
