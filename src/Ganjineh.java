public class Ganjineh extends Book{
    private final String donor;
    private String reader;
    private String borrowedToReadDate;
    private String borrowedToReadTime;

    public Ganjineh(String id, String name, String author, String publisher,
                    String printYear, String donor, String categoryID,
                    String libraryID) {
        super(id, name, author, publisher, printYear, 1, categoryID, libraryID);
        this.donor = donor;
        borrowedToReadDate = "0001-01-01";
        borrowedToReadTime = "00:00";
    }

    @Override
    public String[] namesToSearchIN() {
        String[] names = super.namesToSearchIN();
        names[2] = this.donor;
        return new String[]{names[0], names[1], names[2], this.getPublisher()};
    }

    public String getDonor() {
        return donor;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getBorrowedToReadDate() {
        return borrowedToReadDate;
    }

    public void setBorrowedToReadDate(String borrowedToReadDate) {
        this.borrowedToReadDate = borrowedToReadDate;
    }

    public String getBorrowedToReadTime() {
        return borrowedToReadTime;
    }

    public void setBorrowedToReadTime(String borrowedToReadTime) {
        this.borrowedToReadTime = borrowedToReadTime;
    }
}
