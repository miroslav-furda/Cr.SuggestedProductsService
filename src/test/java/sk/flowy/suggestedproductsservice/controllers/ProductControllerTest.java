package sk.flowy.suggestedproductsservice.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sk.flowy.suggestedproductsservice.TestConfiguration;
import sk.flowy.suggestedproductsservice.exceptions.ProductNotFoundException;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.EanRepository;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Import(TestConfiguration.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EanRepository eanRepository;

    @Before
    public void setup() {
        Ean ean = new Ean();
        ean.setValue("1234560");

        Product product = new Product();
        product.setEans(singletonList(new Ean()));
        product.setId(10L);
        product.setName("Horalka");
        ean.setProduct(product);

        when(eanRepository.findByValue("1234560")).thenReturn(ean);
        when(eanRepository.findByValue("invalid")).thenReturn(null);
    }

    @Test
    public void if_ean_is_in_database_then_right_product_is_returned() throws Exception {
        mockMvc.perform(get("/api/product/1234560").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string("Honda Civic"));
    }

    @Test(expected = ProductNotFoundException.class)
    public void if_ean_is_not_found_exception_is_thrown() {

    }
}