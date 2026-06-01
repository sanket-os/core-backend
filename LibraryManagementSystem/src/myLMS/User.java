package myLMS;

public abstract class User {

    private String name;
    private int borrowedBooksCount;

    public User(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getBorrowedBooksCount()
    {
        return borrowedBooksCount;
    }

    public void incrementBorrowedBooks()
    {
        borrowedBooksCount++;
    }

    public void decrementBorrowedBooks()
    {
        borrowedBooksCount--;
    }

    public abstract int getBorrowLimit();

    @Override
    public String toString()
    {
        return """
               ---------------------------------
               Name: %s
               Type: %s
               Borrowed Books: %d
               """.formatted(
                name,
                this.getClass().getSimpleName(),
                borrowedBooksCount
        );
    }

}
