package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCommonBaseTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCommonBaseTypeDTO.class);
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO1 = new TblCommonBaseTypeDTO();
        tblCommonBaseTypeDTO1.setId(1L);
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO2 = new TblCommonBaseTypeDTO();
        assertThat(tblCommonBaseTypeDTO1).isNotEqualTo(tblCommonBaseTypeDTO2);
        tblCommonBaseTypeDTO2.setId(tblCommonBaseTypeDTO1.getId());
        assertThat(tblCommonBaseTypeDTO1).isEqualTo(tblCommonBaseTypeDTO2);
        tblCommonBaseTypeDTO2.setId(2L);
        assertThat(tblCommonBaseTypeDTO1).isNotEqualTo(tblCommonBaseTypeDTO2);
        tblCommonBaseTypeDTO1.setId(null);
        assertThat(tblCommonBaseTypeDTO1).isNotEqualTo(tblCommonBaseTypeDTO2);
    }
}
