package com.jsalliaris.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class Item {

    private String title;
    private double unit_price;
    private int kcal_per_100g;
    private String description;

    public Item(String title, double unit_price, int kcal_per_100g, String description) {
        this.title = title;
        this.unit_price = unit_price;
        this.kcal_per_100g = kcal_per_100g;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public double getUnit_price() {
        return unit_price;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getKcal_per_100g() {
        return kcal_per_100g;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", unit_price=" + unit_price +
                ", kcal_per_100g=" + kcal_per_100g +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Double.compare(item.unit_price, unit_price) == 0 &&
                kcal_per_100g == item.kcal_per_100g &&
                Objects.equals(title, item.title) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, unit_price, kcal_per_100g, description);
    }
}
