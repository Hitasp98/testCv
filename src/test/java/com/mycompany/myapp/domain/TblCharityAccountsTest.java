package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCharityAccountsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCharityAccounts.class);
        TblCharityAccounts tblCharityAccounts1 = new TblCharityAccounts();
        tblCharityAccounts1.setId(1L);
        TblCharityAccounts tblCharityAccounts2 = new TblCharityAccounts();
        tblCharityAccounts2.setId(tblCharityAccounts1.getId());
        assertThat(tblCharityAccounts1).isEqualTo(tblCharityAccounts2);
        tblCharityAccounts2.setId(2L);
        assertThat(tblCharityAccounts1).isNotEqualTo(tblCharityAccounts2);
        tblCharityAccounts1.setId(null);
        assertThat(tblCharityAccounts1).isNotEqualTo(tblCharityAccounts2);
    }
}
