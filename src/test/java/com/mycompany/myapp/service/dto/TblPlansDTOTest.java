package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblPlansDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblPlansDTO.class);
        TblPlansDTO tblPlansDTO1 = new TblPlansDTO();
        tblPlansDTO1.setId(1L);
        TblPlansDTO tblPlansDTO2 = new TblPlansDTO();
        assertThat(tblPlansDTO1).isNotEqualTo(tblPlansDTO2);
        tblPlansDTO2.setId(tblPlansDTO1.getId());
        assertThat(tblPlansDTO1).isEqualTo(tblPlansDTO2);
        tblPlansDTO2.setId(2L);
        assertThat(tblPlansDTO1).isNotEqualTo(tblPlansDTO2);
        tblPlansDTO1.setId(null);
        assertThat(tblPlansDTO1).isNotEqualTo(tblPlansDTO2);
    }
}
