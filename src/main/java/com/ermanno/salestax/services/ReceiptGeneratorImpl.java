package com.ermanno.salestax.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.Money;

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
        Money totalPriceAfterTax = new Money("0.00");
        Money salesTaxes = new Money("0.00");
        for (Item item : shoppingBasket) {
            Money tax = taxCalculator.calculateTaxes(item);
            Money priceAfterTax = item.getPrice().add(tax);
            receipt.append(String.format("%s: %s\n", item.getDescription(), priceAfterTax));
            totalPriceAfterTax.add(priceAfterTax);
            salesTaxes.add(tax);
        }
        receipt.append(String.format("Sales Taxes: %s\nTotal: %s", salesTaxes, totalPriceAfterTax));
        return receipt.toString();
    }
}
