package com.ermanno.salestax.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ermanno.salestax.config.SalesTaxTestConfig;
import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.ItemType;
import com.ermanno.salestax.model.Money;
import com.ermanno.salestax.model.taxes.BasicSalesTax;
import com.ermanno.salestax.model.taxes.ImportDutySalesTax;
import com.ermanno.salestax.model.taxes.Tax;

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
                             .withPrice(new Money("100.0"))
                             .build();
        assertTrue(new Money("0.00").compareTo(basicSalesTax.calculateTax(bookItem)) == 0);
    }

    @Test
    public void importDutySalesTaxNotAppliedToNonImportedItemTest() {
        Item item = new Item.Builder().withDescription("non imported item").withPrice(new Money("100.0")).imported(false).build();
        assertTrue(new Money("0.00").compareTo(importDutySalesTax.calculateTax(item)) == 0);
    }

    @Test
    public void basicSalesTaxAppliedToGenericItemTest() {
        Item item = new Item.Builder().withDescription("generic item").withPrice(new Money("100.0")).build();
        assertTrue(new Money("10.00").compareTo(basicSalesTax.calculateTax(item)) == 0);
    }

    @Test
    public void importDutySalesTaxAppliedToImportedGoodsTest() {
        Item item = new Item.Builder().withDescription("imported item").withPrice(new Money("100.0")).imported(true).build();
        assertTrue(new Money("5.00").compareTo(importDutySalesTax.calculateTax(item)) == 0);
    }

    @Test
    public void nonImportedMusicShouldPayBasicSalesTaxTest() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(new Money("14.99")).build();
        assertTrue(new Money("1.50").compareTo(basicSalesTax.calculateTax(cd)) == 0);
    }

    @Test
    public void nonImportedMusicShouldPayTaxTest() {
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(new Money("14.99")).build();
        assertTrue(new Money("1.50").compareTo(taxCalculator.calculateTaxes(cd)) == 0);
    }
    
    @Test
    public void importedPerfumeShouldPayBasicSalesTax() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
            .withPrice(new Money("47.50")).imported(true).build();
        assertTrue(new Money("4.750").compareTo(basicSalesTax.calculateTax(item)) == 0);
    }
    
    @Test
    public void importedPerfumeShouldPayImportDutySalesTax() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
            .withPrice(new Money("47.50")).imported(true).build();
        assertTrue(new Money("2.40").compareTo(importDutySalesTax.calculateTax(item)) == 0);        
    }
    
    @Test
    public void importedPerfumeShouldPayTaxTest() {
        Item item = new Item.Builder().withDescription("1 imported bottle of perfume")
            .withPrice(new Money("47.50")).imported(true).build();
        assertTrue(new Money("7.150").compareTo(taxCalculator.calculateTaxes(item)) == 0);
    }
}
