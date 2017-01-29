package com.ermanno.salestax.services;

import java.util.List;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.taxes.Tax;

public class TaxCalculatorImpl implements TaxCalculator {
    private List<Tax> taxes;
        
    public TaxCalculatorImpl(List<Tax> taxes) {
        this.taxes = taxes;
    }

    @Override
    public double calculateTaxes(final Item item) {
        double total = 0;
        for (Tax tax : taxes)
            total += tax.calculateTax(item);
        return total;
    }
}
