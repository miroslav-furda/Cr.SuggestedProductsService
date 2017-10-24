package sk.flowy.suggestedproductsservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.exceptions.ProductNotFoundException;
import sk.flowy.suggestedproductsservice.model.NewProduct;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.ReceiptProduct;
import sk.flowy.suggestedproductsservice.service.ProductDataService;
import sk.flowy.suggestedproductsservice.service.EanService;

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
@Api(value="product-controller", description="This micro service represent create new product")
public class ProductController {

    private final ProductDataService productDataService;
    private final EanService eanService;

    /**
     * Constructor.
     *
     * @param productDataService service for manipulating products.
     * @param eanService service for eans.
     */
    @Autowired
    public ProductController(ProductDataService productDataService, EanService eanService) {
        this.productDataService = productDataService;
        this.eanService = eanService;
    }

    @ApiOperation(value = "Create new product")
    @RequestMapping(value = "/product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody NewProduct newProduct) {
        log.info("Creating new product " + newProduct);
        Product product = productDataService.setDataForProductAndSaveIntoDatabase(newProduct);
        return new ResponseEntity<>(product, OK);
    }

    @RequestMapping(value = "/product/{ean}", method = GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptProduct> getProductByEan(@PathVariable("ean") String ean) {
        log.info("Searching for product by ean " + ean);
        ReceiptProduct product = eanService.getProductByEan(ean);

        if (product == null) {
            log.warn("Product with ean " + ean + "was not found in database.");
            throw new ProductNotFoundException();
        }
        return new ResponseEntity<>(product, OK);
    }
}
