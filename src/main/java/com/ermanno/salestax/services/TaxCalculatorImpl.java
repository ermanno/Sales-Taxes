package com.ermanno.salestax.services;

import java.util.Arrays;
import java.util.Collection;

import com.ermanno.salestax.valueobjects.Item;
import com.ermanno.salestax.valueobjects.ItemType;

public class TaxCalculatorImpl implements TaxCalculator {
    private static final double ROUND_OFF = 0.05;
    private static final double BASIC_SALES_TAX_RATE = 0.1;
    private static final double IMPORT_DUTY_SALES_TAX = 0.05;
    private static final Collection<ItemType> ITEM_TYPE_EXCLUDED_FROM_BASIC_SALES_TAX = 
            Arrays.asList(ItemType.BOOK, ItemType.FOOD, ItemType.MEDICINE);

    private boolean isExcludedFromBasicSalesTax(final Item item) {
        return ITEM_TYPE_EXCLUDED_FROM_BASIC_SALES_TAX.contains(item.getType());
    }

    private double round(final double number) {
        return Math.ceil(number / ROUND_OFF) * ROUND_OFF;
    }

    /* (non-Javadoc)
     * @see com.ermanno.salestax.services.TaxCalculator#calculateBasicSalesTax(com.ermanno.salestax.valueobjects.Item)
     */
    @Override
    public double calculateBasicSalesTax(final Item item) {
        if (isExcludedFromBasicSalesTax(item))
            return 0;
        else
            return round(item.getPrice() * BASIC_SALES_TAX_RATE);
    }

    /* (non-Javadoc)
     * @see com.ermanno.salestax.services.TaxCalculator#calculateImportDutySales(com.ermanno.salestax.valueobjects.Item)
     */
    @Override
    public double calculateImportDutySalesTax(final Item item) {
        if (item.isImported())
            return round(item.getPrice() * IMPORT_DUTY_SALES_TAX);
        else
            return 0;
    }

    /* (non-Javadoc)
     * @see com.ermanno.salestax.services.TaxCalculator#calculateTaxes(com.ermanno.salestax.valueobjects.Item)
     */
    @Override
    public double calculateTaxes(final Item item) {
        return calculateImportDutySalesTax(item) + calculateBasicSalesTax(item);
    }
}
