public class BookAdapter {
    public Book toBook(OldBookRecord record) {
        return new Book(record.getTitle(), record.getPageCount(), record.getPublicationYear());
    }
}
