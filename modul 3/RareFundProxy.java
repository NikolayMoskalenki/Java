import java.util.Optional;

public class RareFundProxy implements LibraryService {
    private final LibraryService inner;

    public RareFundProxy(LibraryService inner) {
        this.inner = inner;
    }

    @Override
    public Optional<Textbook> find(String title) {
        System.out.println("Прокси: обращение к фонду — «" + title + "»");

        Optional<Textbook> result = inner.find(title);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        Textbook textbook = result.get();
        if (textbook.getPages() > 800) {
            System.out.println("Прокси: учебник редкий, только в читальном зале");
            return Optional.empty();
        }
        return result;
    }
}
