package domain;

import com.fasterxml.jackson.annotation.JsonInclude;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getKcal_per_100g() {
        return kcal_per_100g >= 0 ? String.valueOf(kcal_per_100g) : null;
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
}
