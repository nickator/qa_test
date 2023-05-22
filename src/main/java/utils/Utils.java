package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utils {

    public static double convertCurrencyTextToDouble(String text) {
        return Double.parseDouble(text.replaceAll("\\s+", "").replaceAll("Kč", "").replace(",", "."));
    }

    public static <T> Collector<T, ?, List<T>> toShuffledList() {
        return Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                list -> {
                    Collections.shuffle(list);
                    return list;
                });
    }

    public static String generateRandomLetters(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomLetter = (char) (random.nextInt(26) + 'a');
            stringBuilder.append(randomLetter);
        }

        return stringBuilder.toString();
    }
}
