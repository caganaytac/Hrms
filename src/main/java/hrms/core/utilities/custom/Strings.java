package hrms.core.utilities.custom;

public class Strings {
    public static String getPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\s", "");
        if (phoneNumber.startsWith("0")) {
            return "+90" + phoneNumber.substring(1).replaceAll("[^0-9]", "");
        }
        if (phoneNumber.startsWith("+90")) {
            return "+" + phoneNumber.replaceAll("[^0-9]", "");
        }
        return "+90" + phoneNumber.replaceAll("[^0-9]", "");
    }

    public static String getDomain(String url) {
        url = url.replaceAll("\\s", "");
        if (url.startsWith("http://")) {
            String domain = url.startsWith("http://www.") ? url.substring(11) : url.substring(7);
            return domain.endsWith("/") ? domain.substring(0, domain.length() - 1) : domain;
        }
        if (url.startsWith("https://")) {
            String domain = url.startsWith("https://www.") ? url.substring(12) : url.substring(8);
            return domain.endsWith("/") ? domain.substring(0, domain.length() - 1) : domain;
        }
        if (url.startsWith("www.")) {
            return url.endsWith("/") ? url.substring(4, url.length() - 1) : url.substring(4);
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}