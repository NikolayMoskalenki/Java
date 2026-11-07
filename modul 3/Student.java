import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private List<Textbook> textbooks = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addTextbook(Textbook textbook) {
        textbooks.add(textbook);
    }

    public List<Textbook> getTextbooks() {
        return textbooks;
    }

    @Override
    public String toString() {
        return "Студент " + name + ", учебников на полке: " + textbooks.size();
    }
}
