package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblAssignNeedyToPlans;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblAssignNeedyToPlans entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblAssignNeedyToPlansRepository extends JpaRepository<TblAssignNeedyToPlans, Long> {}
