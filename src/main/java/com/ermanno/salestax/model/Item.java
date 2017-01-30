package com.ermanno.salestax.model;

public class Item {
    public static class Builder {
        private String description;
        private Money price;
        private boolean isImported = false;
        private ItemType type = ItemType.OTHER;

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withPrice(final Money price) {
            this.price = price;
            return this;
        }

        public Builder imported(final boolean isImported) {
            this.isImported = isImported;
            return this;
        }

        public Builder withType(final ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            if (price.compareTo(new Money("0.00")) <= 0)
                throw new IllegalStateException("Cannot create item with negative price");
            return new Item(description, price, isImported, type);
        }
    }

    private final String description;
    private final Money price;
    private final boolean isImported;
    private final ItemType type;

    private Item(final String description, final Money price, final boolean isImported, final ItemType type) {
        this.description = description;
        this.price = price;
        this.isImported = isImported;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }

    public ItemType getType() {
        return type;
    }
}
