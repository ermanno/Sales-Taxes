package com.ermanno.salestax.model.taxes;

import java.math.BigDecimal;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.Money;

public class ImportDutySalesTax implements Tax {

    protected static BigDecimal rate = new BigDecimal("0.05");
    
    @Override
    public Money calculateTax(Item item) {
        if (item.isImported())
            return item.getPrice().percentage(rate);
        else
            return new Money("0.00");
    }

}
