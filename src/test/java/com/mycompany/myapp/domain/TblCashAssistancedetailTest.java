package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCashAssistancedetailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCashAssistancedetail.class);
        TblCashAssistancedetail tblCashAssistancedetail1 = new TblCashAssistancedetail();
        tblCashAssistancedetail1.setId(1L);
        TblCashAssistancedetail tblCashAssistancedetail2 = new TblCashAssistancedetail();
        tblCashAssistancedetail2.setId(tblCashAssistancedetail1.getId());
        assertThat(tblCashAssistancedetail1).isEqualTo(tblCashAssistancedetail2);
        tblCashAssistancedetail2.setId(2L);
        assertThat(tblCashAssistancedetail1).isNotEqualTo(tblCashAssistancedetail2);
        tblCashAssistancedetail1.setId(null);
        assertThat(tblCashAssistancedetail1).isNotEqualTo(tblCashAssistancedetail2);
    }
}
