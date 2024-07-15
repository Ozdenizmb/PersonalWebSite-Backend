package com.baranozdeniz.personalwebsite.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class PageMapperHelper {

    public static <S, T> Page<T> mapEntityPageToDtoPage(Page<S> source, BaseMapper<S, T> mapper) {
        List<T> dtoList = source.getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, PageRequest.of(source.getNumber(), source.getSize()), source.getTotalElements());
    }

}
