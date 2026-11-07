import java.util.List;

public class PatternsDemo {

    public static void main(String[] args) {
        Textbook mechanics = new Textbook("Курс теоретической механики", 1200, 1869);
        Textbook clean = new Textbook.Builder("Чистый код").pages(464).year(2008).build();
        Textbook grok = new Textbook.Builder("Грокаем алгоритмы").pages(256).year(2013).build();
        Textbook quantum = new Textbook.Builder("Физика. Квантовая механика").pages(936).year(2003).build();
        Textbook web = new Textbook.Builder("Основы веб-программирования").pages(384).year(2018).build();

        List<Textbook> catalog = List.of(mechanics, clean, grok, quantum, web);
        Student alex = new Student("Алексей");

        System.out.println("=== СТРАТЕГИЯ ===");
        SortStrategy byPages = new SortByPages();
        byPages.sort(catalog).forEach(textbook -> System.out.println(textbook));
        System.out.println("---");
        SortStrategy byYear = new SortByYear();
        byYear.sort(catalog).forEach(textbook -> System.out.println(textbook));

        System.out.println();
        System.out.println("=== ПРОКСИ ===");
        LibraryService library = new RareFundProxy(new CatalogService(catalog));
        library.find("Курс теоретической механики").ifPresentOrElse(
                textbook -> System.out.println("Выдан: " + textbook),
                () -> System.out.println("Учебник не выдан")
        );
        library.find("Чистый код").ifPresentOrElse(
                textbook -> System.out.println("Выдан: " + textbook),
                () -> System.out.println("Учебник не выдан")
        );

        System.out.println();
        System.out.println("=== ЦЕПОЧКА ОБЯЗАННОСТЕЙ ===");
        BorrowHandler chain = new LimitHandler();
        chain.setNext(new ContainsHandler()).setNext(new CatalogHandler(catalog));

        Textbook toBorrow = grok;
        System.out.println("Пытаемся взять: " + toBorrow);
        System.out.println("Результат: " + chain.handle(alex, toBorrow));

        System.out.println("Пытаемся взять тот же учебник ещё раз:");
        System.out.println("Результат: " + chain.handle(alex, toBorrow));

        Textbook unknown = new Textbook("Несуществующий учебник", 100, 2020);
        System.out.println("Пытаемся взять: " + unknown);
        System.out.println("Результат: " + chain.handle(alex, unknown));
        System.out.println("Полка Алексея: " + alex);

        System.out.println();
        System.out.println("=== ДЕКОРАТОР ===");
        TextbookDescription simple = new BasicTextbookDescription(grok);
        System.out.println(simple.describe());
        TextbookDescription rich = new DiscountDescription(new SignedCopyDescription(new BasicTextbookDescription(grok)));
        System.out.println(rich.describe());

        System.out.println();
        System.out.println("=== АДАПТЕР ===");
        OldTextbookRecord record = new OldTextbookRecord("Справочник по сопротивлению материалов", 1088, 1957);
        Textbook adapted = new TextbookAdapter().toTextbook(record);
        System.out.println("Из старой записи получен учебник: " + adapted);
    }
}
