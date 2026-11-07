import java.util.List;

public class CatalogHandler implements BorrowHandler {

    private final List<Textbook> catalog;

    public CatalogHandler(List<Textbook> catalog) {
        this.catalog = catalog;
    }

    @Override
    public BorrowHandler setNext(BorrowHandler next) {
        return this;
    }

    @Override
    public String handle(Student student, Textbook textbook) {
        if (catalog.contains(textbook)) {
            student.addTextbook(textbook);
            return "Выдан: " + textbook;
        }
        return "Учебника нет в каталоге";
    }
}
