package com.slobx.cra.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.math.BigInteger;
import java.util.UUID;

public class Utils {

    public static String getUuid() {
        return "C" + String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }

    public static String mapToJson(final Object object) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object).replaceAll("[\n\r\t]", "");
    }


}
