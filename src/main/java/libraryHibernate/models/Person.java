package libraryHibernate.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_id;
    @Column(name = "fullname")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name size should be min-2 max-30 characters")
    private String fullName;
    @Column(name = "birthyear")
    @Min(value = 1900, message = "Рік народження повинен бути білбше ніж 1900")
    private int birthYear;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(){}

    public Person(String fullName,int birthYear){
        this.fullName=fullName;
        this.birthYear=birthYear;
    }

    public Person(Integer person_id, String fullName, int birthYear) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
