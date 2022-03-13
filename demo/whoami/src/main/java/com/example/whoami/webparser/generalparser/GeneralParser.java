package com.example.whoami.webparser.generalparser;

import com.example.whoami.webparser.Header;
import com.example.whoami.webparser.flavioparser.FlavioHeader;
import com.example.whoami.webparser.flavioparser.FlavioHeaderParser;
import com.example.whoami.webparser.rfcparser.Rfc2616Header;
import com.example.whoami.webparser.rfcparser.Rfc2616HeaderParser;
import com.example.whoami.webparser.spec.HeaderSpec;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@Service
@AllArgsConstructor
public class GeneralParser {

    private final FlavioHeaderParser flavioHeaderParser;
    private final Rfc2616HeaderParser rfc2616HeaderParser;

    private Map<String, Integer> createIndexMap(List<? extends Header> headerList) {
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < headerList.size(); i++) {
            indexMap.put(headerList.get(i).getName(), i);
        }
        return indexMap;
    }

    private void setFieldsFromRfc(
            @NonNull HeaderSpec headerSpec,
            @Nullable Rfc2616Header rfcHeader) {
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
            @Nullable Rfc2616Header rfcHeader,
            @Nullable FlavioHeader flavioHeader) {

        HeaderSpec headerSpec = new HeaderSpec();

        setFieldsFromRfc(headerSpec, rfcHeader);
        setFieldsFromFlavio(headerSpec, flavioHeader);

        return headerSpec;
    }

    public List<HeaderSpec> createAllHeaderSpecs() {

        List<FlavioHeader> flavioHeaders =  flavioHeaderParser.parseHeaders();
        List<Rfc2616Header> rfcHeaders = rfc2616HeaderParser.parseHeaders();

        Map<String, Integer> rfcIndexMap = createIndexMap(rfcHeaders);

        // populate all the header specifications
        List<HeaderSpec> headerSpecs = new ArrayList<>();
        {
            FlavioHeader flavioHeader;
            String rfcHeaderName;
            HeaderSpec spec;
            Integer rfcIndex;
            for (int i = 0; i < flavioHeaders.size(); i++) {
                flavioHeader = flavioHeaders.get(i);
                rfcHeaderName = flavioHeader.getName();
                rfcIndex = rfcIndexMap.getOrDefault(rfcHeaderName, null);
                if (rfcIndex == null) {
                    log.warning("Could not find an rfc-header that matches the flavio-header: " +
                            flavioHeader.getName());
                    spec = createHeaderSpec(null, flavioHeader);
                } else {
                    spec = createHeaderSpec(rfcHeaders.get(rfcIndex), flavioHeader);
                }
                headerSpecs.add(spec);
            }
        }

        return headerSpecs;
    }

}
