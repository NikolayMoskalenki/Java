public interface BorrowHandler {
    BorrowHandler setNext(BorrowHandler next);
    String handle(Student student, Textbook textbook);
}
