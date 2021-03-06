package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findByUsername(String username);
}
