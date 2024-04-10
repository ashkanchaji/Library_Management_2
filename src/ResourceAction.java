public abstract class ResourceAction implements Limitations{
    private final String libraryID;
    private final String resourceID;
    private final String actorID;

    protected ResourceAction(String libraryID, String resourceID, String actorID) {
        this.libraryID = libraryID;
        this.resourceID = resourceID;
        this.actorID = actorID;
    }

    public static boolean notValidInfo(String userID, String userPass, String libraryID, String resourceID){
        return (userWrongInfo(userID, userPass) ||
                resourceWrongInfo(libraryID, resourceID));
    }

    protected static boolean userWrongInfo (String userID, String userPass){
        if (!Management.getUsers().containsKey(userID)){
            System.out.println("not-found");
            return true;
        }
        if (!Management.getUsers().get(userID).getPassword().equals(userPass)){
            System.out.println("invalid-pass");
            return true;
        }
        return false;
    }

    protected static boolean resourceWrongInfo (String libraryID, String resourceID){
        if(!Management.getLibraries().containsKey(libraryID) ||
                !Management.getLibraries().get(libraryID).getResources().containsKey(resourceID)){
            System.out.println("not-found");
            return true;
        }
        return false;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public String getResourceID() {
        return resourceID;
    }

    public String getActorID() {
        return actorID;
    }
}
