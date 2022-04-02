package com.example.whoami.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * this exception indicates that the Size Specification, for the data size to
 * returned, could not be interpreted as a valid Specification.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSizeSpecException extends RuntimeException {

    private static final String formatMessage = "An invalid size specification was requested." +
            " The string %s could not be interpreted as a valid size. ";

    private final String sizeSpec;

    public InvalidSizeSpecException(String sizeSpec, String errorMsg) {
        super(String.format(formatMessage, sizeSpec) + errorMsg);
        this.sizeSpec = sizeSpec;
    }

    public String getSizeSpec() {
        return sizeSpec;
    }
}
