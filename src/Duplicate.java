public interface Duplicate {
    boolean isDuplicate(String userID);

    /**
     * Checks if the resource ID is a duplicate and if so, prints "duplicate-id".
     * (Used in other methods and not called directly)
     *
     * @param resourceID resource id to check
     * @param libraryID  library id to search in
     * @return boolean -> true if it's a duplicate, else false.
     */
    boolean isDuplicate(String resourceID, String libraryID);
}
