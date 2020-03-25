import service.ScrappingService;

public class Application {

    public static void main(String[] args) {
        ScrappingService service = new ScrappingService();
        service.getProducts(args[0]);
    }

}
