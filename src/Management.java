import java.util.*;

public class Management {
    private static final String adminName = "admin";
    private static final String adminPass = "AdminPass";

    private static final HashMap<String, Library> libraries = new HashMap<>();
    private static final HashMap<String, Category> categories = new HashMap<>();
    private static final HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        // add the null category as default
        Category nullCategory = new Category("null", "null", null);
        categories.put("null", nullCategory);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            // first check the no info commands
            if (input.equals("finish")) {
                break;
            } else if (input.equals("report-penalties-sum")) {
                //reportPenaltiesSum();
                continue;
            }

            processInput(input);
        }
    }

    private static void processInput(String input) {
        String[] commands = input.split("#");

        String[] info = commands[1].split("\\|");

        for (int i = 0; i < info.length; i++) {
            info[i] = info[i].trim();
        }

        switch (commands[0]) {
            case "add-library":
                Library.addLibrary(info);
                break;
            case "add-category":
                Category.addCategory(info);
                break;
            case "add-student":
                Act.addStudent(info);
                break;
            case "add-staff":
                Act.addStaff(info);
                break;
            case "add-manager":
                Act.addManager(info);
                break;
            case "remove-user":
                User.removeUser(info);
                break;
            case "add-book":
                Act.addBook(info);
                break;
            case "add-thesis":
                Act.addThesis(info);
                break;
            case "add-ganjineh-book":
                Act.addGanjineh(info);
                break;
            case "add-selling-book":
                Act.addSellingBook(info);
                break;
            case "remove-resource":
                Resource.removeResource(info);
                break;
            case "borrow":
                Act.borrowResource(info);
                break;
            case "return":
                Act.returnResource(info);
                break;
            case "buy":
                Act.buy(info);
                break;
            case "read":
                Act.read(info);
                break;
            case "add-comment":
                Act.comment(info);
                break;
            case "search":
                break;
            case "search-user":
                break;
            case "category-report":
                break;
            case "library-report":
                break;
            case "report-passed-deadline":
                break;
            case "report-penalties-sum":
                break;
        }
    }

    public static HashMap<String, Library> getLibraries(){
        return libraries;
    }

    public static HashMap<String, Category> getCategories(){
        return categories;
    }

    public static HashMap<String, User> getUsers(){
        return users;
    }

    /**
     * A method used in other methods that work with admin name & pass. If the user
     * is not the admin it outputs "permission-denied", else if the id is invalid,
     * outputs "not-found" and if the pass is wrong then "invalid-pass".
     *
     * @param name user name to compare with admin
     * @param pass user pass to compare with admin
     * @return boolean -> true if one of the cases above happens, else false.
     */

    public static boolean checkNotAdmin (String name, String pass){
        if (!name.equals(adminName)){
            if (!users.containsKey(name)){
                System.out.println("not-found");
            } else {
                System.out.println("permission-denied");
            }
            return true;
        }

        if (!pass.equals(adminPass)){
            System.out.println("invalid-pass");
            return true;
        }

        return false;
    }
}
