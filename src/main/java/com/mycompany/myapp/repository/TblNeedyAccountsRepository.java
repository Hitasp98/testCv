package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblNeedyAccounts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblNeedyAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblNeedyAccountsRepository extends JpaRepository<TblNeedyAccounts, Long> {}
