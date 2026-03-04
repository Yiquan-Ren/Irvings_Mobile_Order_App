package menu;

public class CustomizationOption {
    private final String optionId;
    private final String name;
    private final double priceDelta;

    public CustomizationOption(String optionId, String name, double priceDelta) {
        this.optionId = optionId;
        this.name = name;
        this.priceDelta = priceDelta;
    }

    public String getOptionId() { return optionId; }
    public String getName() { return name; }
    public double getPriceDelta() { return priceDelta; }

    @Override
    public String toString() {
        return name + " (" + optionId + ", +" + priceDelta + ")";
    }
}
