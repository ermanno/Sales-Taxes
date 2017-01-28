package com.ermanno.salestax.services;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ermanno.salestax.config.SalesTaxTestConfig;
import com.ermanno.salestax.valueobjects.Item;
import com.ermanno.salestax.valueobjects.ItemType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SalesTaxTestConfig.class)
public class ReceiptGeneratorIntegrationTest {
    @Value(value = "classpath:importedItemsExpectedOutput.txt")
    private Resource importedItems;
    
    @Value(value = "classpath:mixedItemsExpectedOutput.txt")
    private Resource mixedItems;
    
    @Value(value = "classpath:nonImportedItemsExpectedOutput.txt")
    private Resource nonImportedItems;
    
    private String getFile(Resource resource) throws IOException{
        File file = resource.getFile();
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return content;
    }

    private ReceiptGenerator receipt;
    
    @Autowired
    public void setReceiptGenerator(ReceiptGenerator receipt) {
        this.receipt = receipt;
    }
    
    @Test
    public void printReceiptNonImportedItemsTest() throws IOException {
        Item book = new Item.Builder()
                         .withDescription("1 book")
                         .withType(ItemType.BOOK)
                         .withPrice(12.49)
                         .build();
        Item cd = new Item.Builder().withDescription("1 music CD").withPrice(14.99).build();
        Item chocolate = new Item.Builder()
                              .withDescription("1 chocolate bar")
                              .withType(ItemType.FOOD)
                              .withPrice(0.85)
                              .build();
        List<Item> items = new ArrayList<>();
        items.add(book);
        items.add(cd);
        items.add(chocolate);
        assertEquals(getFile(nonImportedItems), receipt.createReceiptString(items));
    }

    @Test
    public void printReceiptImportedItemsTest() throws IOException {
        Item chocolate = new Item.Builder()
                              .withDescription("1 imported box of chocolates")
                              .withType(ItemType.FOOD)
                              .imported(true)
                              .withPrice(10.00)
                              .build();
        Item perfume = new Item.Builder()
                            .withDescription("1 imported bottle of perfume")
                            .imported(true)
                            .withPrice(47.50)
                            .build();
        List<Item> items = new ArrayList<>();
        items.add(chocolate);
        items.add(perfume);
        assertEquals(getFile(importedItems), receipt.createReceiptString(items));
    }

    @Test
    public void printReceiptMixedItemsTest() throws IOException {
        Item importedPerfume = new Item.Builder()
                                    .withDescription("1 imported bottle of perfume")
                                    .imported(true)
                                    .withPrice(27.99)
                                    .build();
        Item perfume = new Item.Builder().withDescription("1 bottle of perfume").withPrice(18.99).build();
        Item pills = new Item.Builder()
                          .withDescription("1 packet of headache pills")
                          .withType(ItemType.MEDICINE)
                          .withPrice(9.75)
                          .build();
        Item importedChocolate = new Item.Builder()
                                      .withDescription("1 imported box of chocolates")
                                      .withType(ItemType.FOOD)
                                      .imported(true)
                                      .withPrice(11.25)
                                      .build();
        List<Item> items = new ArrayList<>();
        items.add(importedPerfume);
        items.add(perfume);
        items.add(pills);
        items.add(importedChocolate);
        assertEquals(getFile(mixedItems), receipt.createReceiptString(items));
    }
}
