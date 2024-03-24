package externalizable;

import externalizable.FileOperations.FileExternalizationReader;
import externalizable.FileOperations.FileExternalizationWriter;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Library library = new Library("Nika Biriukova Central Library");

        while (true) {
            System.out.print("1. Add author\n" +
                    "2. Delete author\n" +
                    "3. Add book\n" +
                    "4. Delete book\n" +
                    "5. Add reader\n" +
                    "6. Delete reader\n" +
                    "7. Give book to user\n" +
                    "8. Information about library\n" +
                    "9. Save\n" +
                    "a. Load\n" +
                    "0. Exit\n");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Author author = createAuthor();
                    library.addAuthor(author);
                    System.out.println("Author was added!");
                    break;
                case "2":
                    System.out.print("Enter the index of removable author: ");
                    int authorRemoveIndex = Integer.parseInt(scanner.nextLine());
                    if (authorRemoveIndex >= 0 && authorRemoveIndex < library.getAuthorsCount()) {
                        library.removeAuthor(authorRemoveIndex);
                        System.out.println("Author was deleted!");
                    } else {
                        System.out.println("Check correctness of author index!");
                    }
                    break;
                case "3":
                    System.out.print("Enter the index of author: ");
                    int authorAddBookIndex = Integer.parseInt(scanner.nextLine());
                    Author authorAddBook = library.getAuthor(authorAddBookIndex);

                    if (authorAddBook == null) {
                        break;
                    }

                    Book book = createBook(authorAddBook);
                    if (!library.bookExists(book)) {
                        library.addBook(book);
                        System.out.println("Book was added!");
                    } else {
                        System.out.println("The same book is already in the library!");
                    }
                    break;
                case "4":
                    System.out.print("Enter the index of removable book: ");
                    int bookIndex = Integer.parseInt(scanner.nextLine());
                    if (bookIndex >= 0 && bookIndex < library.getBooksCount() ) {
                        library.removeBook(bookIndex);
                        System.out.println("Book was removed!");
                    } else {
                        System.out.println("Check correctness of book index!");
                    }
                    break;
                case "5":
                    Reader reader = createReader();
                    library.addReader(reader);
                    System.out.println("Reader was added!");
                    break;
                case "6":
                    System.out.print("Enter the index of removable reader: ");
                    int readerIndex = Integer.parseInt(scanner.nextLine());
                    if (readerIndex >= 0 && readerIndex < library.getReadersCount() ) {
                        library.removeReader(readerIndex);
                        System.out.println("Reader was removed!");
                    } else {
                        System.out.println("Check correctness of reader index!");
                    }
                    break;
                case "7":
                    System.out.print("Enter the index of book: ");
                    Book requestedBook = library.getBook(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Enter the index of reader: ");
                    Reader requiredReader = library.getReader(Integer.parseInt(scanner.nextLine()));
                    if (requestedBook != null && requiredReader != null) {
                        library.giveBookToReader(requestedBook, requiredReader);
                        System.out.println("Book was gave!");
                    } else {
                        System.out.println("Check correctness of indexes!");
                    }
                    break;
                case "8":
                    System.out.println("\n" + library.toString() + "\n");
                    break;
                case "9":
                    FileExternalizationWriter.objectToFile(library, "src\\externalizable\\Files\\serializableLibrary.txt");
                    break;
                case "a":
                    library = (Library) FileExternalizationReader.objectFromFile("src\\externalizable\\Files\\serializableLibrary.txt");
                    break;
                case "0":
                    return;
            }
        }

    }

    private static Author createAuthor(){
        String name;
        String surname;

        System.out.print("Enter author name: ");
        name = scanner.nextLine();
        System.out.print("Enter author surname: ");
        surname = scanner.nextLine();

        return new Author(name, surname);
    }

    private static Book createBook(Author author) {
        String title;

        System.out.print("Enter title: ");
        title = scanner.nextLine();

        return new Book(title, author);
    }

    private static Reader createReader() {
        String name;
        String surname;

        System.out.print("Enter name: ");
        name = scanner.nextLine();
        System.out.print("Enter surname: ");
        surname = scanner.nextLine();

        return new Reader(name, surname);
    }
}
