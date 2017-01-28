package com.ermanno.salestax.services;

import com.ermanno.salestax.valueobjects.Item;

public interface TaxCalculator {

    /**
     * Returns the basic sales tax for the given item.
     * @param item the item for which we wish to calculate the basic sales tax.
     * @return the rounded amount of the basic sales tax.
     */
    double calculateBasicSalesTax(Item item);

    /**
     * Returns the import duty sales for the given item.
     * @param item the item for which we wish to calculate the import duty sales tax.
     * @return the rounded amount of the import duty sales tax.
     */
    double calculateImportDutySalesTax(Item item);

    /**
     * Returns the total amount of taxes applied to the given item.
     * @param item the item for which we wish te calculate the total amount of taxes.
     * @return the rounded amouent of the total amount of taxes.
     */
    double calculateTaxes(Item item);

}