package myLMS;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {

    private static List<Book> bookInventory = new ArrayList<>();
    private static List<User> registeredUsers = new ArrayList<>();

    public static void addBook(Book book)
    {
        bookInventory.add(book);
    }

    public static void registeredUser(User user)
    {
        registeredUsers.add(user);
    }

    public static void displayAllBooks()
    {
        System.out.println("\n===== BOOK INVENTORY =====");

        for (Book book : bookInventory)
        {
            System.out.println(book);
        }
    }

    public static void displayRegisteredUsers()
    {
        System.out.println("\n===== REGISTERED USERS =====");

        for (User user : registeredUsers)
        {
            System.out.println(user);
        }
    }

    public static List<Book> searchBooks(String criteria)
    {
        List<Book> result = new ArrayList<>();

        for (Book book : bookInventory)
        {
            if (book.getTitle().toLowerCase().contains(criteria.toLowerCase())
                    ||
                book.getAuthor().toLowerCase().contains(criteria.toLowerCase()))
            {
                result.add(book);
            }
        }

        return result;
    }

    public static List<Book> searchBooks(String criteria, String type)
    {
        List<Book> result = new ArrayList<>();

        for (Book book : bookInventory)
        {
            boolean matchesCriteria =
                    book.getTitle().toLowerCase().contains(criteria.toLowerCase())
                    ||
                    book.getAuthor().toLowerCase().contains(criteria.toLowerCase());

            boolean matchesType =
                    book.getBookType().name().equalsIgnoreCase(type);

            if (matchesCriteria && matchesType)
            {
                result.add(book);
            }
        }

        return result;
    }



        public static void main(String[] args)
        {
            TextBook book1 =
                    new TextBook(
                            "Java Programming",
                            "James Gosling",
                            "Programming"
                    );

            TextBook book2 =
                    new TextBook(
                            "Data Structures",
                            "Robert Lafore",
                            "Computer Science"
                    );

            NovelBook book3 =
                    new NovelBook(
                            "Harry Potter",
                            "J.K. Rowling",
                            "Fantasy"
                    );

            NovelBook book4 =
                    new NovelBook(
                            "The Hobbit",
                            "J.R.R. Tolkien",
                            "Fantasy"
                    );

            addBook(book1);
            addBook(book2);
            addBook(book3);
            addBook(book4);

            Member member1 = new Member("Sam");
            Member member2 = new Member("Rahul");

            Librarian librarian1 = new Librarian("Admin");

            registeredUser(member1);
            registeredUser(member2);
            registeredUser(librarian1);

            displayAllBooks();
            displayRegisteredUsers();

            System.out.println("\n===== SEARCH RESULT =====");

            List<Book> searchResult1 = searchBooks("Java");

            for (Book book : searchResult1)
            {
                System.out.println(book);
            }

            System.out.println("\n===== SEARCH BY TYPE =====");

            List<Book> searchResult2 =
                    searchBooks("Harry", "NOVEL");

            for (Book book : searchResult2)
            {
                System.out.println(book);
            }

            System.out.println("\n===== LENDING =====");

            boolean success = book1.lend(member1);

            if (success)
            {
                System.out.println(
                        member1.getName()
                                + " borrowed "
                                + book1.getTitle()
                );
            }

            book1.lend(member2);

            book2.lend(member1);
            book2.lend(member1);

            System.out.println("\n===== RETURN =====");

            System.out.println(
                    "Before Return: "
                            + book1.isAvailable()
            );

            book1.returnBook(member1);

            System.out.println(
                    "After Return: "
                            + book1.isAvailable()
            );

            book1.lend(member2);

            System.out.println(
                    member2.getName()
                            + " borrowed "
                            + book1.getTitle()
            );

        }

}


//===== BOOK INVENTORY =====
//------------------------
//Title: Java Programming
//Author: James Gosling
//Type: TEXTBOOK
//Available: true
//Subject: Programming
//
//------------------------
//Title: Data Structures
//Author: Robert Lafore
//Type: TEXTBOOK
//Available: true
//Subject: Computer Science
//
//------------------------
//Title: Harry Potter
//Author: J.K. Rowling
//Type: NOVEL
//Available: true
//Genre: Fantasy
//
//------------------------
//Title: The Hobbit
//Author: J.R.R. Tolkien
//Type: NOVEL
//Available: true
//Genre: Fantasy
//
//
//===== REGISTERED USERS =====
//---------------------------------
//Name: Sam
//Type: Member
//Borrowed Books: 0
//
//---------------------------------
//Name: Rahul
//Type: Member
//Borrowed Books: 0
//
//---------------------------------
//Name: Admin
//Type: Librarian
//Borrowed Books: 0
//
//
//===== SEARCH RESULT =====
//------------------------
//Title: Java Programming
//Author: James Gosling
//Type: TEXTBOOK
//Available: true
//Subject: Programming
//
//
//===== SEARCH BY TYPE =====
//------------------------
//Title: Harry Potter
//Author: J.K. Rowling
//Type: NOVEL
//Available: true
//Genre: Fantasy
//
//
//===== LENDING =====
//Sam borrowed Java Programming
//Java Programming is already lent.
//Data Structures is already lent.
//
//===== RETURN =====
//Before Return: false
//Java Programming returned successfully.
//After Return: true
//Rahul borrowed Java Programming
//
