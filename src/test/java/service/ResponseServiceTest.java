package service;

import com.jsalliaris.domain.Item;
import com.jsalliaris.domain.Response;
import com.jsalliaris.service.ResponseService;
import com.jsalliaris.service.ScrappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResponseServiceTest {

    private ResponseService responseService;

    private static final double PRICE_1 = 5.25;
    private static final double PRICE_2 = 2.35;
    private static final double VAT = 0.2;

    @Mock
    private ScrappingService scrappingService;

    @Before
    public void setUp() {
        initMocks(this);
        responseService = new ResponseService(scrappingService);
    }

    @Test
    public void getResponse() {
        when(scrappingService.getProducts(anyString())).thenReturn(getMockItems());
        Response response = responseService.getResponse("aURL");
        assertNotNull(response);
        assertNotNull(response.getResults());
        assertEquals(getMockItems(), response.getResults());
        assertNotNull(response.getTotal());
        assertEquals(PRICE_1 + PRICE_2, response.getTotal().getGross(), 0.0);
        assertEquals((PRICE_1 + PRICE_2) * VAT, response.getTotal().getVat(), 0.0);
    }

    private Collection<Item> getMockItems(){
        Set<Item> items = new HashSet<>();
        Item item = new Item("Item1", PRICE_1, 100, "item1 desc");
        items.add(item);
        item = new Item("Item2", PRICE_2, 20, "item2 desc");
        items.add(item);

        return items;
    }

}
