package myLMS;

public class TextBook extends Book {

    private String subject;

    public TextBook(String title, String author, String subject)
    {
        super(title, author);
        this.subject = subject;
    }

    @Override
    public BookType getBookType()
    {
        return BookType.TEXTBOOK;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "Subject: " + subject + "\n";
    }

}
