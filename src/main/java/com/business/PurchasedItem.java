package com.business;

/**
 * Describes an item purchased. It has an inventory item and number
 * of units purchased plus taxes on the total amount
 */
public class PurchasedItem {

    private InventoryItem inventoryItem;
    private int units;
    private double total;
    private double taxValue;

    public PurchasedItem(InventoryItem inventoryItem, int units, double taxValue, double total) {
        this.inventoryItem = inventoryItem;
        this.taxValue = taxValue;
        this.total = total;
        this.units = units;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public double getTotal() {
        return total;
    }

    public int getUnits() {
        return units;
    }
}
