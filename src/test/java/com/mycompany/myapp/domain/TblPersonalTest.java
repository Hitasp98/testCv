package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblPersonalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblPersonal.class);
        TblPersonal tblPersonal1 = new TblPersonal();
        tblPersonal1.setId(1L);
        TblPersonal tblPersonal2 = new TblPersonal();
        tblPersonal2.setId(tblPersonal1.getId());
        assertThat(tblPersonal1).isEqualTo(tblPersonal2);
        tblPersonal2.setId(2L);
        assertThat(tblPersonal1).isNotEqualTo(tblPersonal2);
        tblPersonal1.setId(null);
        assertThat(tblPersonal1).isNotEqualTo(tblPersonal2);
    }
}
