package com.example.whoami.webparser.generalparser;

import com.example.whoami.webparser.Header;
import com.example.whoami.webparser.flavioparser.FlavioHeader;
import com.example.whoami.webparser.flavioparser.FlavioHeaderParser;
import com.example.whoami.webparser.rfcparser.Rfc2616Header;
import com.example.whoami.webparser.rfcparser.Rfc2616HeaderParser;
import com.example.whoami.webparser.spec.HeaderSpec;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Integer> flavioIndexMap = createIndexMap(flavioHeaders);

        // populate all the header specifications
        List<HeaderSpec> headerSpecs = new ArrayList<>();
        {
            Rfc2616Header rfc2616Header;
            String rfcHeaderName;
            HeaderSpec spec;
            Integer flavioIndex;
            for (int i = 0; i < rfcHeaders.size(); i++) {
                rfc2616Header = rfcHeaders.get(i);
                rfcHeaderName = rfc2616Header.getName();
                flavioIndex = flavioIndexMap.getOrDefault(rfcHeaderName, null);
                if (flavioIndex == null) {
                    spec = createHeaderSpec(rfc2616Header, null);
                } else {
                    spec = createHeaderSpec(rfc2616Header, flavioHeaders.get(flavioIndex));
                }
                headerSpecs.add(spec);
            }
        }

        return headerSpecs;
    }

}
