public class TextbookAdapter {
    public Textbook toTextbook(OldTextbookRecord record) {
        return new Textbook(record.getTitle(), record.getPageCount(), record.getPublicationYear());
    }
}
