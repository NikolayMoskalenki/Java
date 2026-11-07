public class DiscountDescription implements TextbookDescription {
    private final TextbookDescription wrappee;

    public DiscountDescription(TextbookDescription wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String describe() {
        return wrappee.describe() + ", действует скидка 10%";
    }
}
