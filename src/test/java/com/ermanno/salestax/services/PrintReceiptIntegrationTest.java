package com.ermanno.salestax.services;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ermanno.salestax.valueobjects.Item;
import com.ermanno.salestax.valueobjects.ItemType;

public class PrintReceiptIntegrationTest {
    // from https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
    private String getFile(String fileName) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private Receipt receipt;
    
    @Autowired
    public void setReceipt(Receipt receipt) {
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
        assertEquals(getFile("nonImportedItemsExpectedOutput.txt"), receipt.createReceiptString(items));
    }

    @Test
    public void printReceiptImportedItemsTest() {
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
        assertEquals(getFile("importedItemsExpectedOutput.txt"), receipt.createReceiptString(items));
    }

    @Test
    public void printReceiptMixedItemsText() {
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
        assertEquals(getFile("mixedItemsExpectedOutput.txt"), receipt.createReceiptString(items));
    }
}
