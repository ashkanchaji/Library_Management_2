import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Act {
    /**
     * Gets the info about the student and if the ID is a duplicate it prints "duplicate-id",
     * else it creates a student object and adds it to the students hashMap and print "success".
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
     *             5: lastName, 6: nationalID, 7: birthYear, 8: address
     *
     *
     */

    public static void addStudent(String[] info){
        // 0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
        // 5: lastName, 6: nationalID, 7: birthYear, 8: address
        Student student = new Student(info[2], info[3], info[4], info[5], info[6], info[7], info[8]);

        if (student.cannotAdd(info)){return;}

        User.addUser(info[2], student);
    }

    /**
     * Gets info about a staff or professor and calls isDuplicateUser and
     * checkNotAdmin, and if it passes both, creates a user subclass depending
     * on type and adds it to users collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
     *             5: lastName, 6: nationalID, 7: birthYear, 8: address, 9: staffType
     *
     *
     *
     */

    public static void addStaff (String[] info) {
        // 0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
        // 5: lastName, 6: nationalID, 7: birthYear, 8: address, 9: staffType

        User user;

        if (info[9].equals("staff")){
            user = new Staff(info[2], info[3], info[4], info[5], info[6], info[7], info[8]);
        } else {
            user = new Professor(info[2], info[3], info[4], info[5], info[6], info[7], info[8]);
        }

        if (user.cannotAdd(info)){return;}

        User.addUser(info[2], user);
    }

    /**
     * Gets info about the manager and calls isDuplicateUser and
     * checkNotAdmin, and if it passes both, creates a manager object
     * and adds it to users collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
     *             5: lastName, 6: nationalID, 7: birthYear, 8: address, 9: libraryID
     *
     */

    public static void addManager (String[] info){
        // 0: adminName, 1: adminPass, 2: id, 3: password, 4: firstName,
        // 5: lastName, 6: nationalID, 7: birthYear, 8: address, 9: libraryID

        Manager manager = new Manager(info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9]);

        if (manager.cannotAdd(info)){return;}

        if (!Management.getLibraries().containsKey(info[9])){
            System.out.println("not-found");
            return;
        }

        User.addUser(info[2], manager);
    }

    /**
     * Gets a book's information and after checking all the infos for validation
     * add it to its library resource collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: managerID, 1: password, 2: bookID, 3: bookName, 4: author,
     *             5: publisher, 6: printYear, 7: copyCount, 8: categoryId, 9 libraryID
     *
     *
     */

    public static void addBook(String[] info){
        // 0: managerID, 1: password, 2: bookID, 3: bookName, 4: author,
        // 5: publisher, 6: printYear, 7: copyCount, 8: categoryId, 9: libraryID

        Book book = new Book(info[2], info[3], info[4], info[5], info[6],
                Integer.parseInt(info[7]), info[8], info[9]);

        if (book.cannotAdd(IDs(info[0], info[1], info[2], info[9], info[8]))){
            return;
        }

        Resource.addResource(info[9], info[2], book);
    }

    /**
     * Gets a thesis's information and after checking all the infos for validation
     * add it to its library resource collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: managerID, 1: password, 2: thesisID, 3: thesisName, 4: author,
     *             5: professor, 6: printYear, 7: categoryId, 8: libraryID
     *
     */

    public static void addThesis(String[] info){
        // 0: managerID, 1: password, 2: thesisID, 3: thesisName, 4: author,
        // 5: professor, 6: printYear, 7: categoryId, 8: libraryID

        Thesis thesis = new Thesis(info[2], info[3], info[4], info[5], info[6], info[7], info[8]);

        if (thesis.cannotAdd(IDs(info[0], info[1], info[2], info[8], info[7]))){
            return;
        }

        Resource.addResource(info[8], info[2], thesis);
    }

    /**
     * Gets a Ganjineh book's information and after checking all the infos for validation
     * add it to its library resource collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: managerID, 1: password, 2: bookID, 3: bookName, 4: author,
     *             5: publisher, 6: printYear, 7: donor, 8: categoryId, 9: libraryID
     *
     */

    public static void addGanjineh (String[] info){
        // 0: managerID, 1: password, 2: bookID, 3: bookName, 4: author,
        // 5: publisher, 6: printYear, 7: donor, 8: categoryId, 9: libraryID

        Ganjineh ganjineh = new Ganjineh(info[2], info[3], info[4], info[5], info[6],
                info[7], info[8], info[9]);

        if (ganjineh.cannotAdd(IDs(info[0], info[1], info[2], info[9], info[8]))){
            return;
        }

        Resource.addResource(info[9], info[2], ganjineh);
    }

    /**
     * Gets a selling book's information and after checking all the infos for validation
     * add it to its library resource collection.
     *
     * @param info A String array which each index is associated to an info like this:
     *             0: managerID, 1: password, 2: bookID, 3: bookName, 4: author, 5: publisher,
     *             6: printYear, 7: copyCount, 8: price, 9: discount, 10: categoryId, 11: libraryID
     *
     */

    public static void addSellingBook (String[] info) {
        // 0: managerID, 1: password, 2: bookID, 3: bookName, 4: author, 5: publisher,
        // 6: printYear, 7: copyCount, 8: price, 9: discount, 10: categoryId, 11: libraryID

        SellingBook sellingBook = new SellingBook(info[2], info[3], info[4], info[5], info[6],
                Integer.parseInt(info[7]), Integer.parseInt(info[8]), Integer.parseInt(info[9]),
                info[10], info[11]);


        if (sellingBook.cannotAdd(IDs(info[0], info[1], info[2], info[11], info[10]))){
            return;
        }

        Resource.addResource(info[11], info[2], sellingBook);
    }

    private static String[] IDs (String managerID, String managerPass,
                                 String resourceID, String libraryId, String categoryID){
        return new String[] {managerID, managerPass, resourceID, libraryId, categoryID};
    }

    public static void borrowResource(String[] info){
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID, 4: date, 5: time

        Borrow borrow = new Borrow(info[0], info[2], info[3], info[4], info[5]);

        if (borrow.cannotAdd(info)){return;}

        User user = Management.getUsers().get(info[0]);
        Library library = Management.getLibraries().get(info[2]);

        user.getBorrowedResources().add(borrow);
        library.getBorrows().add(borrow);

        Resource resource = library.getResources().get(info[3]);
        if (resource instanceof Thesis){
            ((Thesis) resource).setBorrowed(true);
        } else {
            int newCount = ((Book) resource).getCurrentCopyCount() - 1;
            ((Book) resource).setCurrentCopyCount(newCount);
        }
        System.out.println("success");
    }

    // not returning the latest resource?
    public static void returnResource(String[] info){
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID, 4: date, 5: time

        if (ResourceAction.notValidInfo(info[0], info[1], info[2], info[3])){return;}

        Borrow borrow = Borrow.getBorrow(info[3], info[2], info[0]);

        if (borrow == null){
            System.out.println("not-found");
            return;
        }

        long borrowDuration = Borrow.borrowDuration(borrow.getDate(), borrow.getTime(), info[4], info[5]);
        long penalty = Borrow.calculatePenalty(Management.getUsers().get(info[0]), borrow, borrowDuration);

        Borrow.removeBorrow(info[3], info[2], info[0]);

        Resource resource = Management.getLibraries().get(info[2]).getResources().get(info[3]);

        if (resource instanceof Book){
            int newCount = ((Book) resource).getCurrentCopyCount() + 1;
            ((Book) resource).setCurrentCopyCount(newCount);
        } else {
            ((Thesis) resource).setBorrowed(false);
        }

        if (penalty != 0){
            long oldPenalty = Management.getUsers().get(info[0]).getPenaltySum();
            long newPenaltySum = oldPenalty + penalty;
            Management.getUsers().get(info[0]).setPenaltySum(newPenaltySum);

            System.out.println(penalty);
            return;
        }

        System.out.println("success");
    }

    public static void buy (String[] info){
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID

        Buy boughtResource = new Buy(info[2], info[3], info[0]);

        if (boughtResource.cannotAdd(info)){return;}

        Resource resource = Management.getLibraries().get(info[2]).getResources().get(info[3]);

        int newCount = ((SellingBook) resource).getCurrentCopyCount() - 1;
        ((SellingBook) resource).setCurrentCopyCount(newCount);

        Buy.getSoldBooks().add(boughtResource);
        System.out.println("success");
    }

    public static void read(String[] info){
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID, 4: date, 5: time
        if (ResourceAction.notValidInfo(info[0], info[1], info[2], info[3])){return;}

        Resource resource = Management.getLibraries().get(info[2]).getResources().get(info[3]);
        if (Read.limitationApplied(info[0], resource, info[4], info[5])){return;}

        ((Ganjineh) resource).setReader(info[0]);
        ((Ganjineh) resource).setBorrowedToReadDate(info[4]);
        ((Ganjineh) resource).setBorrowedToReadTime(info[5]);

        System.out.println("success");
    }

    public static void comment (String[] info){
        // 0: userID, 1: userPass, 2: libraryId, 3: resourceID, 4: comment

        Comment comment = new Comment(info[0], info[4]);

        if (comment.cannotAdd(info)){return;}

        Management.getLibraries().get(info[2]).getResources().get(info[3]).getComments().add(comment);

        System.out.println("success");
    }

    public static void search(String[] info){
        // 0: keyword to search

        String keyWord = info[0].toLowerCase();

        ArrayList<String> foundSources = new ArrayList<>();

        Management.getLibraries().forEach((libraryID, library) ->{
            HashMap<String, Resource> resources = library.getResources();

            resources.forEach((resourceID, resource) -> {
                for (String name : resource.namesToSearchIN()){
                    if (name.toLowerCase().contains(keyWord)){
                        foundSources.add(resource.getId());
                    }
                }
            });
        });

        printIDs(foundSources, true);
    }

    public static void searchUser(String[] info){
        // 0: userID, 1: userPass, 2: keyword

        User staff = Management.getUsers().get(info[0]);

        if (staff == null){
            System.out.println("not-found");
            return;
        }

        if (staff.notStaff(staff, info[1])){return;}

        String keyword = info[2].toLowerCase();

        ArrayList<String> foundIDs = new ArrayList<>();

        Management.getUsers().forEach((userID, user) -> {
            if (user.getFirstName().toLowerCase().contains(keyword) ||
                user.getLastName().toLowerCase().contains(keyword)){
                foundIDs.add(userID);
            }
        });

        printIDs(foundIDs, true);
    }

    private static void printIDs(ArrayList<String> IDs, boolean notFound){
        if (IDs.isEmpty()){
            if (notFound){
                System.out.println("not-found");
            } else {
                System.out.println("none");
            }
            return;
        }

        Collections.sort(IDs);

        for (int i = 0; i < IDs.size(); i++) {
            if (i == 0) {
                System.out.print(IDs.get(i));
            } else {
                System.out.print("|" + IDs.get(i));
            }
        }

        System.out.println();
    }

    public static void categoryReport(String[] info){
        //0: managerID, 1: managerPass, 2: categoryID, 3: libraryID

        if (Resource.checkNotManager(info[0], info[1]) ||
            Resource.notValidIDs(info[3], info[2]) ||
            Resource.notManagerLibrary(info[0], info[3])){return;}

        int bookCount = 0;
        int thesisCount = 0;
        int ganjinehCount = 0;
        int sellingBookCount = 0;

        Library library = Management.getLibraries().get(info[3]);

        for (String resourceID : library.getResources().keySet()){
            Resource resource = library.getResources().get(resourceID);

            Category category = Management.getCategories().get(resource.getCategoryID());

            if (Category.lookUpCategories(category, info[2])){
                if (resource instanceof Thesis){
                    if (!((Thesis) resource).isBorrowed()){
                        thesisCount++;
                    }
                } else if (resource instanceof Ganjineh){
                    ganjinehCount += ((Ganjineh) resource).getCurrentCopyCount();
                } else if (resource instanceof SellingBook){
                    sellingBookCount += ((SellingBook) resource).getCurrentCopyCount();
                } else {
                    bookCount += ((Book) resource).getCurrentCopyCount();
                }
            }
        }

        System.out.printf("%d %d %d %d\n", bookCount, thesisCount, ganjinehCount, sellingBookCount);
    }

    public static void libraryReport(String[] info){
        //0: managerID, 1: managerPass, 2: libraryID

        if (Resource.checkNotManager(info[0], info[1]) ||
                Resource.notValidIDs(info[2], "null") ||
                Resource.notManagerLibrary(info[0], info[2])){return;}

        int booksCount = 0;
        int thesisCount = 0;
        int borrowedBooksCount = 0;
        int borrowedThesisCount = 0;
        int ganjinehCount = 0;
        int remainedSellingBooksCount = 0;

        Library library = Management.getLibraries().get(info[2]);

        for (String resourceID : library.getResources().keySet()){
            Resource resource = library.getResources().get(resourceID);

            if (resource instanceof Thesis){
                if (!((Thesis) resource).isBorrowed()){
                    thesisCount++;
                } else {
                    borrowedThesisCount++;
                }
            } else if (resource instanceof Ganjineh){
                ganjinehCount++;
            } else if (resource instanceof SellingBook){
                remainedSellingBooksCount += ((SellingBook) resource).getCurrentCopyCount();
            } else {
                Book book = (Book) resource;

                booksCount += book.getCurrentCopyCount();

                if (book.getCopyCount() != book.getCurrentCopyCount()){
                    borrowedBooksCount += book.getCopyCount() - book.getCurrentCopyCount();
                }
            }
        }

        System.out.printf("%d %d %d %d %d %d\n", booksCount, thesisCount, borrowedBooksCount,
                            borrowedThesisCount, ganjinehCount, remainedSellingBooksCount);
    }

    public static void reportPassedDeadLine(String[] info){
        //0: managerID, 1: managerPass, 2: libraryID, 3: date, 4: time

        if (Resource.checkNotManager(info[0], info[1]) ||
                Resource.notValidIDs(info[2], "null") ||
                Resource.notManagerLibrary(info[0], info[2])){return;}

        Library library = Management.getLibraries().get(info[2]);

        ArrayList<String> passedDeadlineSources = new ArrayList<>();

        for (Borrow borrow : library.getBorrows()){
            if (!passedDeadlineSources.contains(borrow.getResourceID()) &&
                borrow.getLibraryID().equals(info[2]) &&
               (Borrow.borrowDuration(borrow.getDate(), borrow.getTime(), info[3], info[4]) > borrow.getMaxBorrowTime())){
                passedDeadlineSources.add(borrow.getResourceID());
            }
        }

        printIDs(passedDeadlineSources, false);
    }

    public static void reportPenaltiesSum(String[] info){
        // 0: managerID, 1: managerPass

        if (Management.checkNotAdmin(info[0], info[1])){return;}

        long[] totalPenalties = {0};

        Management.getUsers().forEach((ID, user) -> {
            totalPenalties[0] += user.getPenaltySum();
        });

        System.out.println(totalPenalties[0]);
    }
}
