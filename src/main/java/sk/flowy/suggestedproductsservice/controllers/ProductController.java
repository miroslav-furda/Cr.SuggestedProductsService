package sk.flowy.suggestedproductsservice.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.FoundProduct;
import sk.flowy.suggestedproductsservice.services.EanService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Log4j
@RequestMapping("/api")
public class ProductController {

    private final EanService eanService;

    @Autowired
    public ProductController(EanService eanService) {
        this.eanService = eanService;
    }

    @RequestMapping(value = "/product/{ean}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FoundProduct> getProductByEan(@PathVariable("ean") String ean) {
        FoundProduct product = eanService.getProductByEan(ean);

        if (product == null) {
            return new ResponseEntity<>(product, INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product, OK);
    }

}
