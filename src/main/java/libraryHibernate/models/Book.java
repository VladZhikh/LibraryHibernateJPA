package libraryHibernate.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "person_id")
    private Person owner;



    @Column(name = "book_name")
    @NotEmpty(message = "Name should not be empty")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    private String author;

    @Column(name = "book_year")
    private int bookYear;

    public Book() {
    }

    public Book(String title, String author, int bookYear) {
        this.title = title;
        this.author = author;
        this.bookYear = bookYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "owner="+owner+
                ", title='" + title + '\'' +
                '}';
    }
}
