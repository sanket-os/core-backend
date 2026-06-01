package myLMS;

public class Librarian extends User{
    public Librarian(String name)
    {
        super(name);
    }

    @Override
    public int getBorrowLimit()
    {
        return 5;
    }
}
