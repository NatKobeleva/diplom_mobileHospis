package ru.iteco.fmhandroid.ui.data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    public static String getRandomCategory() {
        List<String> categories = List.of("Объявление", "День рождения", "Зарплата", "Профсоюз",
                "Праздник", "Массаж");
        return categories.get(new Random().nextInt(categories.size()));
    }

    public static String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }
        return randomText.toString();
    }

    public static String generateRandomDate() {
        long minDay = LocalDate.of(2025, 2, 1).toEpochDay(); // Минимальная дата
        long maxDay = LocalDate.of(2025, 3, 31).toEpochDay(); // Максимальная дата
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateRandomTime() {
        Random random = new Random();
        int hour = random.nextInt(24); // Часы от 0 до 23
        int minute = random.nextInt(60); // Минуты от 0 до 59
        return String.format("%02d:%02d", hour, minute); // Формат времени HH:mm
    }
}