import java.util.*;

public class GameManager {
    private static GameManager instance;
    private Theme currentTheme;
    private final List<ThemeObserver> observers = new ArrayList<>();

    private GameManager() {
        currentTheme = ThemeFactory.getTheme("Animal"); // default using Factory
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void setTheme(Theme theme) {
        this.currentTheme = theme;
        notifyObservers();
    }

    public Theme getTheme() {
        return currentTheme;
    }

    public void addObserver(ThemeObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ThemeObserver obs : observers) {
            obs.onThemeChanged(currentTheme);
        }
    }
}
