package com.ermanno.salestax.tax;

import com.ermanno.salestax.item.Item;

public class ImportDutySalesTax extends AbstractSalesTax {

    protected static double importDutySalesTax = 0.05;
    
    @Override
    protected double calculateSalesTax(Item item) {
        if (item.isImported())
            return item.getPrice() * importDutySalesTax;
        else
            return 0;
    }

}
