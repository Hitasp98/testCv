package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblPersonalDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblPersonalDTO.class);
        TblPersonalDTO tblPersonalDTO1 = new TblPersonalDTO();
        tblPersonalDTO1.setId(1L);
        TblPersonalDTO tblPersonalDTO2 = new TblPersonalDTO();
        assertThat(tblPersonalDTO1).isNotEqualTo(tblPersonalDTO2);
        tblPersonalDTO2.setId(tblPersonalDTO1.getId());
        assertThat(tblPersonalDTO1).isEqualTo(tblPersonalDTO2);
        tblPersonalDTO2.setId(2L);
        assertThat(tblPersonalDTO1).isNotEqualTo(tblPersonalDTO2);
        tblPersonalDTO1.setId(null);
        assertThat(tblPersonalDTO1).isNotEqualTo(tblPersonalDTO2);
    }
}
