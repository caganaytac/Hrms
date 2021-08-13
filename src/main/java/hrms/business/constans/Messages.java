package hrms.business.constans;

import java.util.Arrays;
import java.util.List;

public class Messages {
    public static String alreadyExists(String entity) {
        return entity + " already exists.";
    }

    public static String notFound(String entity) {
        return entity + " could not found.";
    }

    public static String canNotBe(String entity, String entity2) {
        List<String> wovels = Arrays.asList("A", "a", "E", "e", "I", "i", "O", "o");
        String one = wovels.contains(entity.substring(0, 1)) ? "An " : "A ";
        return one + entity + " cannot be " + entity2.toLowerCase() + ".";
    }

    public static String added(String entity) {
        return entity + " has been added.";
    }

    public static String updated(String entity) {
        return entity + " has been updated.";
    }

    public static String deleted(String entity) {
        return entity + " has been deleted.";
    }

    // User
    public static final String userConfirmed = "User has been confirmed.";
    public static final String userNotConfirmed = "Your account is not confirmed. Please confirm your account.";
    public static final String userAlreadyConfirmed = "User is already confirmed.";

    // Corporate
    public static final String notBusinessEmail = "Your email domain and website domain must be the same.";
    public static final String corporateConfirmed = "Corporate has been confirmed.";
    public static final String corporateAlreadyConfirmed = "Corporare is already confirmed.";

    // Favorite Job Advert
    public static final String unverifiedJobAdvert = "You cannot bookmark unverified job adverts.";
    public static final String employeeGuardForFavoriteJobAdvert = "Employees cannot bookmark to a job advert.";
    public static final String registered = "Welcome to us. Your confirmation email has been sent.";
    public static final String notRealPerson = "Please try to sign up with your real informations.";
}