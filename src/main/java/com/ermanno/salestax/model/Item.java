package com.ermanno.salestax.model;

public class Item {
    public static class Builder {
        private String description;
        private double price;
        private boolean isImported = false;
        private ItemType type = ItemType.OTHER;

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withPrice(final double price) {
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
            if (price < 0)
                throw new IllegalStateException("Cannot create item with negative price");
            return new Item(description, price, isImported, type);
        }
    }

    private final String description;
    private final double price;
    private final boolean isImported;
    private final ItemType type;

    private Item(final String description, final double price, final boolean isImported, final ItemType type) {
        this.description = description;
        this.price = price;
        this.isImported = isImported;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }

    public ItemType getType() {
        return type;
    }
}
