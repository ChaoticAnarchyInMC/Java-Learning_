import java.util.*;

class Book implements Comparable<Book> {
    private String title;
    private String author;
    private String isbn;
    private int borrowCount;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowCount = 0;
    }

    public void incrementBorrowCount() {
        borrowCount++;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getBorrowCount() { return borrowCount; }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return this.isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

class Patron {
    private String name;
    private String email;
    private String phoneNumber;
    private String patronId;

    public Patron(String name, String email, String phoneNumber, String patronId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronId = patronId;
    }

    public String getName() { return name; }
    public String getPatronId() { return patronId; }
}

class LibraryManagementSystem {
    private HashSet<Book> bookCatalog = new HashSet<>();
    private TreeSet<Book> sortedBooks = new TreeSet<>();
    private ArrayList<Patron> patrons = new ArrayList<>();
    private LinkedList<String> reservationQueue = new LinkedList<>();
    private LinkedHashSet<Book> recentlyBorrowed = new LinkedHashSet<>();

    public void addBook(String title, String author, String isbn) {
        Book book = new Book(title, author, isbn);
        if (bookCatalog.add(book)) {
            sortedBooks.add(book);
            System.out.println("Book added: " + title);
        } else {
            System.out.println("Book already exists!");
        }
    }

    public void registerPatron(String name, String email, String phoneNumber) {
        patrons.add(new Patron(name, email, phoneNumber, "P" + (patrons.size() + 1)));
        System.out.println("Patron registered: " + name);
    }

    public void borrowBook(String patronId, String isbn) {
        for (Book book : bookCatalog) {
            if (book.getIsbn().equals(isbn)) {
                book.incrementBorrowCount();
                recentlyBorrowed.add(book);
                System.out.println("Book borrowed: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook(String isbn) {
        for (Book book : recentlyBorrowed) {
            if (book.getIsbn().equals(isbn)) {
                recentlyBorrowed.remove(book);
                System.out.println("Book returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("This book was not borrowed recently.");
    }

    public void searchBookByTitle(String title) {
        for (Book book : sortedBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void displayPopularBooks() {
        sortedBooks.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()))
                .limit(5)
                .forEach(book -> System.out.println(book.getTitle() + " - Borrowed " + book.getBorrowCount() + " times"));
    }
}

class LibraryInterface {
    private LibraryManagementSystem lms = new LibraryManagementSystem();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Library Management System");
        System.out.println("Enter commands (type 'HELP' for a list of commands or 'EXIT' to quit)");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Thank you for using the Library Management System. Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("HELP")) {
                displayHelp();
            } else {
                processCommand(input);
            }
        }
        scanner.close();
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("  ADD BOOK \"Title\" \"Author\" ISBN");
        System.out.println("  REGISTER PATRON \"Name\" \"Email\" PhoneNumber");
        System.out.println("  BORROW PatronID ISBN");
        System.out.println("  RETURN BOOK ISBN");
        System.out.println("  SEARCH TITLE \"Title\"");
        System.out.println("  POPULAR BOOKS");
    }
}

public class Main {
    public static void main(String[] args) {
        LibraryInterface libraryInterface = new LibraryInterface();
        libraryInterface.start();
    }
}
