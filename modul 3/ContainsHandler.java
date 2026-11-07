public class ContainsHandler implements BorrowHandler {
    private BorrowHandler next;

    @Override
    public BorrowHandler setNext(BorrowHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public String handle(Student student, Textbook textbook) {
        if (student.getTextbooks().contains(textbook)) {
            return "Учебник уже у студента";
        }
        return next.handle(student, textbook);
    }
}
