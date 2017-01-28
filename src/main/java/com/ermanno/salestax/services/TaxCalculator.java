package com.ermanno.salestax.services;

import com.ermanno.salestax.valueobjects.Item;

public interface TaxCalculator {

    /**
     * 
     * @param item
     * @return
     */
    double calculateBasicSalesTax(Item item);

    /**
     * 
     * @param item
     * @return
     */
    double calculateImportDutySalesTax(Item item);

    /**
     * 
     * @param item
     * @return
     */
    double calculateTaxes(Item item);

}