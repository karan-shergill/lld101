package parkinglot.accountFactory;

public class AccountFactory {
    public static Account getAccountOfType(AccountType accountType, Person person, String userName, String password, AccountStatus accountStatus) {
        switch (accountType) {
            case PARKING_ADMIN -> {
                return new ParkingAdmin(person,userName, password, accountStatus);
            }
            case PARKING_ATTENDANT -> {
                return new ParkingAttendant(person, userName, password, accountStatus);
            }
        }
        return null;
    }
}
