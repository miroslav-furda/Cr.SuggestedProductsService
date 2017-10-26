package sk.flowy.suggestedproductsservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Exception thrown by api when request product is not save in database.
 */
@ResponseStatus(value = BAD_REQUEST, reason = "Product is not save in database!")
public class ProductNotSavedException extends RuntimeException {
}