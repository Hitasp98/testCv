package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCommonBaseData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblCommonBaseData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblCommonBaseDataRepository extends JpaRepository<TblCommonBaseData, Long> {}
