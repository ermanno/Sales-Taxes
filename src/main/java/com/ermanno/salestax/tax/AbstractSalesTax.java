package com.ermanno.salestax.tax;

import com.ermanno.salestax.item.Item;

public abstract class AbstractSalesTax implements Tax {
    
    protected double round(final double number) {
        // See http://stackoverflow.com/questions/11815135/best-method-to-round-up-to-the-nearest-0-05-in-java
        // to understand why this is better than BigDecimal or long.
        return Math.ceil(number * 20) / 20.0; // round up to multiple of 0.05
    }
    
    protected abstract double calculateSalesTax(Item item);
    
    public double calculateTax(Item item) {
        return round(calculateSalesTax(item));
    }
}
