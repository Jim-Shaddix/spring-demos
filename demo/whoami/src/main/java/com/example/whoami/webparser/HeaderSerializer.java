package com.example.whoami.webparser;

import com.example.whoami.webparser.flavioparser.FlavioHeader;
import com.example.whoami.webparser.flavioparser.FlavioHeaderParser;
import com.example.whoami.webparser.rfcparser.RfcHeader;
import com.example.whoami.webparser.rfcparser.RfcHeaderParser;
import com.example.whoami.webparser.spec.HeaderSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
@AllArgsConstructor
public class HeaderSerializer {

    FlavioHeaderParser flavioHeaderParser;
    RfcHeaderParser rfcHeaderParser;
    ObjectMapper objectMapper;

    private Map<String, Integer> createIndexMap(List<? extends Header> headerList) {
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < headerList.size(); i++) {
            indexMap.put(headerList.get(i).getName(), i);
        }
        return indexMap;
    }

    private void setFieldsFromRfc(
            @NonNull HeaderSpec headerSpec,
            @Nullable RfcHeader rfcHeader) {
       if (rfcHeader != null) {
           headerSpec.setName(rfcHeader.getName());
           headerSpec.setLongDescription(rfcHeader.getDefinition());
       }
    }

    private void setFieldsFromFlavio(
            @NonNull HeaderSpec headerSpec,
            @Nullable FlavioHeader flavioHeader) {
        if (flavioHeader != null) {
            headerSpec.setName(flavioHeader.getName());
            headerSpec.setShortDescription(flavioHeader.getDefinition());
            headerSpec.setExample(flavioHeader.getExample());
            headerSpec.setType(flavioHeader.getHeaderType().toString());
        }
    }

    private HeaderSpec createHeaderSpec(
            @Nullable RfcHeader rfcHeader,
            @Nullable FlavioHeader flavioHeader) {

        HeaderSpec headerSpec = new HeaderSpec();

        setFieldsFromRfc(headerSpec, rfcHeader);
        setFieldsFromFlavio(headerSpec, flavioHeader);

        return headerSpec;
    }

    private List<HeaderSpec> createAllHeaderSpecs() {

        List<FlavioHeader> flavioHeaders =  flavioHeaderParser.parseHeaders();
        List<RfcHeader> rfcHeaders = rfcHeaderParser.parseHeaders();

        // sort header lists in place
        //Collections.sort(flavioHeaders);
        //Collections.sort(rfcHeaders);

        Map<String, Integer> flavioIndexMap = createIndexMap(flavioHeaders);

        // populate all the header specifications
        List<HeaderSpec> headerSpecs = new ArrayList<>();
        {
            RfcHeader rfcHeader;
            String rfcHeaderName;
            HeaderSpec spec;
            Integer flavioIndex;
            for (int i = 0; i < rfcHeaders.size(); i++) {
                rfcHeader = rfcHeaders.get(i);
                rfcHeaderName = rfcHeader.getName();
                flavioIndex = flavioIndexMap.getOrDefault(rfcHeaderName, null);
                if (flavioIndex == null) {
                    spec = createHeaderSpec(rfcHeader, null);
                } else {
                    spec = createHeaderSpec(rfcHeader, flavioHeaders.get(flavioIndex));
                }
                headerSpecs.add(spec);
            }
        }

        return headerSpecs;
    }

    /**
     * Serialize all the header definitions to a JSON file.
     * @throws IOException
     */
    public void serializeHeaders() throws IOException {
        List<HeaderSpec> headerSpecs = createAllHeaderSpecs();
        try {
            objectMapper.writeValue(Paths.get("books.json").toFile(), headerSpecs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
