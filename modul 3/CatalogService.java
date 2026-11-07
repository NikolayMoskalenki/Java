import java.util.List;
import java.util.Optional;

public class CatalogService implements LibraryService {
    private final List<Textbook> catalog;

    public CatalogService(List<Textbook> catalog) {
        this.catalog = catalog;
    }

    @Override
    public Optional<Textbook> find(String title) {
        for (Textbook textbook : catalog) {
            if (textbook.getTitle().equals(title)) {
                return Optional.of(textbook);
            }
        }
        return Optional.empty();
    }
}
