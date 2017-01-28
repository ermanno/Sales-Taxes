package com.ermanno.salestax.services;

import java.util.List;

import com.ermanno.salestax.valueobjects.Item;

public interface ReceiptGenerator {

    /**
     * Uses the TaxCalculator that is injected by Spring to compose a receipt summing up the information 
     * about price and taxes that are appliet to the items in the shoppingBasket. This could potentially 
     * be extendend to include other forms of output such as JSON.
     * @param shoppingBasket the list (not collection, since order matters for the purpose of the report)
     *        of items for which we want to generate the report.
     * @return a String containing the receipt content.
     */
    public String createReceiptString(List<Item> shoppingBasket);

}