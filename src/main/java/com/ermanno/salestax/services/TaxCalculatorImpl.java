package com.ermanno.salestax.services;

import java.util.List;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.Money;
import com.ermanno.salestax.model.taxes.Tax;

public class TaxCalculatorImpl implements TaxCalculator {
    private List<Tax> taxes;
        
    public TaxCalculatorImpl(List<Tax> taxes) {
        this.taxes = taxes;
    }

    @Override
    public Money calculateTaxes(final Item item) {
        Money total = new Money("0.00");
        for (Tax tax : taxes)
            total.add(tax.calculateTax(item));
        return total;
    }
}
