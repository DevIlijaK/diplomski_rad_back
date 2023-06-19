package com.diplomski.obavestavanje.nastavnika.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomSqlDateDeserializer extends JsonDeserializer<Date> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String dateString = jsonParser.getValueAsString();
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, FORMATTER);
        return java.sql.Date.valueOf(localDateTime.toLocalDate());
    }
}
