package service;

import domain.Item;
import domain.Response;
import domain.Total;

import java.util.Collection;

public class ResponseService {

    private final ScrappingService scrappingService;

    public ResponseService() {
        this.scrappingService = new ScrappingService();
    }

    public Response getResponse(String url){
        Collection<Item> products = scrappingService.getProducts(url);
        Total total = calculateTotal(products);
        return new Response(products, total);
    }

    private Total calculateTotal(Collection<Item> items){
        double gross = items.stream().mapToDouble(Item::getUnit_price).sum();
        return new Total(gross);
    }
}
