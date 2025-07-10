package parkinglot_V1.accountFactory;

public class ParkingAttendant extends Account{
    public ParkingAttendant(Person person, String userName, String password, AccountStatus accountStatus) {
        super(person, userName, password, accountStatus);
    }

    public boolean processTickets(String ticketNo) {
        return false;
    }

    @Override
    public boolean resetPassword() {
        return false;
    }
}
