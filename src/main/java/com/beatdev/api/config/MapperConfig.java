package com.beatdev.api.config;

import com.beatdev.api.exception.MapperException;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct's configuration class.
 */
@org.mapstruct.MapperConfig(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unexpectedValueMappingException = MapperException.class)
public interface MapperConfig {
}