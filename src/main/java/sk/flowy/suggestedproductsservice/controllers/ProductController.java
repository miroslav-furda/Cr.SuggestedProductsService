package sk.flowy.suggestedproductsservice.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.exceptions.ProductNotFoundException;
import sk.flowy.suggestedproductsservice.model.CallResponse;
import sk.flowy.suggestedproductsservice.model.ProductSupplierWrapper;
import sk.flowy.suggestedproductsservice.model.ReceiptProduct;
import sk.flowy.suggestedproductsservice.services.EanService;
import sk.flowy.suggestedproductsservice.services.SupplierService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Simple rest api for working with products.
 */
@RestController
@Log4j
@RequestMapping("/api")
public class ProductController {

    private final EanService eanService;
    private final SupplierService supplierService;

    /**
     * Constructor.
     *
     * @param eanService injected ean service.
     */
    @Autowired
    public ProductController(EanService eanService, SupplierService supplierService) {
        this.eanService = eanService;
        this.supplierService = supplierService;
    }

    @RequestMapping(value = "/product/{ean}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptProduct> getProductByEan(@PathVariable("ean") String ean) {
        ReceiptProduct product = eanService.getProductByEan(ean);

        if (product == null) {
            throw new ProductNotFoundException();
        }
        return new ResponseEntity<>(product, OK);
    }

    @RequestMapping(value = "/product/supplier", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CallResponse> addProductToDeliverer(@RequestBody ProductSupplierWrapper
                                                                      productSupplierWrapper) {
        CallResponse callResponse = supplierService.addProductToSupplier(productSupplierWrapper.getProductId(),
                productSupplierWrapper.getDelivererId());

        if (callResponse.hasError()) {
            return new ResponseEntity<>(callResponse, BAD_REQUEST);
        }

        return new ResponseEntity<>(callResponse, OK);
    }
}
