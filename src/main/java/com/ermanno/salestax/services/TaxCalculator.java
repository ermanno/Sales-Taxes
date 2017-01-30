package com.ermanno.salestax.services;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.Money;

public interface TaxCalculator {
    
    /**
     * Returns the total amount of taxes applied to the given item.
     * @param item the item for which we wish to calculate the total amount of taxes.
     * @return the rounded amount of the total amount of taxes.
     */
    public Money calculateTaxes(Item item);

}