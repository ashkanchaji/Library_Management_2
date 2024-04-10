public class SellingBook extends Book{
    private final int price;
    private final int discount;

    public SellingBook(String id, String name, String author, String publisher,
                       String printYear, int copyCount, int price, int discount,
                       String categoryID, String libraryID) {
        super(id, name, author, publisher, printYear, copyCount, categoryID, libraryID);
        this.price = price;
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
