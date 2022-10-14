package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCashAssistancedetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblCashAssistancedetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblCashAssistancedetailRepository extends JpaRepository<TblCashAssistancedetail, Long> {}
