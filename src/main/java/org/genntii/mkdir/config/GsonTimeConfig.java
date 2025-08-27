package org.genntii.mkdir.config;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author mkdir
 * @since 2025/08/22 14:15
 */
@Configuration
public class GsonTimeConfig implements WebMvcConfigurer {

    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .setPrettyPrinting().create();
    }


    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }



    public static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }

    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);

        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }

}
