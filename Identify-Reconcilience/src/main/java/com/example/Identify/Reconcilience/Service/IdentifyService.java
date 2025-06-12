package com.example.Identify.Reconcilience.Service;

import com.example.Identify.Reconcilience.DTO.ContactDTO;
import com.example.Identify.Reconcilience.Entity.ContactEntity;
import com.example.Identify.Reconcilience.Repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class IdentifyService {

    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public ContactEntity addContact(ContactDTO contactDTO){
        ContactEntity contactEntity = new ContactEntity();
        List<ContactEntity> contacts = contactRepository.findAll();

        boolean isPresent = false;
        ContactEntity primaryContact = new ContactEntity();

        for(ContactEntity contact : contacts){ // Iterating through all records
            if(contact.getEmail().equals(contactDTO.getEmail()) || contact.getPhoneNumber().equals(contactDTO.getPhoneNumber())){   // Checking for similarity in phone number and email in primary records

                if(!isPresent){
                    primaryContact.setId(contact.getId());
                    isPresent = true;
                    continue;
                }

                contactRepository.update(primaryContact.getId(), contact.getId());  // If found the link precedence is set to secondary and linked id is added and saved to database

            }
        }

        contactEntity.setPhoneNumber(contactDTO.getPhoneNumber());
        contactEntity.setEmail(contactDTO.getEmail());
        contactEntity.setCreatedAt(LocalDateTime.now());
        contactEntity.setUpdatedAt(LocalDateTime.now());

        if(isPresent){
            contactEntity.setLinkedId(primaryContact.getId());
            contactEntity.setLinkPrecedence("secondary");
            contactRepository.save(contactEntity);
        }else{
            contactEntity.setLinkPrecedence("primary");
            contactRepository.save(contactEntity);
        }
        return contactEntity;

    }
}
