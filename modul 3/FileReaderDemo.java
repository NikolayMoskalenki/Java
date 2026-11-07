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
                .peek(student -> student.getTextbooks()
                        .forEach(textbook -> System.out.println("    " + textbook)))
                .flatMap(student -> student.getTextbooks().stream())
                .sorted(Comparator.comparingInt(Textbook::getPages))
                .distinct()
                .filter(textbook -> textbook.getYear() > 2000)
                .limit(3)
                .map(Textbook::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденного учебника: " + year),
                        () -> System.out.println("Такой учебник отсутствует")
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

            student.addTextbook(new Textbook(title, pages, year));
        }

        return student;
    }
}
