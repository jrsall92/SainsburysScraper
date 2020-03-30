package service;

import com.jsalliaris.Utils.LogUtil;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jsalliaris.domain.Item;
import com.jsalliaris.service.ScrappingService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*;

public class ScrappingServiceTest {

    @Rule
    public WireMockRule rule = new WireMockRule(options().port(8080).withRootDirectory("/resources"));

    @Before
    public void setUp() throws IOException {
        final InputStream inputStreamTopPage = getClass().getClassLoader().getResourceAsStream("__files/top-page.html");
        final String topPageHtml = new String(IOUtils.toByteArray(inputStreamTopPage), StandardCharsets.UTF_8);

        final InputStream inputStreamStrawberries = getClass().getClassLoader().getResourceAsStream("__files/strawberries-400g.html");
        final String strawberriesHtml = new String(IOUtils.toByteArray(inputStreamStrawberries), StandardCharsets.UTF_8);

        final InputStream inputStreamStrawberries_altKcal = getClass().getClassLoader().getResourceAsStream("__files/strawberries-400g-alternative-kcal.html");
        final String strawberriesHtml_altKcal = new String(IOUtils.toByteArray(inputStreamStrawberries_altKcal), StandardCharsets.UTF_8);

        final InputStream inputStreamStrawberries_noKcal = getClass().getClassLoader().getResourceAsStream("__files/strawberries-400g-no-kcal.html");
        final String strawberriesHtml_noKcal = new String(IOUtils.toByteArray(inputStreamStrawberries_noKcal), StandardCharsets.UTF_8);

        stubFor(get(urlEqualTo("/top-page.html")).willReturn(aResponse().withBody(topPageHtml)));
        stubFor(get(urlEqualTo("/strawberries-400g.html")).willReturn(aResponse().withBody(strawberriesHtml)));
        stubFor(get(urlEqualTo("/strawberries-400g-alternative-kcal.html")).willReturn(aResponse().withBody(strawberriesHtml_altKcal)));
        stubFor(get(urlEqualTo("/strawberries-400g-no-kcal.html")).willReturn(aResponse().withBody(strawberriesHtml_noKcal)));

    }

    @Test
    public void getProducts() {
        LogUtil.setDebug(true);
        ScrappingService service = new ScrappingService();
        Collection<Item> products = service.getProducts("http://localhost:8080/top-page.html");

        assertNotNull(products);
        assertEquals(1, products.size());
    }

    @Test
    public void getProductDetails() {
        LogUtil.setDebug(true);
        ScrappingService service = new ScrappingService();
        Item item = service.getProductDetails("http://localhost:8080/strawberries-400g.html");
        assertNotNull(item);
        assertEquals("Sainsbury's Strawberries 400g | Sainsbury's", item.getTitle());
        assertTrue(item.getUnit_price() == 1.75);
        assertTrue(item.getKcal_per_100g() == 33);
        assertEquals("by Sainsbury's strawberries", item.getDescription());
    }

    @Test
    public void getProductDetails_AlternativeKcal() {
        LogUtil.setDebug(true);
        ScrappingService service = new ScrappingService();
        Item item = service.getProductDetails("http://localhost:8080/strawberries-400g-alternative-kcal.html");
        assertNotNull(item);
        assertEquals("Sainsbury's Strawberries 400g | Sainsbury's", item.getTitle());
        assertTrue(item.getUnit_price() == 1.75);
        assertTrue(item.getKcal_per_100g() == 32);
        assertEquals("by Sainsbury's strawberries", item.getDescription());
    }

    @Test
    public void getProductDetails_noKcal() {
        LogUtil.setDebug(true);
        ScrappingService service = new ScrappingService();
        Item item = service.getProductDetails("http://localhost:8080/strawberries-400g-no-kcal.html");
        assertNotNull(item);
        assertEquals("Sainsbury's Strawberries 400g | Sainsbury's", item.getTitle());
        assertTrue(item.getUnit_price() == 1.75);
        assertTrue(item.getKcal_per_100g() == 0);
        assertEquals("by Sainsbury's strawberries", item.getDescription());
    }
}
