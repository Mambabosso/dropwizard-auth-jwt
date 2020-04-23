package com.github.mambabosso.dropwizard.auth.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;

public class DefaultConverter<T extends Principal & Serializable> implements Converter<T, Map<String, Object>> {

    private final Class<T> tClass;

    public DefaultConverter(final Class<T> tClass) {
        this.tClass = Objects.requireNonNull(tClass);
    }

    @Override
    public Map<String, Object> to(T value) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(value, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public T from(Map<String, Object> value) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(value, tClass);
    }

}
