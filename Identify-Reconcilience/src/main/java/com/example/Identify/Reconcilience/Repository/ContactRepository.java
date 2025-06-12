package com.example.Identify.Reconcilience.Repository;

import com.example.Identify.Reconcilience.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Repository to deal with postgres for ContactEntity
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {


}
