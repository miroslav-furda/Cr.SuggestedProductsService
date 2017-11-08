package sk.codexa.cr.suggestedproductsservice.security;

import sk.codexa.cr.suggestedproductsservice.model.CallResponse;

/**
 * Retrieves information about validity of token input.
 */
public interface TokenRepository {

    /**
     * Checks validity of token.
     *
     * @param token token taken from http request's authorization header.
     * @return {@link CallResponse} instance that contains either success or error message.
     */
    CallResponse checkTokenValidity(String token);
}
