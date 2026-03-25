package com.irvings.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuItem {
    private final String itemId;
    private final String name;
    private final double basePrice;
    private boolean available;
    private final List<CustomizationOption> customizationOptions = new ArrayList<>();

    public MenuItem(String itemId, String name, double basePrice, boolean available) {
        this.itemId = itemId;
        this.name = name;
        this.basePrice = basePrice;
        this.available = available;
    }

    public String getItemId() { return itemId; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public boolean isAvailable() { return available; }

    public void setAvailability(boolean available) {
        System.out.println("[MenuItem] setAvailability(" + itemId + "): " + this.available + " -> " + available);
        this.available = available;
    }

    public void addCustomizationOption(CustomizationOption option) {
        customizationOptions.add(option);
    }

    public List<CustomizationOption> getCustomizationOptions() {
        return Collections.unmodifiableList(customizationOptions);
    }

    // deterministic menu for harness
    public static List<MenuItem> getStubMenu() {
        System.out.println("[MenuItem] getStubMenu()");
        List<MenuItem> menu = new ArrayList<>();

        MenuItem plain = new MenuItem("BAGEL-PLAIN", "Plain Bagel", 1.75, true);
        plain.addCustomizationOption(new CustomizationOption("TOAST", "Toasted", 0.00));
        plain.addCustomizationOption(new CustomizationOption("EXTRA-CC", "Extra Cream Cheese", 0.75));

        MenuItem everything = new MenuItem("BAGEL-EVERYTHING", "Everything Bagel", 1.95, true);
        everything.addCustomizationOption(new CustomizationOption("TOAST", "Toasted", 0.00));
        everything.addCustomizationOption(new CustomizationOption("CC-SCALLION", "Scallion Cream Cheese", 0.80));

        MenuItem coldBrew = new MenuItem("DRINK-COLDBREW", "Cold Brew", 3.25, true);

        MenuItem soldOut = new MenuItem("BAGEL-SOLDOUT", "Limited Bagel (Sold Out)", 2.25, false);

        menu.add(plain);
        menu.add(everything);
        menu.add(coldBrew);
        menu.add(soldOut);
        return menu;
    }
}

