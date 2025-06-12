package com.example.Identify.Reconcilience.Service;

import com.example.Identify.Reconcilience.DTO.ContactDTO;
import com.example.Identify.Reconcilience.Entity.ContactEntity;
import com.example.Identify.Reconcilience.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class IdentifyService {

    @Autowired
    ContactRepository contactRepository;

    public ContactEntity addContact(ContactDTO contactDTO){
        ContactEntity contactEntity = new ContactEntity();
        List<ContactEntity> contacts = contactRepository.findAll();


        for(ContactEntity contact : contacts){ // Iterating through all records
            if(contact.getEmail().equals(contactDTO.getEmail()) || contact.getPhoneNumber().equals(contactDTO.getPhoneNumber())){   // Checking for similarity in phone number and email in primary records
                contactEntity.setPhoneNumber(contactDTO.getPhoneNumber());
                contactEntity.setEmail(contactDTO.getEmail());
                contactEntity.setLinkPrecedence("secondary");
                contactEntity.setLinkedId(contact.getId());
                contactEntity.setCreatedAt(LocalDateTime.now());
                contactEntity.setUpdatedAt(LocalDateTime.now());
                contactRepository.save(contactEntity);  // If found the link precedence is set to secondary and linked id is added and saved to database
                return contactEntity;
            }
        }
        // If no similarity found then the contact is added as primary 
        contactEntity.setPhoneNumber(contactDTO.getPhoneNumber());
        contactEntity.setEmail(contactDTO.getEmail());
        contactEntity.setLinkPrecedence("primary");
        contactEntity.setCreatedAt(LocalDateTime.now());
        contactEntity.setUpdatedAt(LocalDateTime.now());
        contactRepository.save(contactEntity);
        return contactEntity;


    }
}
