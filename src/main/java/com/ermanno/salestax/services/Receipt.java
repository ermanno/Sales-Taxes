package com.ermanno.salestax.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ermanno.salestax.valueobjects.Item;

@Service
public interface Receipt {

    /**
     * 
     * @param shoppingBasket
     * @return
     */
    String createReceiptString(List<Item> shoppingBasket);

}