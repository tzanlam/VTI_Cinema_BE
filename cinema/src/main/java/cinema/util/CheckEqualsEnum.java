package cinema.util;

public class CheckEqualsEnum {
    public static <E extends Enum<E>> boolean checkEqualsEnum(Class<E> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
}
