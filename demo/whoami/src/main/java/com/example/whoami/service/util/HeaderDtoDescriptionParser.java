package com.example.whoami.service.util;

import com.example.whoami.dto.HeaderSpec;
import com.example.whoami.dto.component.RequestHeaderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeaderDtoDescriptionParser {

    private final List<HeaderSpec> headerSpecs;

    public void setHeaderDescription(RequestHeaderDto requestHeaderDto) {

        // find spec for the request dto
        Optional<HeaderSpec> optionalSpec = headerSpecs.stream()
                .filter(spec -> {
                    return spec.getName()
                            .equalsIgnoreCase(requestHeaderDto.getName());
                }).findAny();

        // if the spec was found, then set the fields in the DTO.
        if (optionalSpec.isPresent()) {
            HeaderSpec spec = optionalSpec.get();
            requestHeaderDto.setType(String.valueOf(spec.getType()));
            requestHeaderDto.setType(spec.getType());
            requestHeaderDto.setLink(spec.getLink());
            requestHeaderDto.setDescription(spec.getDescription());
        }

    }

}
