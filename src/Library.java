import java.util.HashMap;
import java.util.HashSet;

public class Library {
    private final String id;
    private final String name;
    private final String establishYear;
    private final int tableCount;
    private final String address;
    private HashMap<String, Resource> resources;
    private HashSet<Borrow> borrows;

    /**
     * A constructor that initializes all the fields
     *
     * @param id library ID
     * @param name library name
     * @param establishYear library establish year
     * @param tableCount the number of seats in the library
     * @param address library address
     */

    public Library(String id, String name, String establishYear,
                   String tableCount, String address) {
        this.id = id;
        this.name = name;
        this.establishYear = establishYear;
        this.tableCount = Integer.parseInt(tableCount);
        this.address = address;
        resources = new HashMap<>();
        borrows = new HashSet<>();
    }

    /**
     * Gets the info about the library and if the ID is not a duplicate, creates
     * a library object and adds it to the libraries hashMap. If the ID is a duplicate
     * it will print "duplicate-id", else ir would print "success".
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id, 3: name, 4: establishYear,
     *             5: tableCount, 6: address
     *
     *
     */

    public static void addLibrary(String[] info) {
        // 0: adminName, 1: adminPass, 2: id, 3: name, 4: establishYear,
        // 5: tableCount, 6: address

        if (Management.checkNotAdmin(info[0], info[1])){return;}

        if (Management.getLibraries().containsKey(info[2])) {
            System.out.println("duplicate-id");
            return ;
        }

        Library library = new Library(info[2], info[3], info[4], info[5], info[6]);
        Management.getLibraries().put(info[2], library);

        System.out.println("success");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEstablishYear() {
        return establishYear;
    }

    public int getTableCount() {
        return tableCount;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Resource> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Resource> resources) {
        this.resources = resources;
    }

    public HashSet<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(HashSet<Borrow> borrows) {
        this.borrows = borrows;
    }
}

