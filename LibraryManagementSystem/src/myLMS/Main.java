//package myLMS;
//
//import java.util.List;
//
//
//public class Main
//{
//    public static void main(String[] args)
//    {
//        TextBook book1 =
//                new TextBook(
//                        "Java Programming",
//                        "James Gosling",
//                        "Programming"
//                );
//
//        TextBook book2 =
//                new TextBook(
//                        "Data Structures",
//                        "Robert Lafore",
//                        "Computer Science"
//                );
//
//        NovelBook book3 =
//                new NovelBook(
//                        "Harry Potter",
//                        "J.K. Rowling",
//                        "Fantasy"
//                );
//
//        NovelBook book4 =
//                new NovelBook(
//                        "The Hobbit",
//                        "J.R.R. Tolkien",
//                        "Fantasy"
//                );
//
//        addBook(book1);
//        addBook(book2);
//        addBook(book3);
//        addBook(book4);
//
//        Member member1 = new Member("Sam");
//        Member member2 = new Member("Rahul");
//
//        Librarian librarian1 = new Librarian("Admin");
//
//        registeredUser(member1);
//        registeredUser(member2);
//        registeredUser(librarian1);
//
//        displayAllBooks();
//        displayRegisteredUsers();
//
//        System.out.println("\n===== SEARCH RESULT =====");
//
//        List<Book> searchResult1 = searchBooks("Java");
//
//        for (Book book : searchResult1)
//        {
//            System.out.println(book);
//        }
//
//        System.out.println("\n===== SEARCH BY TYPE =====");
//
//        List<Book> searchResult2 =
//                searchBooks("Harry", "NOVEL");
//
//        for (Book book : searchResult2)
//        {
//            System.out.println(book);
//        }
//
//        System.out.println("\n===== LENDING =====");
//
//        boolean success = book1.lend(member1);
//
//        if (success)
//        {
//            System.out.println(
//                    member1.getName()
//                            + " borrowed "
//                            + book1.getTitle()
//            );
//        }
//
//        book1.lend(member2);
//
//        book2.lend(member1);
//        book2.lend(member1);
//
//        System.out.println("\n===== RETURN =====");
//
//        System.out.println(
//                "Before Return: "
//                        + book1.isAvailable()
//        );
//
//        book1.returnBook(member1);
//
//        System.out.println(
//                "After Return: "
//                        + book1.isAvailable()
//        );
//
//        book1.lend(member2);
//
//        System.out.println(
//                member2.getName()
//                        + " borrowed"
//                        + book1.getTitle()
//        );
//
//
//
//    }
//}
