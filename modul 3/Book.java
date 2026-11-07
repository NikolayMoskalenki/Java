public class Book {

    private final String title;
    private final int pages;
    private final int year;

    public Book(String title, int pages, int year) {
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    public static class Builder {
        private final String title;
        private int pages;
        private int year;

        public Builder(String title) {
            this.title = title;
        }

        public Builder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Book build() {
            return new Book(title, pages, year);
        }
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + pages + " стр., " + year + " г.)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages
                && year == book.year
                && title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, pages, year);
    }
}
