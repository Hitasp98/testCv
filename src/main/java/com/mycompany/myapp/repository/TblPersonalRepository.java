package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblPersonal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblPersonal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblPersonalRepository extends JpaRepository<TblPersonal, Long> {}
