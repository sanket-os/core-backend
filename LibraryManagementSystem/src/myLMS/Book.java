package myLMS;

public abstract class Book {

    private String title;
    private String author;
    private boolean isAvailable;
    private User borrowedBy;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public abstract BookType getBookType();

    public boolean lend(User user)
    {
        if (!isAvailable)
        {
            System.out.println(title + " is already lent.");
            return false;
        }

        if (user.getBorrowedBooksCount() >= user.getBorrowLimit())
        {
            System.out.println(user.getName() + " exceeded borrowing limit.");
            return false;
        }

        isAvailable = false;
        borrowedBy = user;

        user.incrementBorrowedBooks();

        return true;
    }

    public void returnBook(User user)
    {
        if (borrowedBy == user)
        {
            isAvailable = true;
            borrowedBy = null;

            user.decrementBorrowedBooks();

            System.out.println(title + " returned successfully.");
        }
    }

    @Override
    public String toString()
    {
        return """
                ------------------------
                Title: %s
                Author: %s
                Type: %s
                Available: %s
                """.formatted(
                title,
                author,
                getBookType(),
                isAvailable
        );
    }

}
