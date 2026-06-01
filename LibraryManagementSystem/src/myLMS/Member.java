package myLMS;

public class Member extends User{

    public Member(String name)
    {
        super(name);
    }

    @Override
    public int getBorrowLimit()
    {
        return 2;
    }

}
