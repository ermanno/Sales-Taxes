package com.ermanno.salestax.tax;

import com.ermanno.salestax.item.Item;

public abstract class AbstractSalesTax implements Tax {
    protected static double round_off = 0.05;
    
    protected double round(final double number) {
        return Math.ceil(number / round_off) * round_off;
    }
    
    protected abstract double calculateSalesTax(Item item);
    
    public double calculateTax(Item item) {
        return round(calculateSalesTax(item));
    }
}
