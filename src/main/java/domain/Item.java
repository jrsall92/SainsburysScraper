package domain;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getKcal_per_100g() {
        return kcal_per_100g;
    }

    public void setKcal_per_100g(int kcal_per_100g) {
        this.kcal_per_100g = kcal_per_100g;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
