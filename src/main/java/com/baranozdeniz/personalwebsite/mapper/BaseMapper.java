package com.baranozdeniz.personalwebsite.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface BaseMapper<S, T> {

    T toDto(S s);

    @InheritInverseConfiguration
    S toEntity(T t);

    @InheritConfiguration
    List<T> toDtoList(List<S> sourceList);

    @InheritConfiguration
    List<S> toEntityList(List<T> targetList);

}
