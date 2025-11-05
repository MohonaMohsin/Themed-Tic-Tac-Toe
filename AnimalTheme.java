import java.awt.Color;

public class AnimalTheme implements Theme {
    @Override
    public String getPlayerXSymbol() { return "   🐱"; }
    @Override
    public String getPlayerXName() { return "Cat"; }
    @Override
    public String getPlayerOSymbol() { return "🐰"; }
    @Override
    public String getPlayerOName() { return "Bunny"; }
    @Override
    public Color getBackgroundColor() { return new Color(255, 182, 193); } // light pink
}
