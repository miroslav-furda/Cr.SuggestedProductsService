package sk.codexa.cr.suggestedproductsservice.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import sk.codexa.cr.suggestedproductsservice.model.ReceiptProduct;
import sk.codexa.cr.suggestedproductsservice.model.Ean;
import sk.codexa.cr.suggestedproductsservice.model.Product;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProductUtilsTest {

    @Test
    public void receiptProduct_contains_data_from_provided_product() {
        Product product = new Product();
        product.setEans(singletonList(new Ean()));
        product.setId(10L);
        product.setName("Horalka");

        ReceiptProduct receiptProduct = ProductUtils.convertProduct(product);

        Assertions.assertThat(receiptProduct.getEans()).isEqualTo(singletonList(new Ean()));
        assertThat(receiptProduct.getId()).isEqualTo(10L);
        assertThat(receiptProduct.getName()).isEqualTo("Horalka");
    }
}