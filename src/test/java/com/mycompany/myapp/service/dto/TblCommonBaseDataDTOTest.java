package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCommonBaseDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCommonBaseDataDTO.class);
        TblCommonBaseDataDTO tblCommonBaseDataDTO1 = new TblCommonBaseDataDTO();
        tblCommonBaseDataDTO1.setId(1L);
        TblCommonBaseDataDTO tblCommonBaseDataDTO2 = new TblCommonBaseDataDTO();
        assertThat(tblCommonBaseDataDTO1).isNotEqualTo(tblCommonBaseDataDTO2);
        tblCommonBaseDataDTO2.setId(tblCommonBaseDataDTO1.getId());
        assertThat(tblCommonBaseDataDTO1).isEqualTo(tblCommonBaseDataDTO2);
        tblCommonBaseDataDTO2.setId(2L);
        assertThat(tblCommonBaseDataDTO1).isNotEqualTo(tblCommonBaseDataDTO2);
        tblCommonBaseDataDTO1.setId(null);
        assertThat(tblCommonBaseDataDTO1).isNotEqualTo(tblCommonBaseDataDTO2);
    }
}
