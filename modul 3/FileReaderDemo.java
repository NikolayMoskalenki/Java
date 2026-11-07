import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderDemo {

    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of("students.txt"))) {
            lines.forEach(line -> students.add(parseStudent(line)));
        }

        students.stream()
                .peek(student -> System.out.println(student))
                .peek(student -> student.getBooks()
                        .forEach(book -> System.out.println("    " + book)))
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Такая книга отсутствует")
                );
    }

    private static Student parseStudent(String line) {
        String[] parts = line.split(";");

        Student student = new Student(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            String[] conversation = parts[i].split(",");
            String title = conversation[0];
            int pages = Integer.parseInt(conversation[1]);
            int year = Integer.parseInt(conversation[2]);

            student.addBook(new Book(title, pages, year));
        }

        return student;
    }
}
