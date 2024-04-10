public class Staff extends User {
    public Staff(String id, String password, String firstName, String lastName,
                 String nationalId, String birthYear, String address) {
        super(id, password, firstName, lastName, nationalId, birthYear, address, 100, 5);
    }
}
