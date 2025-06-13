package com.example.Identify.Reconcilience.Service;

import com.example.Identify.Reconcilience.DTO.ContactDTO;
import com.example.Identify.Reconcilience.DTO.ReturnTypeDTO;
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
    public ReturnTypeDTO addContact(ContactDTO contactDTO){

        ContactEntity contactEntity = new ContactEntity();           // Object used for saving in database table
        ReturnTypeDTO returnTypeDTO = new ReturnTypeDTO();           // Object for returning contact
        List<ContactEntity> contacts = contactRepository.findAll();  // Fetch list of contacts from database table

        boolean isPresent = false;
        ContactEntity primaryContact = new ContactEntity();          // Object used to store primary contact for future use

        for(ContactEntity contact : contacts){                       // Iterating through all records
            if(contact.getEmail().equals(contactDTO.getEmail()) || contact.getPhoneNumber().equals(contactDTO.getPhoneNumber())){   // Checking for similarity in phone number and email in primary records

                if(!isPresent){
                    primaryContact.setId(contact.getId());
                    isPresent = true;
                    continue;
                }
                LocalDateTime updatedAt = LocalDateTime.now();       // Record updating time
                contactRepository.update(primaryContact.getId(), contact.getId(),updatedAt);  // Recods with same email or phone number is updated with linkedId as primary contact's ID and link precedence as secondary

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

        returnTypeDTO.setPrimaryContactId(primaryContact.getId());
        returnTypeDTO.setEmails(contactRepository.getEmails(primaryContact.getId()));
        returnTypeDTO.setPhoneNumbers(contactRepository.getPhoneNumbers(primaryContact.getId()));
        returnTypeDTO.setNumber(contactRepository.getIds(primaryContact.getId()));

        return returnTypeDTO;
    }
}
