package com.ermanno.salestax.model.taxes;

import com.ermanno.salestax.model.Item;

public interface Tax {  
    /**
     * Calculates the amount to pay for this tax for the current object.
     * @param item the item to which we want to apply the tax.
     * @return the tax amount.
     */
    public double calculateTax(Item item);
}