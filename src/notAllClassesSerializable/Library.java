package notAllClassesSerializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

    private final String title;
    private transient List<Author> authors = new ArrayList<>();
    private transient List<Book> books = new ArrayList<>();
    private transient List<Reader> readers = new ArrayList<>();

    public Library(String title) {
        this.title = title;
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

    private void writeObject(ObjectOutputStream outputStream) {
        try {
            outputStream.defaultWriteObject();

            // authors
            outputStream.writeInt(authors.size());
            for (Author author : authors) {
                outputStream.writeObject(author.getName());
                outputStream.writeObject(author.getSurname());
            }

            // books
            outputStream.writeInt(books.size());
            for (Book book : books) {
                outputStream.writeObject(book.getTitle());
                outputStream.writeObject(book.getAuthor().getName());
                outputStream.writeObject(book.getAuthor().getSurname());
            }

            // readers
            outputStream.writeInt(readers.size());
            for (Reader reader : readers) {
                outputStream.writeObject(reader.getName());
                outputStream.writeObject(reader.getSurname());
                outputStream.writeInt(reader.getTakenBooks().size());
                for (Book book : reader.getTakenBooks()) {
                    outputStream.writeObject(book.getTitle());
                    outputStream.writeObject(book.getAuthor().getName());
                    outputStream.writeObject(book.getAuthor().getSurname());
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream inputStream) {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        readers = new ArrayList<>();

        try {
            inputStream.defaultReadObject();

            // authors
            int sizeAuthors = inputStream.readInt();
            for (int i = 0; i < sizeAuthors; i++) {
                authors.add(new Author((String) inputStream.readObject(), (String) inputStream.readObject()));
            }

            // books
            int sizeBooks = inputStream.readInt();
            books = extractArrayOfBooks(inputStream, sizeBooks);

            // readers
            int sizeReaders = inputStream.readInt();
            for (int i = 0; i < sizeReaders; i++) {
                String readerName = (String) inputStream.readObject();
                String readerSurname = (String) inputStream.readObject();

                int takenBooksSize = inputStream.readInt();
                List<Book> takenBooks = extractArrayOfBooks(inputStream, takenBooksSize);

                readers.add(new Reader(readerName, readerSurname, takenBooks));
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private List<Book> extractArrayOfBooks(ObjectInputStream inputStream, int takenBooksSize) throws IOException, ClassNotFoundException {
        List<Book> takenBooks = new ArrayList<>();
        for (int j = 0; j < takenBooksSize; j++) {
            String bookTitle = (String) inputStream.readObject();
            String authorName = (String) inputStream.readObject();
            String authorSurname = (String) inputStream.readObject();
            takenBooks.add(new Book(bookTitle, new Author(authorName, authorSurname)));
        }
        return takenBooks;
    }
}
