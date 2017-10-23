package sk.flowy.suggestedproductsservice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sk.flowy.suggestedproductsservice.model.CallResponse;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.Supplier;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;
import sk.flowy.suggestedproductsservice.repository.SupplierRepository;

import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SupplierServiceImplTest {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private SupplierRepository supplierRepository;

    private SupplierService supplierService;

    @Before
    public void setup() {
        supplierService = new SupplierServiceImpl(productRepository, supplierRepository);
    }

    @Test
    public void if_product_or_supplier_does_not_exists_in_db_then_error_is_returned() {
        when(productRepository.findOne(1L)).thenReturn(null);
        when(supplierRepository.findOne(8L)).thenReturn(null);

        CallResponse callResponse = supplierService.addProductToSupplier(1L, 8L);

        assertThat(callResponse.hasError()).isTrue();
        assertThat(callResponse.getError()).isEqualTo("Wrong input!");
    }

    @Test
    public void if_product_already_is_connected_to_the_supplier_error_is_returned() {
        Product product = new Product();
        Supplier supplier = new Supplier();
        product.setSuppliers(singletonList(supplier));
        supplier.setProducts(singletonList(product));

        when(productRepository.findOne(1L)).thenReturn(product);
        when(supplierRepository.findOne(8L)).thenReturn(supplier);

        CallResponse callResponse = supplierService.addProductToSupplier(1L, 8L);

        assertThat(callResponse.hasError()).isTrue();
        assertThat(callResponse.getError()).isEqualTo("Product is already connected to the supplier.");
    }

    @Test
    public void when_there_is_no_connection_between_supplier_and_product_then_it_is_created() {
        Product product = new Product();
        product.setSuppliers(new ArrayList<>());
        Supplier supplier = new Supplier();
        supplier.setProducts(new ArrayList<>());

        when(productRepository.findOne(1L)).thenReturn(product);
        when(supplierRepository.findOne(8L)).thenReturn(supplier);

        CallResponse callResponse = supplierService.addProductToSupplier(1L, 8L);

        verify(supplierRepository).save(supplier);
        assertThat(supplier.getProducts()).isEqualTo(singletonList(product));
        assertThat(product.getSuppliers()).isEqualTo(singletonList(supplier));
        assertThat(callResponse.hasError()).isFalse();
        assertThat(callResponse.getSuccess()).isEqualTo("Product is successfully connected to the supplier");
    }
}