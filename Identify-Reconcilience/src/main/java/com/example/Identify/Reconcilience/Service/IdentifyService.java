package com.example.Identify.Reconcilience.Service;

import com.example.Identify.Reconcilience.DTO.ContactDTO;
import com.example.Identify.Reconcilience.Entity.ContactEntity;
import com.example.Identify.Reconcilience.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IdentifyService {

    @Autowired
    ContactRepository contactRepository;

    public void addContact(ContactDTO contactDTO){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setPhoneNumber(contactDTO.getPhoneNumber());
        contactEntity.setEmail(contactDTO.getEmail());
        contactEntity.setLinkPrecedence("primary");
        contactEntity.setCreatedAt(LocalDateTime.now());
        contactEntity.setUpdatedAt(LocalDateTime.now());
        contactRepository.save(contactEntity);

    }
}
