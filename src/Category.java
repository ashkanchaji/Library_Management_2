public class Category {
    private final String id;
    private final String name;
    private final Category superCategory;

    /**
     * A constructor that initializes all the fields.
     *
     * @param id category id
     * @param name category name
     * @param superCategory super category object
     */

    public Category(String id, String name, Category superCategory) {
        this.id = id;
        this.name = name;
        this.superCategory = superCategory;
    }

    /**
     * Gets the info about the category and if the ID is not a duplicate, creates
     * a category object and adds it to the categories hashMap. If the ID is a duplicate
     * it will print "duplicate-id", else ir would print "success".
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id, 3: name, 4: superCategory
     *
     *
     */

    public static void addCategory(String[] info) {
        // 0: adminName, 1: adminPass, 2: id, 3: name, 4: superCategory

        if (Management.checkNotAdmin(info[0], info[1])){return;}

        if (Management.getCategories().containsKey(info[2])) {
            System.out.println("duplicate-id");
            return ;
        }

        if (!info[4].equals("null") && !Management.getCategories().containsKey(info[4])){
            System.out.println("not-found");
            return;
        }

        Category category = new Category(info[2], info[3], Management.getCategories().get(info[4]));
        Management.getCategories().put(info[2], category);

        System.out.println("success");
    }

    public static boolean lookUpCategories(Category subCategory, String categoryID){
        if (subCategory.getId().equals(categoryID)){return true;}

        Category category = subCategory;

        while (category.getSuperCategory() != null && !category.getSuperCategory().getId().equals("null")) {
            category = category.getSuperCategory();
            if (category.getId().equals(categoryID)){return true;}
        }

        return false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getSuperCategory() {
        return superCategory;
    }
}
