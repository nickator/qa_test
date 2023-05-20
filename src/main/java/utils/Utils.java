package utils;

public class Utils {

    public static double convertCurrencyTextToDouble(String text) {
        return Double.parseDouble(text.replaceAll("\\s+", "").replaceAll("Kč", "").replace(",", "."));
    }
}
