package externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Externalizable {

    private String name;
    private String surname;
    private List<Book> takenBooks;

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

    public Reader() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Book> getTakenBooks() { return new ArrayList<>(takenBooks); }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(surname);

        out.writeInt(takenBooks.size());
        for (Book book : takenBooks) {
            out.writeObject(book);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        surname = (String) in.readObject();

        takenBooks = new ArrayList<>();
        int takenBooksSize = in.readInt();
        for (int i = 0; i < takenBooksSize; i++) {
            takenBooks.add((Book) in.readObject());
        }
    }
}
