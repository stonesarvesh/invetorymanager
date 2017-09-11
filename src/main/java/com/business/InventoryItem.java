package com.business;

/**
 * Describes an item from inventory. It has name, item category and rate of item per unit
 */
public class InventoryItem {

    private String name;

    private String category;

    private double ratePerUnit;

    public InventoryItem(String name, String category, double ratePerUnit) {
        this.name = name;
        this.category = category;
        this.ratePerUnit = ratePerUnit;
    }

    public double getRatePerUnit() {
        return ratePerUnit;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InventoryItem) {
            InventoryItem other = (InventoryItem) obj;
            return this.name.equals(other.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
