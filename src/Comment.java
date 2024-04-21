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

        if (!Management.getUsers().containsKey(info[0]) ||
            !Management.getLibraries().containsKey(info[2]) ||
            !Management.getLibraries().get(info[2]).getResources().containsKey(info[3])){
            System.out.println("not-found");
            return true;
        }

        User user = Management.getUsers().get(info[0]);
        if (!(user instanceof Student) && !(user instanceof Professor)){
            System.out.println("permission-denied");
            return true;
        }

        if (!Management.getUsers().get(info[0]).getPassword().equals(info[1])){
            System.out.println("invalid-pass");
            return true;
        }

        return false;
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
