package com.ermanno.salestax.services;

import com.ermanno.salestax.model.Item;

public interface TaxCalculator {
    
    /**
     * Returns the total amount of taxes applied to the given item.
     * @param item the item for which we wish te calculate the total amount of taxes.
     * @return the rounded amouent of the total amount of taxes.
     */
    public double calculateTaxes(Item item);

}