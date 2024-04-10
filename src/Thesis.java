public class Thesis extends Resource{
    private final String professor;
    private boolean isBorrowed;

    public Thesis(String id, String name, String author, String professor, String printYear,
                  String categoryID, String libraryID) {
        super(id, name, author, printYear, categoryID, libraryID);
        this.professor = professor;
        isBorrowed = false;
    }

    public String getProfessor() {
        return professor;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
