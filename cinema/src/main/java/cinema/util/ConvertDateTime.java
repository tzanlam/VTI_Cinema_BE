package cinema.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConvertDateTime {
    public static LocalDate convertToLocalDate(String dateString) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("định dạng thời gian không hợp lệ");
            return null;
        }
    }

    public static LocalTime convertToLocalTime(String timeString) {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("định dạng thời gian không hợp lệ");
            return null;
        }
    }

    public static LocalDateTime convertToLocalDateTime(String dateTimeString) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("định dạng thời gian không hợp lệ");
            return null;
        }
    }
}
