package allClassesSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Serializable {

    private String name;
    private String surname;
    private final List<Book> takenBooks;

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
        takenBooks = new ArrayList<>();
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
