package allClassesSerializable;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Book book = (Book) object;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public String toString() {
        return  "{Title='" + getTitle() + '\'' +
                ", Author='" + getAuthor().toString() + '\'' +
                '}';
    }
}
