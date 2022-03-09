package com.example.whoami.service;

import com.example.whoami.exception.InvalidSizeSpecException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

/**
 * This class contains a single public method that
 * is utilized for generating a response for the /data
 * endpoint.
 *
 * NOTE: Currently the approach I am taking in this class is imperfect.
 * The method generatePayloadFromUnitSpec would need to be configured to allow
 * for Long StringBuilder sizes in order to support gb sized payloads, however,
 * currently I am only using Integer buffer sizes. There is also RAM considerations
 * that will need to be taken into account if I want to allow for gb sized payloads.
 */
@Service
public class SizeSpecifiedPayloadService {

    // this order is actually important, because their exists a greedy
    // algorithm that loops through this array, and selects the first
    // unit that matchs the suffix of size provided by the user.
    private static final String[] validUnits = {"kb", "mb", "gb", "b"};

    private static class SizeUnitSpecification {
        public String unit;
        public Long byteSize;
        public Long stringSize;
    }

    private int randomInt() {
        return ThreadLocalRandom.current().nextInt(0, 9 + 1);
    }

    /**
     * generates a payload from a specified size.
     */
    public String generatePayloadFromUnitSpec(String sizeUnitString) {

        SizeUnitSpecification sizeSpec = parseUnitSpecComponents(sizeUnitString);

        int intSize = Math.toIntExact(sizeSpec.stringSize);

        StringBuilder builder = LongStream.range(0, intSize)
                .map(x -> randomInt()).collect(
                        () -> new StringBuilder(intSize), // supplier
                        StringBuilder::append,            // accumulator
                        StringBuilder::append);           // combiner

        return builder.toString();
    }

    private Long byteSizeToStringSize(String unit, Long byteSize) {

        long baseBytes = 1;

        switch (unit) {
            case "b":
                baseBytes = 1;
                break;
            case "kb":
                baseBytes = 1024;
                break;
            case "mb":
                baseBytes = 1024 * 1024;
                break;
            case "gb":
                baseBytes = 1024 * 1024 * 1024;
                break;
        }

        return byteSize * baseBytes;
    }

    private SizeUnitSpecification parseUnitSpecComponents(String sizeUnitString) {

        SizeUnitSpecification sizeUnitSpec = new SizeUnitSpecification();

        // parsing units from sizeUnitString
        for (String unit: validUnits) {
            if(sizeUnitString.endsWith(unit)) {
                sizeUnitSpec.unit = unit;
                break;
            }
        }
        if (sizeUnitSpec.unit == null) {
            throw new InvalidSizeSpecException(sizeUnitString, "Failed to find a valid UNIT. " +
                    " The valid units are: " + Arrays.toString(validUnits));
        }

        // parsing size from sizeUnitString
        String sizeString = sizeUnitString.substring(0, sizeUnitString.length() - sizeUnitSpec.unit.length());
        try {
            sizeUnitSpec.byteSize = Long.parseLong(sizeString);
        } catch(NumberFormatException e) {
            throw new InvalidSizeSpecException(sizeUnitString, "Failed to find a valid SIZE. ");
        }

        sizeUnitSpec.stringSize = byteSizeToStringSize(sizeUnitSpec.unit, sizeUnitSpec.byteSize);

        return sizeUnitSpec;
    }

}
