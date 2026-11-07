import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByYear implements SortStrategy {
    @Override
    public List<Textbook> sort(List<Textbook> textbooks) {
        List<Textbook> copy = new ArrayList<>(textbooks);
        copy.sort(Comparator.comparingInt(Textbook::getYear));
        return copy;
    }
}
