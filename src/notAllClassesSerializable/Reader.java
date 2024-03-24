package notAllClassesSerializable;

import java.util.ArrayList;
import java.util.List;

public class Reader {

    private String name;
    private String surname;
    private transient final List<Book> takenBooks;

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
        takenBooks = new ArrayList<>();
    }

    public Reader(String name, String surname, List<Book> takenBooks) {
        this.name = name;
        this.surname = surname;
        this.takenBooks = new ArrayList<>(takenBooks);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Book> getTakenBooks() { return new ArrayList<>(takenBooks); }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void takeBook(Book book) {
        takenBooks.add(book);
    }

    @Override
    public String toString() {
        return  "{Name='" + getName() + '\'' +
                ", Surname='" + getSurname() + '\'' +
                ", Taken Books=" + getTakenBooks().toString() +
                '}';
    }
}
