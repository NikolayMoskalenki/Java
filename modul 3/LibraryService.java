import java.util.Optional;

public interface LibraryService {
    Optional<Textbook> find(String title);
}
