package parkinglot.accountFactory;

public abstract class Account {
    private Person person;
    private String userName;
    private String password;
    private AccountStatus accountStatus;

    public abstract boolean resetPassword();

    public Account(Person person, String userName, String password, AccountStatus accountStatus) {
        this.person = person;
        this.userName = userName;
        this.password = password;
        this.accountStatus = accountStatus;
    }

    public Person getPerson() {
        return person;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }
}
