public class Professor extends User {
    public Professor(String id, String password, String firstName, String lastName,
                     String nationalId, String birthYear, String address) {
        super(id, password, firstName, lastName, nationalId, birthYear, address, 100, 5);
    }

    @Override
    public boolean notStaff(User user, String userPass) {
        if (!(user instanceof Professor)){
            return super.notStaff(user, userPass);
        }
        if (!user.getPassword().equals(userPass)){
            System.out.println("invalid-pass");
            return true;
        }
        return false;
    }
}
