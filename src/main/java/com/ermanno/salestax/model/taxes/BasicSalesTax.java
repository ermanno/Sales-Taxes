package com.ermanno.salestax.model.taxes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.ItemType;
import com.ermanno.salestax.model.Money;

public class BasicSalesTax implements Tax {

    protected static BigDecimal rate = new BigDecimal("0.1");
    protected static Collection<ItemType> itemsExcludedFromBasicSalesTax = 
            Arrays.asList(ItemType.BOOK, ItemType.FOOD, ItemType.MEDICINE);
    
    private boolean isExcludedFromBasicSalesTax(final Item item) {
        return itemsExcludedFromBasicSalesTax.contains(item.getType());
    }
    
    @Override
    public Money calculateTax(Item item) {
        if (isExcludedFromBasicSalesTax(item))
            return new Money("0.00");
        else
            return item.getPrice().percentage(rate);
    }
    
}
