package com.business.invoice;

import com.business.InventoryItem;
import com.business.Tax;
import com.business.PurchasedItem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tax calculator calculates taxes on an order
 */
public class TaxCalculator {

    /**
     * Gets the taxes for each category
     * @return
     */
    public Map<String, List<Tax>> getCategoryTaxes() {


        /**
         * Define several type of taxes with tax rates
         */
        Tax taxPercentage = new Tax("percentage", 17.5);
        Tax taxFixed = new Tax( "fixed", 1.25);
        Tax exemptTax = new Tax("fixed", 0);

        List<Tax> medicalTaxes = new ArrayList<Tax>();
        medicalTaxes.add(exemptTax);

        List<Tax> cdTaxes = new ArrayList<Tax>();
        cdTaxes.add(taxPercentage);
        cdTaxes.add(taxFixed);

        List<Tax> otherTaxes = new ArrayList<Tax>();
        otherTaxes.add(taxPercentage);

        Map<String, List<Tax>> categoryTaxes = new HashMap<String, List<Tax>>();

        /**
         * For each category of Items, define a list of taxes.
         */
        categoryTaxes.put("CD", cdTaxes);
        categoryTaxes.put("medical", medicalTaxes);
        categoryTaxes.put("other", otherTaxes);

        return  categoryTaxes;
    }


    /**
     * Calculates tax on items in orders.
     * Order is a map of item vs units bought
     * @param order
     * @return A list of purchased items with their final value with tax.
     */
    public List<PurchasedItem> calculateTax(Map<InventoryItem, Integer> order) {

        Map<String, List<Tax>> categoryTaxes = getCategoryTaxes();
        List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();


        for (InventoryItem inventoryItem : order.keySet()) {

            List<Tax> taxes = null;
            if (categoryTaxes.containsKey(inventoryItem.getCategory())) {
                taxes = categoryTaxes.get(inventoryItem.getCategory());
            } else {
                taxes = categoryTaxes.get("other");
            }

            int units = order.get(inventoryItem);
            double taxValue = 0;
            for (Tax tax : taxes) {
                double t = 0;
                if ("fixed".equalsIgnoreCase(tax.getTaxType())) {
                    t += tax.getTaxRate() * units;
                } else {
                    t = (tax.getTaxRate() * inventoryItem.getRatePerUnit() * units) / 100.0;
                }

                DecimalFormat df = new DecimalFormat("##.##");
                df.setRoundingMode(RoundingMode.UP);
                taxValue += Double.parseDouble(df.format(new Double(t)));
            }
            double total = units * inventoryItem.getRatePerUnit() + taxValue;
            purchasedItems.add(new PurchasedItem(inventoryItem, units, taxValue, total));

        }
        return purchasedItems;
    }
}
