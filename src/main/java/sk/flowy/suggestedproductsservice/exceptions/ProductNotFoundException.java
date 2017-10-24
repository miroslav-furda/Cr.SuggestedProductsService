package sk.flowy.suggestedproductsservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Exception thrown by api when request product cannot be found in database.
 */
@ResponseStatus(value = BAD_REQUEST, reason = "Product not found in database!")
public class ProductNotFoundException extends RuntimeException {
}