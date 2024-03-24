package externalizable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Externalizable {

    private String title;
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();

    public Library(String title) {
        this.title = title;
    }

    public Library() {
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Author getAuthor(int index) {
        if (index >= 0 && index < authors.size()) {
            return authors.get(index);
        } else {
            return null;
        }
    }

    public int getAuthorsCount() {
        return authors.size();
    }

    public Author removeAuthor(int index) {
        return authors.remove(index);
    }

    public boolean bookExists(Book book) {
        for (Book b : books) {
            if (b.equals(book)) {
                return true;
            }
        }
        return false;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    public Book removeBook(int index) {
        return books.remove(index);
    }

    public int getBooksCount() {
        return books.size();
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public Reader getReader(int index) {
        if (index >= 0 && index < readers.size()) {
            return readers.get(index);
        } else {
            return null;
        }
    }

    public Reader removeReader(int index) {
        return readers.remove(index);
    }

    public int getReadersCount() {
        return readers.size();
    }

    public void giveBookToReader(Book requestedBook, Reader requiredReader) {
        requiredReader.takeBook(requestedBook);
    }

    @Override
    public String toString() {
        return  "Library name: '" + title + '\'' +
                "\nAuthors=" + authors.toString() +
                "\nBooks=" + books.toString() +
                "\nReaders=" + readers.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);

        out.writeInt(authors.size());
        for (Author author : authors) {
            out.writeObject(author);
        }

        out.writeInt(books.size());
        for (Book book : books) {
            out.writeObject(book);
        }

        out.writeInt(readers.size());
        for (Reader reader : readers) {
            out.writeObject(reader);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();

        authors = new ArrayList<>();
        int authorsSize = in.readInt();
        for (int i = 0; i < authorsSize; i++) {
            authors.add((Author) in.readObject());
        }

        books = new ArrayList<>();
        int booksSize = in.readInt();
        for (int i = 0; i < booksSize; i++) {
            books.add((Book) in.readObject());
        }

        readers = new ArrayList<>();
        int readersSize = in.readInt();
        for (int i = 0; i < readersSize; i++) {
            readers.add((Reader) in.readObject());
        }
    }
}
