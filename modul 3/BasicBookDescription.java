public class BasicBookDescription implements BookDescription {
    private final Book book;

    public BasicBookDescription(Book book) {
        this.book = book;
    }

    @Override
    public String describe() {
        return book.toString();
    }
}
