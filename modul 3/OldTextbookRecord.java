public class OldTextbookRecord {
    private final String title;
    private final int pageCount;
    private final int publicationYear;

    public OldTextbookRecord(String title, int pageCount, int publicationYear) {
        this.title = title;
        this.pageCount = pageCount;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
