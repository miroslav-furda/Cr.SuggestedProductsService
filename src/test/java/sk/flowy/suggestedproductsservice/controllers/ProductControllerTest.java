package sk.flowy.suggestedproductsservice.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.ReceiptProduct;
import sk.flowy.suggestedproductsservice.security.CallResponse;
import sk.flowy.suggestedproductsservice.security.TokenRepository;
import sk.flowy.suggestedproductsservice.services.EanService;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.flowy.suggestedproductsservice.utils.ProductUtils.convertProduct;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static final String VALID_TOKEN = "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYyLCJpc3MiOiJodHRwOlwvXC9sYWJhcy5wcm9taXNlby5jb21cL2FwcFwvcHVibGljXC9hcGlcL2F1dGhlbnRpY2F0ZSIsImlhdCI6MTUwODIzMjIyMSwiZXhwIjoxNTA4NDI0MjIxLCJuYmYiOjE1MDgyMzIyMjEsImp0aSI6IjdlMmQ1NTg2Yzg4YjAzMzE0NDAzZDRmM2U3ODIyMjAzIn0.ujt2PXYrlmHVcL1GZo5ByZHcHdBaPr-dkCgtvBx8uG8";
    private static final String AUTHORIZATION = "Authorization";
    private static final String RIGHT_EAN = "123456";
    private static final String INVALID_EAN = "invalid";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EanService eanService;

    @MockBean
    private TokenRepository tokenRepository;

    private ReceiptProduct receiptProduct;

    @Before
    public void setup() {
        Ean ean = new Ean();
        ean.setValue(RIGHT_EAN);

        Product product = new Product();
        product.setEans(singletonList(new Ean()));
        product.setId(10L);
        product.setName("Horalka");
        ean.setProduct(product);

        receiptProduct = convertProduct(product);

        when(tokenRepository.checkTokenValidity(anyString())).thenReturn(new CallResponse("success", null));
    }

    @Test
    public void if_ean_is_in_database_then_right_product_is_returned() throws Exception {
        when(eanService.getProductByEan(RIGHT_EAN)).thenReturn(receiptProduct);
        mockMvc.perform(get("/api/product/" + RIGHT_EAN).header(AUTHORIZATION, VALID_TOKEN).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":10,\"name\":\"Horalka\",\"eans\":[{\"id\":null,\"type\":null," +
                        "\"value\":null,\"createdAt\":null,\"updatedAt\":null,\"deletedAt\":null}]}"));
    }

    @Test
    public void if_ean_is_not_found_exception_is_thrown() throws Exception {
        when(eanService.getProductByEan(INVALID_EAN)).thenReturn(null);
        mockMvc.perform(get("/api/product/" + INVALID_EAN).header(AUTHORIZATION, VALID_TOKEN).accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}