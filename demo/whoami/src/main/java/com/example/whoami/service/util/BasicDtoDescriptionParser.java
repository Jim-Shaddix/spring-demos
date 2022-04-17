package com.example.whoami.service.util;

import com.example.whoami.dto.description.BasicDescriptionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasicDtoDescriptionParser {

    private static final String JSON_KEY_SUFFIX = "-def";

    private static final Map<Class<?>, Map<String, String>> dtoDescriptions = new HashMap<>();

    private Map<String, String> parseDtoDescriptionFromAnnotations(Class<?> clazz) {
        Map<String, String> fieldNamesToDescriptions = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("description")) {
                continue;
            }
            Schema schema = field.getAnnotation(Schema.class);
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            String jsonFieldName = jsonProperty.value();
            fieldNamesToDescriptions.put(jsonFieldName + JSON_KEY_SUFFIX, schema.description());
        }
        return fieldNamesToDescriptions;
    }

    public Map<String, String> parseDtoDescription(Class<? extends BasicDescriptionDto> clazz) {
        if (!dtoDescriptions.containsKey(clazz)) {
            dtoDescriptions.put(clazz, parseDtoDescriptionFromAnnotations(clazz));
        }
        return dtoDescriptions.get(clazz);
    }

    public void setDescription(BasicDescriptionDto basicDescriptionDto) {
        Map<String, String> map = parseDtoDescription(basicDescriptionDto.getClass());
        basicDescriptionDto.setDefinition(map);
    }

}
