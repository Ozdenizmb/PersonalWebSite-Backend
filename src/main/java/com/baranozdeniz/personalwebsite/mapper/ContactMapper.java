package com.baranozdeniz.personalwebsite.mapper;

import com.baranozdeniz.personalwebsite.dto.ContactDto;
import com.baranozdeniz.personalwebsite.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends BaseMapper<Contact, ContactDto> {



}
