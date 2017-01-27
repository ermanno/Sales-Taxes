package com.ermanno.salestax.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ermanno.salestax.valueobjects.Item;
import com.ermanno.salestax.valueobjects.ItemType;

public class TaxCalculatorTest {
    private TaxCalculator taxCalculator;
    
    @Autowired
    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }
    
    @Test
    public void basicSalesTaxNotAppliedToExcludedItemTest() {
        Item bookItem = new Item.Builder()
                             .withDescription("book test item")
                             .withType(ItemType.BOOK)
                             .withPrice(100.0)
                             .build();
        assertEquals(0.0, taxCalculator.calculateBasicSalesTax(bookItem), 0);
    }

    @Test
    public void importDutySalesTaxNotAppliedToNonImportedItemTest() {
        Item item = new Item.Builder().withDescription("non imported item").withPrice(100.0).imported(false).build();
        assertEquals(0.0, taxCalculator.calculateImportDutySales(item), 0);
    }

    @Test
    public void basicSalesTaxAppliedToGenericItemTest() {
        Item item = new Item.Builder().withDescription("generic item").withPrice(100.0).build();
        assertEquals(10.0, taxCalculator.calculateBasicSalesTax(item), 0);
    }

    @Test
    public void importDutySalesTaxAppliedToImportedGoods() {
        Item item = new Item.Builder().withDescription("imported item").withPrice(100.0).imported(true).build();
        assertEquals(5.0, taxCalculator.calculateImportDutySales(item), 0);
    }

    @Test
    public void nonImportedMusicShouldPayBasicSalesTax() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(14.99).build();
        assertEquals(1.5, taxCalculator.calculateBasicSalesTax(cd), 0);
    }

    @Test
    public void nonImportedMusicTaxesShouldntBeZero() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(14.99).build();
        assertEquals(1.5, taxCalculator.calculateTaxes(cd), 0);
    }
}
