package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCashAssistancedetailDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCashAssistancedetailDTO.class);
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO1 = new TblCashAssistancedetailDTO();
        tblCashAssistancedetailDTO1.setId(1L);
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO2 = new TblCashAssistancedetailDTO();
        assertThat(tblCashAssistancedetailDTO1).isNotEqualTo(tblCashAssistancedetailDTO2);
        tblCashAssistancedetailDTO2.setId(tblCashAssistancedetailDTO1.getId());
        assertThat(tblCashAssistancedetailDTO1).isEqualTo(tblCashAssistancedetailDTO2);
        tblCashAssistancedetailDTO2.setId(2L);
        assertThat(tblCashAssistancedetailDTO1).isNotEqualTo(tblCashAssistancedetailDTO2);
        tblCashAssistancedetailDTO1.setId(null);
        assertThat(tblCashAssistancedetailDTO1).isNotEqualTo(tblCashAssistancedetailDTO2);
    }
}
