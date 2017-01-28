package com.ermanno.salestax.services;

import java.util.List;

import com.ermanno.salestax.valueobjects.Item;

public interface ReceiptGenerator {

    /**
     * Return the receipt as a Java String.
     * @param shoppingBasket
     * @return the receipt in string form
     */
    String createReceiptString(List<Item> shoppingBasket);

}