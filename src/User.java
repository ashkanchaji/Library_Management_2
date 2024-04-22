import java.util.HashSet;

public class User implements Duplicate, Restrictions {
    private final String id;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String nationalId;
    private final String birthYear;
    private final String address;
    private long penaltySum;
    private final int penaltyPerHour;
    private final int maxBorrowCount;
    private HashSet<Borrow> borrowedResources;

    public User(String id, String password, String firstName, String lastName,
                String nationalId, String birthYear, String address, int penaltyPerHour, int maxInUse) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.birthYear = birthYear;
        this.address = address;
        this.penaltyPerHour = penaltyPerHour;
        maxBorrowCount = maxInUse;
        penaltySum = 0;
        borrowedResources = new HashSet<>();
    }

    /**
     * Adds any kind of user to the user collection and prints "success"
     *
     * @param id user id
     * @param user the user object
     */

    protected static void addUser (String id, User user){
        Management.getUsers().put(id, user);

        System.out.println("success");
    }

    /**
     * Gets admin info and an ID and after checking the admin and the validation
     * of the ID, it deletes the user from the collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id
     *
     */

    public static void removeUser(String[] info){
        // 0: adminName, 1: adminPass, 2: id

        if (Management.checkNotAdmin(info[0], info[1])){return;}

        if (!Management.getUsers().containsKey(info[2])){
            System.out.println("not-found");
            return;
        }

        User user = Management.getUsers().get(info[2]);

        if (user.getPenaltySum() != 0 ||
                !user.getBorrowedResources().isEmpty()){
            System.out.println("not-allowed");
            return;
        }

        Management.getUsers().remove(info[2]);
        System.out.println("success");
    }

    @Override
    public boolean cannotAdd(String[] info) {
        // 0: adminName, 1: adminPass, 2: userID
        return Management.checkNotAdmin(info[0], info[1]) || isDuplicate(info[2]);
    }

    /**
     * Checks if the user ID is a duplicate and if so, prints "duplicate-id".
     * (Used in other methods and not called directly)
     *
     * @param userID id to check
     * @return boolean -> true if it's a duplicate, else false.
     */

    @Override
    public boolean isDuplicate(String userID){
        if (Management.getUsers().containsKey(userID)) {
            System.out.println("duplicate-id");
            return true;
        }

        return false;
    }

    @Deprecated
    public boolean isDuplicate(String resourceID, String libraryID) {
        return false;
    }

    public boolean notStaff(User user, String userPass){
        if (user instanceof Student){
            System.out.println("permission-denied");
            return true;
        }
        if (!user.getPassword().equals(userPass)){
            System.out.println("invalid-pass");
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getAddress() {
        return address;
    }

    public long getPenaltySum() {
        return penaltySum;
    }

    public void setPenaltySum(long penaltySum) {
        this.penaltySum = penaltySum;
    }

    public int getPenaltyPerHour() {
        return penaltyPerHour;
    }

    public int getMaxBorrowCount() {
        return maxBorrowCount;
    }

    public HashSet<Borrow> getBorrowedResources() {
        return borrowedResources;
    }

    public void setBorrowedResources(HashSet<Borrow> borrowedResources) {
        this.borrowedResources = borrowedResources;
    }
}
