public class LimitHandler implements BorrowHandler {
    private static final int MAX_TEXTBOOKS = 2;
    private BorrowHandler next;

    @Override
    public BorrowHandler setNext(BorrowHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public String handle(Student student, Textbook textbook) {
        if (student.getTextbooks().size() >= MAX_TEXTBOOKS) {
            return "Отказ: лимит учебников исчерпан";
        }
        return next.handle(student, textbook);
    }
}
