package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.ContactCreateDto;
import com.baranozdeniz.personalwebsite.dto.ContactDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.ContactMapper;
import com.baranozdeniz.personalwebsite.mapper.PageMapperHelper;
import com.baranozdeniz.personalwebsite.model.Contact;
import com.baranozdeniz.personalwebsite.repository.ContactRepository;
import com.baranozdeniz.personalwebsite.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public UUID createContact(ContactCreateDto contactCreateDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactCreateDto, contact);
        Contact response = repository.save(contact);

        return response.getId();
    }

    @Override
    public ContactDto getContactById(UUID id) {
        Optional<Contact> response = repository.findById(id);

        if(response.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.CONTACT_NOT_FOUND);
        }

        return mapper.toDto(response.get());
    }

    @Override
    public Page<ContactDto> getAllContacts(Pageable pageable) {
        Page<Contact> response = repository.findAll(pageable);

        if(response.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.CONTACT_NOT_FOUND);
        }

        return PageMapperHelper.mapEntityPageToDtoPage(response, mapper);
    }

    @Override
    public Boolean deleteContactById(UUID id) {
        Optional<Contact> response = repository.findById(id);

        if(response.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.CONTACT_NOT_FOUND);
        }

        repository.delete(response.get());

        return true;
    }

}
