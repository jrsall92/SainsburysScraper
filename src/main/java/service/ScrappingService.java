package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class ScrappingService {

    private HashSet<String> history;
    private HashSet<String> links;

    public ScrappingService() {
        this.history = new HashSet<>();
        this.links = new HashSet<>();
    }

    public void getProducts(String link){
        if(links.add(link) && !history.contains(link)){
            System.out.println("Scraping: " + link);

            try {

                Document doc = Jsoup.connect(link).get();
                Elements productLinks = doc.select("div.productNameAndPromotions h3 a[href]");

                for (Element element : productLinks) {
                    getProductDetails(element.absUrl("href"));
                }

            } catch (IOException e) {
                links.remove(link);
                System.err.println("Couldn't get: " + link);
            }

        }
    }

    public void getProductDetails(String productLink){
        System.out.println("Scraping: " + productLink);

        try {

            Document doc = Jsoup.connect(productLink).get();
            String title = doc.select("div.productTitleDescriptionContainer h1").text();



        } catch (IOException e) {
            System.err.println("Couldn't get: " + productLink);
        }
    }
}
