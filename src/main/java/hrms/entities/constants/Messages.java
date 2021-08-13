package hrms.entities.constants;

public class Messages {
    private static final String NOT_EMPTY = " cannot be empty.";
    private static final String VALID = "Please enter a valid";
    private static final String MIN_NAME_LENGTH = " name must contain minimum 2 characters.";

    // City
    public static final String cityNameNotEmpty = "City name" + NOT_EMPTY;

    // Corporate
    public static final String companyNameNotEmpty = "Company name" + NOT_EMPTY;
    public static final String websiteNotEmpty = "Website" + NOT_EMPTY;
    public static final String userNotEmpty = "User" + NOT_EMPTY;

    // User
    public static final String emailNotEmpty = "Email" + NOT_EMPTY;
    public static final String invalidEmail = VALID + " email.";

    public static final String firstNameNotEmpty = "First" + NOT_EMPTY;
    public static final String firstNameLength = "First" + MIN_NAME_LENGTH;

    public static final String lastNameNotEmpty = "Last" + NOT_EMPTY;
    public static final String lastNameLength = "Last" + MIN_NAME_LENGTH;

    public static final String passwordNotEmpty = "Password" + NOT_EMPTY;
    public static final String passwordLength = "Your password must be at least 8 characters long.";
    public static final String invalidPassword = VALID + " password.";

    public static final String nationalIdentityNotEmpty = "National Identity" + NOT_EMPTY;
    public static final String nationalIdentityLength = "National Identity must be 11 characters long.";

    public static final String dateOfBirthNotEmpty = "Date of birth" + NOT_EMPTY;
}