public class Textbook {

    private final String title;
    private final int pages;
    private final int year;

    public Textbook(String title, int pages, int year) {
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

        public Textbook build() {
            return new Textbook(title, pages, year);
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
        Textbook textbook = (Textbook) o;
        return pages == textbook.pages
                && year == textbook.year
                && title.equals(textbook.title);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, pages, year);
    }
}
