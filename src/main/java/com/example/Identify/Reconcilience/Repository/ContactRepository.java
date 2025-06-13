package com.example.Identify.Reconcilience.Repository;

import com.example.Identify.Reconcilience.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// Repository to deal with postgres for ContactEntity
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    // Query for updating the linkedId, link precedence, updated time in record
    @Modifying
    @Query("UPDATE ContactEntity x SET x.linkPrecedence = 'secondary' , x.linkedId = :primaryContactId, x.updatedAt = :updatedAt WHERE x.id = :contactId")
    void update(@Param("primaryContactId") int primaryContactId, @Param("contactId") int contactId, @Param("updatedAt")LocalDateTime updatedAt);

    //Query for fetching list of email with primary Id
    @Query("SELECT email from ContactEntity WHERE phoneNumber= :phone")
    List<String> getEmails(@Param("phone") String phone);

    //Query for fetching list of phone numbers with primary ID
    @Query("SELECT phoneNumber from ContactEntity WHERE email = :email")
    List<String> getPhoneNumbers(@Param("email") String email);

    // Query for fetching list of Ids of secondary entity with primary Id
    @Query("SELECT id from ContactEntity WHERE email = :email OR phoneNumber = :phone")
    List<Integer> getIds(@Param("email") String email, @Param("phone") String phone);

    @Query("SELECT id FROM ContactEntity c WHERE (c.email = :email OR c.phoneNumber = :phoneNumber) AND c.linkPrecedence = 'primary' ")
    int getPrimaryId(@Param("email") String email,@Param(("phoneNumber")) String phoneNumber);
}
