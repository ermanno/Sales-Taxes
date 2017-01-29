package com.ermanno.salestax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ermanno.salestax.config.SalesTaxConfig;
import com.ermanno.salestax.item.Item;
import com.ermanno.salestax.item.ItemType;
import com.ermanno.salestax.services.ReceiptGenerator;

public class SalesTaxProblemClient {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SalesTaxConfig.class);
        
        ReceiptGenerator receiptGenerator = context.getBean(ReceiptGenerator.class);
        List<Item> input1 = getInput1();
        List<Item> input2 = getInput2();
        List<Item> input3 = getInput3();
        
        System.out.println("Output 1");
        System.out.println(receiptGenerator.createReceiptString(input1));
        System.out.println();
        System.out.println("Output 2");
        System.out.println(receiptGenerator.createReceiptString(input2));
        System.out.println();
        System.out.println("Output 3");
        System.out.println(receiptGenerator.createReceiptString(input3));
        
        context.close();
    }

    private static List<Item> getInput1() {
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
        return items;
    }
    
    private static List<Item> getInput2() {
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
        return items;
    }
    
    private static List<Item> getInput3() {
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
        return items;
    }
}
