public class ThemeFactory {
    public static Theme getTheme(String type) {
        if (type.equalsIgnoreCase("Animal")) {
            return new AnimalTheme();
        } else if (type.equalsIgnoreCase("Weather")) {
            return new WeatherTheme();
        } else {
            throw new IllegalArgumentException("Unknown theme type: " + type);
        }
    }
}
