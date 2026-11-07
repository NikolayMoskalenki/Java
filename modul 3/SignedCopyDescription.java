public class SignedCopyDescription implements TextbookDescription {
    private final TextbookDescription wrappee;

    public SignedCopyDescription(TextbookDescription wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String describe() {
        return wrappee.describe() + ", экземпляр с дарственной надписью автора";
    }
}
