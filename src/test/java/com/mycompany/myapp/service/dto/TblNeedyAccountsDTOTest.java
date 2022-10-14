package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblNeedyAccountsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblNeedyAccountsDTO.class);
        TblNeedyAccountsDTO tblNeedyAccountsDTO1 = new TblNeedyAccountsDTO();
        tblNeedyAccountsDTO1.setId(1L);
        TblNeedyAccountsDTO tblNeedyAccountsDTO2 = new TblNeedyAccountsDTO();
        assertThat(tblNeedyAccountsDTO1).isNotEqualTo(tblNeedyAccountsDTO2);
        tblNeedyAccountsDTO2.setId(tblNeedyAccountsDTO1.getId());
        assertThat(tblNeedyAccountsDTO1).isEqualTo(tblNeedyAccountsDTO2);
        tblNeedyAccountsDTO2.setId(2L);
        assertThat(tblNeedyAccountsDTO1).isNotEqualTo(tblNeedyAccountsDTO2);
        tblNeedyAccountsDTO1.setId(null);
        assertThat(tblNeedyAccountsDTO1).isNotEqualTo(tblNeedyAccountsDTO2);
    }
}
