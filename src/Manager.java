public class Manager extends User {
    private final String libraryID;

    public Manager(String id, String password, String firstName, String lastName,
                   String nationalId, String birthYear, String address, String libraryID) {
        super(id, password, firstName, lastName, nationalId, birthYear, address, 100, 5);
        this.libraryID = libraryID;
    }

    public String getLibraryID() {
        return libraryID;
    }
}
