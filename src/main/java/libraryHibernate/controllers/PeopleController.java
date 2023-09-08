package libraryHibernate.controllers;

import libraryHibernate.dao.PersonDao;
import libraryHibernate.models.Book;
import libraryHibernate.models.Person;
import libraryHibernate.services.BookService;
import libraryHibernate.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PeopleController {
    private final PersonDao personDao;
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PersonDao personDao, PeopleService peopleService, BookService bookService) {
        this.personDao = personDao;
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("people/new")
    public String newPerson(String name, String year) throws SQLException {
        int birthYear;
        try {
            birthYear = Integer.parseInt(year);
        } catch (Exception e) {
            birthYear = 0;
        }
        Person person = new Person(name, birthYear);
        if (person.getFullName() == null || person.getFullName().isEmpty())
            return "people/new";
        else {
            peopleService.save(person);
            return "success";
        }
    }

    @GetMapping("people/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        //model.addAttribute("person", personDao.show(id));
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }
    //"people/{id}"
    @PatchMapping("people/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person)
    {
        //System.out.println(id+", "+person.getFullName()+", "+person.getBirthYear());
        //personDao.update(id, person);
        peopleService.update(id,person);
        return "success";
    }

    @DeleteMapping("people/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("people/{id}/find")
    public String findBooks(@PathVariable("id") int id,Model model){
        Person person = peopleService.findOne(id);
        List<Book> bookList=bookService.findByOwner(person);
        model.addAttribute("book", bookList);
        model.addAttribute("person",person);
        return "people/find";
    }
}
