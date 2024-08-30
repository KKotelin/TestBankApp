package model;

public class Profile {
    private String fullName;
    private String passportNumber;

    public Profile(String fullName, String passportNumber) {
        this.fullName = fullName;
        this.passportNumber = passportNumber;

    }

    public String getFullName() {
        return fullName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}
