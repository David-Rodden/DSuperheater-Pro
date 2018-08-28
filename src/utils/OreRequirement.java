package utils;

public class OreRequirement {
    private final String name;
    private final int amount;
    public OreRequirement(final String name, final int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
