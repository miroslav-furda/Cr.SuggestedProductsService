package sk.flowy.suggestedproductsservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.exceptions.ProductNotFoundException;
import sk.flowy.suggestedproductsservice.exceptions.ProductNotSavedException;
import sk.flowy.suggestedproductsservice.model.*;
import sk.flowy.suggestedproductsservice.service.EanService;
import sk.flowy.suggestedproductsservice.service.ProductDataService;
import sk.flowy.suggestedproductsservice.service.SupplierService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Simple rest api for working with products.
 */
@RestController
@RequestMapping("/api")
@Log4j
@Api(value = "product-controller", description = "Micro service for creating new product and finding product via ean number.")
public class ProductController {

    private final ProductDataService productDataService;
    private final EanService eanService;
    private final SupplierService supplierService;

    /**
     * Constructor.
     *
     * @param productDataService service for manipulating products.
     * @param eanService         service for eans.
     * @param supplierService    service for manipulating with suppliers.
     */
    @Autowired
    public ProductController(ProductDataService productDataService, EanService eanService, SupplierService
            supplierService) {
        this.productDataService = productDataService;
        this.eanService = eanService;
        this.supplierService = supplierService;
    }

    @ApiOperation(value = "Create new product")
    @RequestMapping(value = "/product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody NewProduct newProduct) {
        if (newProduct.getEan() == null || StringUtils.isNotEmpty(newProduct.getName()) || StringUtils.isNotEmpty(newProduct.getSupplier())) {
            throw new ProductNotSavedException();
        }

        log.info(String.format("Creating new product %s", newProduct));
        Product product = productDataService.setDataForProductAndSaveIntoDatabase(newProduct);
        return new ResponseEntity<>(product, OK);
    }

    @ApiOperation(value = "Find product via ean number")
    @RequestMapping(value = "/product/{ean}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptProduct> getProductByEan(@PathVariable("ean") String ean) {
        log.info(String.format("Searching for product by ean %s", ean));
        ReceiptProduct product = eanService.getProductByEan(ean);

        if (product == null) {
            log.warn("Product with ean " + ean + "was not found in database.");
            throw new ProductNotFoundException();
        }
        return new ResponseEntity<>(product, OK);
    }

    @RequestMapping(value = "/product/supplier", method = POST, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Connects product with supplier")
    public ResponseEntity<CallResponse> addProductToSupplier(@RequestBody ProductSupplierWrapper
                                                                     productSupplierWrapper) {
        CallResponse callResponse = supplierService.addProductToSupplier(productSupplierWrapper.getProductId(),
                productSupplierWrapper.getDelivererId());

        if (callResponse.hasError()) {
            log.warn(String.format("Cannot add product %s to supplier %s. Reason: %s", productSupplierWrapper
                            .getProductId(), productSupplierWrapper.getDelivererId()
                    , callResponse.getError()));
            return new ResponseEntity<>(callResponse, BAD_REQUEST);
        }

        return new ResponseEntity<>(callResponse, OK);
    }
}
