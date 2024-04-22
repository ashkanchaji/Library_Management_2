import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;

public class Borrow extends ResourceAction implements Duplicate{
    private final String date;
    private final String time;
    private String type;
    private String returnedDate;
    private String returnedTime;
    private long inBorrowDays;
    private final int maxBorrowTime;

    public Borrow(String actorID, String libraryID, String resourceID, String date, String time) {
        super(libraryID, resourceID, actorID);
        this.date = date;
        this.time = time;
        this.type = "";
        this.returnedDate = "0001-01-01";
        this.returnedTime = "00:00";
        this.inBorrowDays = 0;
        this.maxBorrowTime = userMaxBorrowTime(actorID, libraryID, resourceID);
    }

    @Override
    public boolean cannotAdd(String[] info){
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID, 4: date, 5: time
        return notValidInfo(info[0], info[1], info[2], info[3]) ||
                limitationApplied(info) ||
                isDuplicate(info[3], info[0]);

    }

    @Override
    public boolean isDuplicate(String resourceID, String userID){
        if (containsResource(resourceID, userID)){
            System.out.println("not-allowed");
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean isDuplicate(String userID){return false;}

    public boolean containsResource(String resource, String userID){
        HashSet<Borrow> borrows = Management.getUsers().get(userID).getBorrowedResources();

        for (Borrow borrow : borrows){
            if (borrow.getResourceID().equals(resource) &&
                borrow.getLibraryID().equals(this.getLibraryID())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean limitationApplied(String[] info) {
        // 0: userID, 1: userPass, 2: libraryID, 3: resourceID, 4: date, 5: time
        User user = Management.getUsers().get(info[0]);
        if (user.getBorrowedResources().size() == user.getMaxBorrowCount() ||
                user.getPenaltySum() != 0){
            System.out.println("not-allowed");
            return true;
        }

        Resource resource = Management.getLibraries().get(info[2]).getResources().get(info[3]);
        if (resource instanceof Book){
            if (resource instanceof Ganjineh ||
                    resource instanceof SellingBook){
                System.out.println("not-allowed");
                return true;
            } else if (((Book) resource).getCurrentCopyCount() <= 0){
                System.out.println("not-allowed");
                return true;
            }
        } else if (resource instanceof Thesis){
            if (((Thesis) resource).isBorrowed()){
                System.out.println("not-allowed");
                return true;
            }
        }

        return false;
    }


    public static Borrow getBorrow (String resourceID, String libraryID, String userID){
        HashSet<Borrow> borrows = Management.getUsers().get(userID).getBorrowedResources();

        for (Borrow borrow : borrows){
            if (borrow.getResourceID().equals(resourceID) && borrow.getLibraryID().equals(libraryID)){
                return borrow;
            }
        }
        return null;
    }

    public static void removeBorrow (String resourceID, String libraryID, String userID){
        Iterator<Borrow> iterator = Management.getUsers().get(userID).getBorrowedResources().iterator();

        searchAndRemove(iterator, resourceID, libraryID);
    }

    private static void searchAndRemove(Iterator<Borrow> iterator, String resourceID, String libraryID){
        while (iterator.hasNext()){
            Borrow borrow = iterator.next();
            if (borrow.getResourceID().equals(resourceID) &&
                    borrow.getLibraryID().equals(libraryID)){
                iterator.remove();
                return;
            }
        }
    }

    private int userMaxBorrowTime (String userID, String libraryID, String resourceID){
        User user = Management.getUsers().get(userID);
        Resource resource;
        if (Management.getLibraries().containsKey(libraryID) &&
                Management.getLibraries().get(libraryID).getResources().containsKey(resourceID)){
            resource  = Management.getLibraries().get(libraryID).getResources().get(resourceID);
        } else {
            return 0;
        }

        if (user instanceof Student){
            if (resource instanceof Book){
                return 240;
            } else {
                return 168;
            }
        } else {
            if (resource instanceof Book){
                return 336;
            } else {
                return 240;
            }
        }
    }

    /**
     * This method calculates the duration that the source was borrowed after the user
     * returns it, in hours. And is used in returnSource method and is not called directly
     * using commands from the user.
     *
     * @param borrowingDate the date that the source was borrowed
     * @param borrowingTime the time that the source was borrowed
     * @param returningDate The date that the source is returned
     * @param returningTime the time that the source is returned
     * @return long (primitive) The duration that the source was borrowed in hours
     */

    public static long borrowDuration(String borrowingDate, String borrowingTime, String returningDate, String returningTime) {
        String[] borrowDateInfo = borrowingDate.split("-");
        String[] borrowTimeInfo = borrowingTime.split(":");

        String[] returnDateInfo = returningDate.split("-");
        String[] returnTimeInfo = returningTime.split(":");

        LocalDateTime borrowDate = LocalDateTime.of(Integer.parseInt(borrowDateInfo[0]),
                Integer.parseInt(borrowDateInfo[1]),
                Integer.parseInt(borrowDateInfo[2]),
                Integer.parseInt(borrowTimeInfo[0]),
                Integer.parseInt(borrowTimeInfo[1]));
        LocalDateTime returnDate = LocalDateTime.of(Integer.parseInt(returnDateInfo[0]),
                Integer.parseInt(returnDateInfo[1]),
                Integer.parseInt(returnDateInfo[2]),
                Integer.parseInt(returnTimeInfo[0]),
                Integer.parseInt(returnTimeInfo[1]));
        Duration duration = Duration.between(borrowDate, returnDate);

        return duration.toHours();
    }

    protected static long calculatePenalty(User user, Borrow borrow, long borrowDuration) {
        if (borrowDuration > borrow.getMaxBorrowTime()) {
            return (borrowDuration - borrow.getMaxBorrowTime()) * user.getPenaltyPerHour();
        } else {
            return 0;
        }
    }

    public static boolean sameBorrow(String userID, String libraryID, String resourceID,
             String borrowDate, String borrowTime, Borrow borrow){

       return (borrow.getLibraryID().equals(libraryID) &&
               borrow.getResourceID().equals(resourceID) &&
               borrow.getActorID().equals(userID) &&
               borrow.getDate().equals(borrowDate) &&
               borrow.getTime().equals(borrowTime));
    }

    public int getMaxBorrowTime() {
        return maxBorrowTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getReturnedTime() {
        return returnedTime;
    }

    public void setReturnedTime(String returnedTime) {
        this.returnedTime = returnedTime;
    }

    public long getInBorrowDays() {
        return inBorrowDays;
    }

    public void setInBorrowDays(long inBorrowDays) {
        this.inBorrowDays = inBorrowDays;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
