public class Book extends Resource {
    private final String publisher;
    private final int copyCount;
    private int currentCopyCount;

    public Book(String id, String name, String author, String publisher,
                String printYear, int copyCount, String categoryID, String libraryID) {
        super(id, name, author, printYear, categoryID, libraryID);
        this.publisher = publisher.toLowerCase();
        this.copyCount = copyCount;
        currentCopyCount = copyCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public int getCurrentCopyCount() {
        return currentCopyCount;
    }

    public void setCurrentCopyCount(int currentCopyCount) {
        this.currentCopyCount = currentCopyCount;
    }
}
