package sk.codexa.cr.suggestedproductsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sk.codexa.cr.suggestedproductsservice.model.*;
import sk.codexa.cr.suggestedproductsservice.security.TokenRepository;
import sk.codexa.cr.suggestedproductsservice.service.EanService;
import sk.codexa.cr.suggestedproductsservice.service.ProductDataService;
import sk.codexa.cr.suggestedproductsservice.service.SupplierService;
import sk.codexa.cr.suggestedproductsservice.utils.ProductUtils;
import sk.flowy.suggestedproductsservice.model.*;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {
    private static final String VALID_TOKEN = "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYyLCJpc3MiOiJodHRwOlwvXC9sYWJhcy5wcm9taXNlby5jb21cL2FwcFwvcHVibGljXC9hcGlcL2F1dGhlbnRpY2F0ZSIsImlhdCI6MTUwODIzMjIyMSwiZXhwIjoxNTA4NDI0MjIxLCJuYmYiOjE1MDgyMzIyMjEsImp0aSI6IjdlMmQ1NTg2Yzg4YjAzMzE0NDAzZDRmM2U3ODIyMjAzIn0.ujt2PXYrlmHVcL1GZo5ByZHcHdBaPr-dkCgtvBx8uG8";
    private static final String RIGHT_EAN = "123456";
    private static final String INVALID_EAN = "invalid";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EanService eanService;

    @MockBean
    private ProductDataService productDataService;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private SupplierService supplierService;

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

        receiptProduct = ProductUtils.convertProduct(product);

        when(tokenRepository.checkTokenValidity(anyString())).thenReturn(new CallResponse("success", null));
    }

    @Test
    public void if_product_is_not_set_name_then_return_bad_request() throws Exception {
        Product productMock = mock(Product.class);
        NewProduct newProduct = new NewProduct();
        newProduct.setEan(Arrays.asList("1", "2"));
        newProduct.setSupplier("123");
        newProduct.setPicture("picture");

        when(productDataService.setDataForProductAndSaveIntoDatabase(newProduct)).thenReturn(productMock);

        mvc.perform(post("/api/product").header(AUTHORIZATION, VALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newProduct)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void if_ean_is_in_database_then_right_product_is_returned() throws Exception {
        when(eanService.getProductByEan(RIGHT_EAN)).thenReturn(receiptProduct);
        mvc.perform(get("/api/product/" + RIGHT_EAN).header(AUTHORIZATION, VALID_TOKEN).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":10,\"name\":\"Horalka\",\"eans\":[{\"id\":null,\"type\":null," +
                        "\"value\":null,\"createdAt\":null,\"updatedAt\":null,\"deletedAt\":null}]}"));
    }

    @Test
    public void when_suppliers_service_respons_with_error_then_error_is_returned() throws Exception {
        when(supplierService.addProductToSupplier(anyLong(), anyLong())).thenReturn(new CallResponse(null, "Not " +
                "possible"));

        mvc.perform(post("/api/product/supplier").header(AUTHORIZATION, VALID_TOKEN).accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE).content("{\"delivererId\":\"8\",\"productId\":\"1\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_suppliers_service_respons_with_success_then_success_is_returned() throws Exception {
        when(supplierService.addProductToSupplier(anyLong(), anyLong())).thenReturn(new CallResponse("Success", null));

        ProductSupplierWrapper wrapper = new ProductSupplierWrapper(8L, 1L);

        mvc.perform(post("/api/product/supplier").header(AUTHORIZATION, VALID_TOKEN).header(CONTENT_TYPE,
                APPLICATION_JSON_VALUE).content(new ObjectMapper()
                .writeValueAsString(wrapper)))
                .andExpect(status().isOk());
    }

    @Test
    public void if_ean_is_not_found_exception_is_thrown() throws Exception {
        when(eanService.getProductByEan(INVALID_EAN)).thenReturn(null);
        mvc.perform(get("/api/product/" + INVALID_EAN).header(AUTHORIZATION, VALID_TOKEN).accept(APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}