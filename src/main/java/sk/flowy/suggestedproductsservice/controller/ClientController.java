package sk.flowy.suggestedproductsservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Log4j
@Api(value="client-controller", description="This micro service represent list of available products")
public class ClientController {

    @Autowired
    private ProductRepository productRepository;


    @ApiOperation(value = "View a list of available products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/products", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> readProducts() {

        return new ResponseEntity<>(productRepository.readProducts(), HttpStatus.OK);
    }
}