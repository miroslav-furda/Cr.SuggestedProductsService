package sk.flowy.suggestedproductsservice.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.ReceiptProduct;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static sk.flowy.suggestedproductsservice.utils.ProductUtils.convertProduct;

@RunWith(SpringRunner.class)
public class ProductUtilsTest {

    @Test
    public void receiptProduct_contains_data_from_provided_product() {
        Product product = new Product();
        product.setEans(singletonList(new Ean()));
        product.setId(10L);
        product.setName("Horalka");

        ReceiptProduct receiptProduct = convertProduct(product);

        assertThat(receiptProduct.getEans()).isEqualTo(singletonList(new Ean()));
        assertThat(receiptProduct.getId()).isEqualTo(10L);
        assertThat(receiptProduct.getName()).isEqualTo("Horalka");
    }
}