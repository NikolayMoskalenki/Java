public class BasicTextbookDescription implements TextbookDescription {
    private final Textbook textbook;

    public BasicTextbookDescription(Textbook textbook) {
        this.textbook = textbook;
    }

    @Override
    public String describe() {
        return textbook.toString();
    }
}
