package myLMS;

public class NovelBook extends Book{

    private String genre;

    public NovelBook(String title, String author, String genre)
    {
        super(title, author);
        this.genre = genre;
    }

    @Override
    public BookType getBookType()
    {
        return BookType.NOVEL;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "Genre: " + genre + "\n";
    }

}
