package com.ermanno.salestax.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ermanno.salestax.item.Item;

@Service
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
        StringBuilder receipt = new StringBuilder();
        double totalPriceAfterTax = 0;
        double salesTaxes = 0;
        for (Item item : shoppingBasket) {
            double tax = taxCalculator.calculateTaxes(item);
            double priceAfterTax = item.getPrice() + tax;
            receipt.append(String.format("%s: %.2f\n", item.getDescription(), priceAfterTax));
            totalPriceAfterTax += priceAfterTax;
            salesTaxes += tax;
        }
        receipt.append(String.format("Sales Taxes: %.2f\nTotal: %.2f", salesTaxes, totalPriceAfterTax));
        return receipt.toString();
    }
}
