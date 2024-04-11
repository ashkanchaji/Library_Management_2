public class Comment implements Restrictions{
    private final String writerID;
    private final String comment;

    public Comment(String writerID, String comment) {
        this.writerID = writerID;
        this.comment = comment;
    }

    @Override
    public boolean cannotAdd(String[] info) {
        // 0: userID, 1: userPass, 2: libraryId, 3: resourceID

        return ResourceAction.notValidInfo(info[0], info[1], info[2], info[3]) ||
                notCommenter(info[0]);
    }

    private boolean notCommenter(String userID){
        User user = Management.getUsers().get(userID);
        if (!(user instanceof Student) && !(user instanceof Professor)){
            System.out.println("permission-denied");
            return true;
        }
        return false;
    }



    public String getWriterID() {
        return writerID;
    }

    public String getComment() {
        return comment;
    }
}
