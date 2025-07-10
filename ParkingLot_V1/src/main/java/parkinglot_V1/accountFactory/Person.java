package parkinglot_V1.accountFactory;

public class Person {
    String Name;
    String email;
    String contactNo;

    public Person(String name, String email, String contactNo) {
        Name = name;
        this.email = email;
        this.contactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }
}
