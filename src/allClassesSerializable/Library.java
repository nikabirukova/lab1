package allClassesSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

    private final String title;
    private final List<Author> authors;
    private final List<Book> books;
    private final List<Reader> readers;

    public Library(String title) {
        this.title = title;
        authors = new ArrayList<>();
        books = new ArrayList<>();
        readers = new ArrayList<>();
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
}
