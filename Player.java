public class Player implements Cloneable {
    private final String name;
    private final String symbol;

    private Player(Builder builder) {
        this.name = builder.name;
        this.symbol = builder.symbol;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }

    @Override
    public Player clone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Player.Builder().setName(name).setSymbol(symbol).build();
        }
    }

    public static class Builder {
        private String name;
        private String symbol;

        public Builder setName(String name) { this.name = name; return this; }
        public Builder setSymbol(String symbol) { this.symbol = symbol; return this; }
        public Player build() { return new Player(this); }
    }
}
