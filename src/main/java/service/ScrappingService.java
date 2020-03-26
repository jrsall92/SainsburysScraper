package service;

import domain.Item;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static Utils.LogUtil.log;
import static Utils.LogUtil.logError;

public class ScrappingService {

    private final HashSet<String> history;
    private final HashSet<String> links;

    public ScrappingService() {
        this.history = new HashSet<>();
        this.links = new HashSet<>();
    }

    public Collection<Item> getProducts(String link){
        Set<Item> items = new HashSet<>();
        if(links.add(link) && !history.contains(link)){

            log("Scraping: " + link);

            try {

                Document doc = Jsoup.connect(link).get();
                Elements productLinks = doc.select("div.productNameAndPromotions h3 a[href]");

                Item item;
                for (Element element : productLinks) {
                    item = getProductDetails(element.absUrl("href"));
                    if(item != null) {
                        items.add(item);
                    }
                }

                history.add(link);

            } catch (IOException e) {
                links.remove(link);
                logError("Couldn't get: " + link);
            }
        }

        return items;
    }

    public Item getProductDetails(String productLink){

        log("Scraping: " + productLink);

        try {

            Document doc = Jsoup.connect(productLink).get();
            String title = doc.select("div.productTitleDescriptionContainer h1").text();
            double price = sanitizePrice(doc.select("p.pricePerUnit").text());
            String description = doc.select("div.productText p").first().text();

            if(StringUtil.isBlank(description)){
                description = doc.select("div.itemTypeGroup p:not(.statements)").first().text();
            }

            int kcalPer100g = -1;

            try{
                kcalPer100g = sanitizeCalories(doc.select("table.nutritionTable tbody tr").get(1)
                        .select("td").text());
            }
            catch (NullPointerException | IndexOutOfBoundsException e){
                logError("Item with url " + productLink + " doesn't provide calories information");
            }

            return new Item(title, price, kcalPer100g, description);

        } catch (IOException e) {
            logError("Couldn't get: " + productLink);
        }

        return null;
    }

    private int sanitizeCalories(String caloriesText){
        String calories = caloriesText.split(" ")[0];
        if(calories.contains("kcal")){
            calories = calories.replace("kcal","");
        }

        return Integer.parseInt(calories);
    }

    private double sanitizePrice(String priceText){
        String price = priceText.split("£")[1].split("/")[0];
        return Double.parseDouble(price);
    }
}
