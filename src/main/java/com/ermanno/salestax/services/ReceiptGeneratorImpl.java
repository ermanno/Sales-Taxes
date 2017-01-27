package com.ermanno.salestax.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ermanno.salestax.valueobjects.Item;

public class ReceiptGeneratorImpl implements ReceiptGenerator {
    private TaxCalculator taxCalculator;
    
    @Autowired
    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }
    
    /* (non-Javadoc)
     * @see com.ermanno.salestax.services.Receipt#createReceiptString(java.util.List)
     */
    @Override
    public String createReceiptString(List<Item> shoppingBasket) {
        StringBuilder receiptString = new StringBuilder();
        double totalPriceAfterTax = 0;
        double salesTaxes = 0;
        for (Item item : shoppingBasket) {
            double tax = taxCalculator.calculateTaxes(item);
            double priceAfterTax = item.getPrice() + tax;
            receiptString.append(String.format("%s: %.2f\n", item.getDescription(), priceAfterTax));
            totalPriceAfterTax += priceAfterTax;
            salesTaxes += tax;
        }
        receiptString.append(String.format("Sales Taxes: %.2f\nTotal: %.2f\n", salesTaxes, totalPriceAfterTax));
        return receiptString.toString();
    }
}
