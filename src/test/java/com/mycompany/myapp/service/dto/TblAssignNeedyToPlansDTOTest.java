package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblAssignNeedyToPlansDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblAssignNeedyToPlansDTO.class);
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO1 = new TblAssignNeedyToPlansDTO();
        tblAssignNeedyToPlansDTO1.setId(1L);
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO2 = new TblAssignNeedyToPlansDTO();
        assertThat(tblAssignNeedyToPlansDTO1).isNotEqualTo(tblAssignNeedyToPlansDTO2);
        tblAssignNeedyToPlansDTO2.setId(tblAssignNeedyToPlansDTO1.getId());
        assertThat(tblAssignNeedyToPlansDTO1).isEqualTo(tblAssignNeedyToPlansDTO2);
        tblAssignNeedyToPlansDTO2.setId(2L);
        assertThat(tblAssignNeedyToPlansDTO1).isNotEqualTo(tblAssignNeedyToPlansDTO2);
        tblAssignNeedyToPlansDTO1.setId(null);
        assertThat(tblAssignNeedyToPlansDTO1).isNotEqualTo(tblAssignNeedyToPlansDTO2);
    }
}
