public class Student extends User{
    public Student(String id, String password, String firstName, String lastName,
                   String nationalId, String birthYear, String address) {
        super(id, password, firstName, lastName, nationalId, birthYear, address, 50, 3);
    }
}
