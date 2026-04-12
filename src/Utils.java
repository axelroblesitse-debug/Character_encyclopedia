public class Utils {

    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isValidPower(int power) {
        return power >= 0 && power <= 100;
    }

    public static String renderPowerBar(int power) {
        int filled = power / 10;
        int empty = 10 - filled;

        String color;
        if (power == 100) {
            color = "\u001B[38;5;51m";   // cian eléctrico
        } else if (power >= 80) {
            color = "\u001B[38;5;46m";   // verde neón
        } else if (power >= 50) {
            color = "\u001B[38;5;226m";  // amarillo
        } else {
            color = "\u001B[38;5;196m";  // rojo
        }

        String bar = "█".repeat(filled) + "░".repeat(empty);
        return color + bar + "\u001B[0m" + " " + power + "/100";
    }
}
