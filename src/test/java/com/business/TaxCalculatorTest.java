package com.business;

import com.business.invoice.TaxCalculator;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxCalculatorTest{

    @Test
    public void testCalculateTax() {


        TaxCalculator taxCalculator = new TaxCalculator();


        /**
         * Prepare some items
         */
        InventoryItem cd1 = new InventoryItem("music CD", "CD", 15.99);
        InventoryItem cd2 = new InventoryItem("music CD", "CD", 14.99);
        InventoryItem book = new InventoryItem("book", "book", 29.49);
        InventoryItem chocolateSnack = new InventoryItem("chocolate snack", "snack", 0.75);

        InventoryItem wine = new InventoryItem("bottle of wine", "alcohol", 20.99);
        InventoryItem toothAcePills = new InventoryItem("tooth ache pills", "medical", 4.15);
        InventoryItem boxOfPins = new InventoryItem("box of pins", "other", 11.25);


        /**
         * Prepare some orders
         */

        Map<InventoryItem, Integer> order1 = new HashMap<InventoryItem, Integer>();
        order1.put(cd1,1);
        order1.put(book,1);
        order1.put(chocolateSnack,1);


        Map<InventoryItem, Integer> order2 = new HashMap<InventoryItem, Integer>();
        order2.put(toothAcePills,1);
        order2.put(wine,1);
        order2.put(cd2,1);
        order2.put(boxOfPins,1);



        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);


        /**
         * Calculate tax for order 1 and print invoice
         */
        List<PurchasedItem> order1Items = taxCalculator.calculateTax(order1);
        double salesTax = 0;
        double total = 0;
        for (PurchasedItem purchasedItem: order1Items) {
            System.out.println(purchasedItem.getUnits() + " " + purchasedItem.getInventoryItem().getName()
                    + ": " + df.format(purchasedItem.getTotal()));
            salesTax += purchasedItem.getTaxValue();
            total += purchasedItem.getTotal();
        }

        System.out.println("Sales Tax: "+ df.format(salesTax));
        System.out.println("Total: "+ df.format(total));

        System.out.println();

        /**
         * Calculate tax for order 2 and print invoice
         */

        List<PurchasedItem> order2Items = taxCalculator.calculateTax(order2);
        salesTax = 0;
        total = 0;
        for (PurchasedItem purchasedItem: order2Items) {
            System.out.println(purchasedItem.getUnits() + " " + purchasedItem.getInventoryItem().getName()
                    + ": " + df.format(purchasedItem.getTotal()));
            salesTax += purchasedItem.getTaxValue();
            total += purchasedItem.getTotal();
        }

        System.out.println("Sales Tax: "+ df.format(salesTax));
        System.out.println("Total: "+ df.format(total));


    }
}
