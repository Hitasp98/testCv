package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCharityAccounts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblCharityAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblCharityAccountsRepository extends JpaRepository<TblCharityAccounts, Long> {}
