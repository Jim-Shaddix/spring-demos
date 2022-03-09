package com.example.whoami.exception;

/**
 * this exception indicates that the Size Specification, for the data size to
 * returned, could not be interpreted as a valid Specification.
 */
public class InvalidSizeSpecException extends RuntimeException {

    private static final String formatMessage = "An invalid size specification was requested." +
            " The string %s could not be interpreted as a valid size. ";

    public InvalidSizeSpecException(String sizeSpec, String errorMsg) {
        super(String.format(formatMessage, sizeSpec) + errorMsg);
    }

}
