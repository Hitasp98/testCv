package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCharityAccountsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCharityAccountsDTO.class);
        TblCharityAccountsDTO tblCharityAccountsDTO1 = new TblCharityAccountsDTO();
        tblCharityAccountsDTO1.setId(1L);
        TblCharityAccountsDTO tblCharityAccountsDTO2 = new TblCharityAccountsDTO();
        assertThat(tblCharityAccountsDTO1).isNotEqualTo(tblCharityAccountsDTO2);
        tblCharityAccountsDTO2.setId(tblCharityAccountsDTO1.getId());
        assertThat(tblCharityAccountsDTO1).isEqualTo(tblCharityAccountsDTO2);
        tblCharityAccountsDTO2.setId(2L);
        assertThat(tblCharityAccountsDTO1).isNotEqualTo(tblCharityAccountsDTO2);
        tblCharityAccountsDTO1.setId(null);
        assertThat(tblCharityAccountsDTO1).isNotEqualTo(tblCharityAccountsDTO2);
    }
}
