package com.keylane.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for {TriangleBySideEntity}
 */
public interface TriangleBySideRepository extends JpaRepository<TriangleBySideEntity, Long> {
    List<TriangleBySideEntity> findAll();
}
