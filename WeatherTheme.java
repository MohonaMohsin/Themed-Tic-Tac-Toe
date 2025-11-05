import java.awt.Color;

public class WeatherTheme implements Theme {
    @Override
    public String getPlayerXSymbol() { return "   🌞"; }
    @Override
    public String getPlayerXName() { return "Sunny"; }
    @Override
    public String getPlayerOSymbol() { return "🌧️"; }
    @Override
    public String getPlayerOName() { return "Rainy"; }
    @Override
    public Color getBackgroundColor() { return new Color(255, 204, 0); } // dark yellow
}
