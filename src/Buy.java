import java.util.HashSet;

public class Buy extends ResourceAction{
    private static HashSet<Buy> soldBooks;

    public Buy(String libraryID, String resourceID, String actorID) {
        super(libraryID, resourceID, actorID);
        soldBooks = new HashSet<>();
    }

    @Override
    public boolean cannotAdd(String[] info) {
        return notValidInfo(info[0], info[1], info[2], info[3]) ||
                limitationApplied(info);
    }

    @Override
    public boolean limitationApplied(String[] info) {
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID
        User user = Management.getUsers().get(info[0]);

        if (user instanceof Manager){
            System.out.println("permission-denied");
            return true;
        }

        Resource resource = Management.getLibraries().get(info[2]).getResources().get(info[3]);

        if (!(resource instanceof SellingBook) ||
                ((SellingBook) resource).getCurrentCopyCount() == 0 ||
                user.getPenaltySum() != 0) {
            System.out.println("not-allowed");
            return true;
        }
        return false;
    }

    public static HashSet<Buy> getSoldBooks() {
        return soldBooks;
    }

    public static void setSoldBooks(HashSet<Buy> soldBooks) {
        Buy.soldBooks = soldBooks;
    }
}
