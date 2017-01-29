package com.ermanno.salestax.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ermanno.salestax.config.SalesTaxTestConfig;
import com.ermanno.salestax.item.Item;
import com.ermanno.salestax.item.ItemType;
import com.ermanno.salestax.tax.BasicSalesTax;
import com.ermanno.salestax.tax.ImportDutySalesTax;
import com.ermanno.salestax.tax.Tax;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SalesTaxTestConfig.class)
public class TaxCalculatorTest {
    private TaxCalculator taxCalculator;
    private Tax basicSalesTax;
    private Tax importDutySalesTax;

    @Autowired
    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }
    
    @Before
    public void setUp() {
        basicSalesTax = new BasicSalesTax();
        importDutySalesTax = new ImportDutySalesTax();
    }
    
    @Test
    public void basicSalesTaxNotAppliedToExcludedItemTest() {
        Item bookItem = new Item.Builder()
                             .withDescription("book test item")
                             .withType(ItemType.BOOK)
                             .withPrice(100.0)
                             .build();
        assertEquals(0.0, basicSalesTax.calculateTax(bookItem), 0);
    }

    @Test
    public void importDutySalesTaxNotAppliedToNonImportedItemTest() {
        Item item = new Item.Builder().withDescription("non imported item").withPrice(100.0).imported(false).build();
        assertEquals(0.0, importDutySalesTax.calculateTax(item), 0);
    }

    @Test
    public void basicSalesTaxAppliedToGenericItemTest() {
        Item item = new Item.Builder().withDescription("generic item").withPrice(100.0).build();
        assertEquals(10.0, basicSalesTax.calculateTax(item), 0);
    }

    @Test
    public void importDutySalesTaxAppliedToImportedGoodsTest() {
        Item item = new Item.Builder().withDescription("imported item").withPrice(100.0).imported(true).build();
        assertEquals(5.0, importDutySalesTax.calculateTax(item), 0);
    }

    @Test
    public void nonImportedMusicShouldPayBasicSalesTaxTest() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(14.99).build();
        assertEquals(1.5, basicSalesTax.calculateTax(cd), 0);
    }

    @Test
    public void nonImportedMusicShouldPayTaxTest() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(14.99).build();
        assertEquals(1.5, taxCalculator.calculateTaxes(cd), 0);
    }
    
    @Test
    public void importedPerfumeShouldPayBasicSalesTax() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
                .withPrice(47.50).imported(true).build();
        assertEquals(4.75, basicSalesTax.calculateTax(item), 0);
    }
    
    @Test
    public void importedPerfumeShouldPayImportDutySalesTax() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
                .withPrice(47.50).imported(true).build();
        assertEquals(2.74, importDutySalesTax.calculateTax(item), 0);        
    }
    
    @Test
    public void importedPerfumeShouldPayTaxTest() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
                        .withPrice(47.50).imported(true).build();
        assertEquals(7.15, taxCalculator.calculateTaxes(item), 0);
    }
}
