package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblNeedyAccountsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblNeedyAccounts.class);
        TblNeedyAccounts tblNeedyAccounts1 = new TblNeedyAccounts();
        tblNeedyAccounts1.setId(1L);
        TblNeedyAccounts tblNeedyAccounts2 = new TblNeedyAccounts();
        tblNeedyAccounts2.setId(tblNeedyAccounts1.getId());
        assertThat(tblNeedyAccounts1).isEqualTo(tblNeedyAccounts2);
        tblNeedyAccounts2.setId(2L);
        assertThat(tblNeedyAccounts1).isNotEqualTo(tblNeedyAccounts2);
        tblNeedyAccounts1.setId(null);
        assertThat(tblNeedyAccounts1).isNotEqualTo(tblNeedyAccounts2);
    }
}
