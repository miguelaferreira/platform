package com.me.miguelferreira.platform.controllers;


import com.me.miguelferreira.platform.model.GeographicalLocation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GeographicalLocationConverter implements Converter<String, GeographicalLocation> {

    @Override
    public GeographicalLocation convert(final String source) {
        final String[] parts = source.split(",");
        return new GeographicalLocation(Double.valueOf(parts[0]), Double.valueOf(parts[1]));
    }

}
