package com.example.Identify.Reconcilience.Controller;

import com.example.Identify.Reconcilience.DTO.ContactDTO;
import com.example.Identify.Reconcilience.DTO.ReturnTypeDTO;
import com.example.Identify.Reconcilience.Entity.ContactEntity;
import com.example.Identify.Reconcilience.Service.IdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdentifyController {

    @Autowired
    IdentifyService identifyService;

    //Controller to post and list contacts with /identify endpoint
    @PostMapping("/identify")
    public ReturnTypeDTO addContact(@RequestBody ContactDTO contactDTO){
        return(identifyService.addContact(contactDTO));

    }
}