package com.example.whoami.dto.component;

import com.example.whoami.dto.description.BasicDescriptionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "Information that describes the Local the server is utilizing to process the request.")
public class LocaleDto extends BasicDescriptionDto {

    @Schema(description = "local name used for processing the request")
    @JsonProperty("name")
    private String name;

    @Schema(description = "language used for processing request")
    @JsonProperty("language")
    private String language;

    @Schema(description = "language tag")
    @JsonProperty("language-tag")
    private String languageTag;

    @Schema(description = "country of origin associated with the request")
    @JsonProperty("country")
    private String country;

    //@Schema(description = "Locale Extensions")
    //@JsonProperty("extensions")
    //private String extensions;
    //
    //@Schema(description = "some script")
    //@JsonProperty("script")
    //private String script;
    //
    //@Schema(description = "country variant")
    //@JsonProperty("variant")
    //private String variant;

}
