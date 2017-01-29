package com.ermanno.salestax.services;

import java.util.List;

import com.ermanno.salestax.model.Item;

public interface ReceiptGenerator {

    /**
     * Uses the TaxCalculator that is injected by Spring to compose a receipt summing up the information 
     * about price and taxes that are applied to the items in the shoppingBasket.
     * @param shoppingBasket the list of items for which we want to generate the report.
     * @return a String containing the receipt content.
     */
    public String createReceiptString(List<Item> shoppingBasket);

}