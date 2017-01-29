package com.ermanno.salestax.tax;

import com.ermanno.salestax.item.Item;

public interface Tax {  
    public double calculateTax(Item item);
}
