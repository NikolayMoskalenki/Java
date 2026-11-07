import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private List<Book> books = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Студент " + name + ", книг на полке: " + books.size();
    }
}
