public class Read {
    protected static boolean limitationApplied(String userID, Resource resource, String date, String time){
        User user = Management.getUsers().get(userID);

        if (!(user instanceof Professor)){
            System.out.println("permission-denied");
            return true;
        }

        if (!(resource instanceof Ganjineh)){
            System.out.println("not-allowed");
            return true;
        }

        Ganjineh ganjineh = (Ganjineh) resource;
        long inReadDuration = Borrow.borrowDuration(ganjineh.getBorrowedToReadDate(),
                ganjineh.getBorrowedToReadTime(), date, time);

        if (inReadDuration < 2 || user.getPenaltySum() != 0){
            System.out.println("not-allowed");
            return true;
        }

        return false;
    }
}
