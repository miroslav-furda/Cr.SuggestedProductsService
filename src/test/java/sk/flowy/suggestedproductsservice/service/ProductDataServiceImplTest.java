package sk.flowy.suggestedproductsservice.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sk.flowy.suggestedproductsservice.model.NewProduct;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.Supplier;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;
import sk.flowy.suggestedproductsservice.repository.SupplierRepository;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductDataServiceImplTest {


    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private SupplierRepository supplierRepository;
    private ProductDataServiceImpl productDataService;

    @Before
    public void setup() {
        productDataService = new ProductDataServiceImpl(productRepository, supplierRepository);
    }

    @Test
    public void if_supplier_does_not_exists_in_db_then_error_is_returned() {
        NewProduct newProduct = new NewProduct();
        newProduct.setName("a");
        newProduct.setSupplier("sup");
        newProduct.setEan(singletonList("aa"));
        when(supplierRepository.findByName("a")).thenReturn(null);

        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
        when(productDataService.setDataForProductAndSaveIntoDatabase(newProduct)).thenReturn(product);

        Assertions.assertThat(product.getSuppliers()).isNull();
    }

    @Test
    public void if_supplier_exists_in_db_then_product_data_service_save_this_supplier() {
        NewProduct newProduct = new NewProduct();
        newProduct.setName("a");
        newProduct.setSupplier("sup");
        newProduct.setEan(singletonList("aa"));


        Product product = new Product();
        Product productWithSupplier = new Product();
        Supplier supplier = new Supplier();
        productWithSupplier.setSuppliers(singletonList(supplier));

        when(supplierRepository.findByName(newProduct.getSupplier())).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(productRepository.save(product)).thenReturn(productWithSupplier);

        Product productWithData = productDataService.setDataForProductAndSaveIntoDatabase(newProduct);
        Assertions.assertThat(productWithData.getSuppliers()).isNotEmpty();
    }

}