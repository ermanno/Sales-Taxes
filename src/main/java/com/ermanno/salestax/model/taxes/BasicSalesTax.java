package com.ermanno.salestax.model.taxes;

import java.util.Arrays;
import java.util.Collection;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.ItemType;

public class BasicSalesTax extends AbstractSalesTax {

    protected static double basicSalesTaxRate = 0.1;
    protected static Collection<ItemType> itemsExcludedFromBasicSalesTax = 
            Arrays.asList(ItemType.BOOK, ItemType.FOOD, ItemType.MEDICINE);
    
    private boolean isExcludedFromBasicSalesTax(final Item item) {
        return itemsExcludedFromBasicSalesTax.contains(item.getType());
    }
    
    @Override
    public double calculateSalesTax(Item item) {
        if (isExcludedFromBasicSalesTax(item))
            return 0;
        else
            return item.getPrice() * basicSalesTaxRate;
    }
    
}
