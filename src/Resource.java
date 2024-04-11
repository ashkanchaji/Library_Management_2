import java.util.ArrayList;

public class Resource implements Duplicate, Restrictions{
    private final String id;
    private final String name;
    private final String author;
    private final String printYear;
    private final String categoryID;
    private final String libraryID;
    private final ArrayList<Comment> comments;

    public Resource(String id, String name, String author, String printYear,
                    String categoryID, String libraryID) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.printYear = printYear;
        this.categoryID = categoryID;
        this.libraryID = libraryID;
        comments = new ArrayList<>();
    }

    /**
     * Adds any kind of resource to its library resource collection and prints "success".
     *
     * @param libraryID library id to add resource to
     * @param resourceID resource id to add
     * @param resource the resource object
     */

    public static void addResource (String libraryID, String resourceID, Resource resource){
        Management.getLibraries().get(libraryID).getResources().put(resourceID, resource);
        System.out.println("success");
    }

    /**
     * Gets info about a resource and if all of them are valid it removes the
     * resource from its library resource. If library ID or resource ID is not
     * valid it outputs "not-found"
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: managerID, 1: password, 2: resourceID, 3: libraryID
     *
     * @see #checkNotManager(String, String)
     * @see #notManagerLibrary(String, String)
     */

    public static void removeResource (String[] info){
        // 0: managerID, 1: password, 2: resourceID, 3: libraryID

        if (checkNotManager(info[0], info[1])){return;}

        if (!Management.getLibraries().containsKey(info[3]) ||
                !Management.getLibraries().get(info[3]).getResources().containsKey(info[2])){
            System.out.println("not-found");
            return;
        }

        if (notManagerLibrary(info[0], info[3])){return;}

        Resource resource = Management.getLibraries().get(info[3]).getResources().get(info[2]);
        if (resource instanceof Book){
            if (((Book) resource).getCurrentCopyCount() != ((Book) resource).getCopyCount()){
                System.out.println("not-allowed");
                return;
            }
        } else if (resource instanceof Thesis){
            if (((Thesis) resource).isBorrowed()){
                System.out.println("not-allowed");
                return;
            }
        }

        Management.getLibraries().get(info[3]).getResources().remove(info[2]);
        System.out.println("success");
    }

    /**
     * A method used in other methods that checks for manager. If the user
     * is not a manager it outputs "permission-denied", else if the id is invalid,
     * outputs "not-found" and if the pass is wrong then "invalid-pass".
     *
     * @param ID user ID to compare with managerID
     * @param pass user pass to compare with manager pass
     * @return boolean -> true if one of the cases above happens, else false.
     */

    private static boolean checkNotManager (String ID, String pass){
        if (!Management.getUsers().containsKey(ID)){
            System.out.println("not-found");
            return true;
        } else if (!(Management.getUsers().get(ID) instanceof Manager)){
            System.out.println("permission-denied");
            return true;
        }

        if (!Management.getUsers().get(ID).getPassword().equals(pass)){
            System.out.println("invalid-pass");
            return true;
        }

        return false;
    }

    /**
     * Checks if the given library and category IDs are valid.
     *
     * @param libraryID library ID to check
     * @param categoryID category ID to check
     * @return boolean -> true if one is not valid, else false.
     */

    public static boolean notValidIDs (String libraryID, String categoryID){
        if (!Management.getLibraries().containsKey(libraryID) || !Management.getCategories().containsKey(categoryID)){
            System.out.println("not-found");
            return true;
        }

        return false;
    }

    public boolean isDuplicate (String resourceID, String libraryID){
        if (Management.getLibraries().get(libraryID).getResources().containsKey(resourceID)){
            System.out.println("duplicate-id");
            return true;
        }

        return false;
    }

    public boolean isDuplicate (String userID){return false;}

    /**
     * Checks if the manager has access to the library
     *
     * @param managerID manager ID to check
     * @param libraryID library ID to check
     * @return boolean -> true if manager does not have access, else false
     */

    private static boolean notManagerLibrary(String managerID, String libraryID){
        if (!((Manager)Management.getUsers().get(managerID)).getLibraryID().equals(libraryID)){
            System.out.println("permission-denied");
            return true;
        }

        return false;
    }

    /**
     * Gets needed infos and calls other methods to check each resource
     * condition validation
     *
     *
     * @param info an array of strings containing this infos:
     *             0: managerID, 1: passWord, 2: resourceID, 3: libraryId, 4: categoryID
     *
     * @return boolean -> true if one of the conditions is not valid, else false
     */

    public boolean cannotAdd(String[] info){
        //0: managerID, 1: passWord, 2: resourceID, 3: libraryId, 4: categoryID
        return checkNotManager(info[0], info[1]) ||
                notValidIDs(info[3], info[4]) ||
                isDuplicate(info[2], info[3]) ||
                notManagerLibrary(info[0], info[3]);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrintYear() {
        return printYear;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
