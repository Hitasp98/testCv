package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCharityAccounts;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface TblCharityAccountsRepositoryWithBagRelationships {
    Optional<TblCharityAccounts> fetchBagRelationships(Optional<TblCharityAccounts> tblCharityAccounts);

    List<TblCharityAccounts> fetchBagRelationships(List<TblCharityAccounts> tblCharityAccounts);

    Page<TblCharityAccounts> fetchBagRelationships(Page<TblCharityAccounts> tblCharityAccounts);
}
