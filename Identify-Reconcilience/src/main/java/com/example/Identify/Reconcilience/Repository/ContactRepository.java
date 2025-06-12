package com.example.Identify.Reconcilience.Repository;

import com.example.Identify.Reconcilience.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository to deal with postgres for ContactEntity
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    @Modifying
    @Query("UPDATE ContactEntity x SET x.linkPrecedence = 'secondary' , x.linkedId = :primaryContactId WHERE x.id = :contactId")
    void update(@Param("primaryContactId") int primaryContactId, @Param("contactId") int contactId);

    @Query("SELECT email from ContactEntity WHERE linkedId = :primaryId")
    List<String> getEmails(@Param("primaryId") int primaryId);

    @Query("SELECT phoneNumber from ContactEntity WHERE linkedId = :primaryId")
    List<String> getPhoneNumbers(@Param("primaryId") int primaryId);

    @Query("SELECT id from ContactEntity WHERE linkedId = :primaryId")
    List<Integer> getIds(@Param("primaryId") int primaryId);
}
