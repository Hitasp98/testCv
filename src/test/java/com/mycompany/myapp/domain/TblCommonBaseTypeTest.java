package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCommonBaseTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCommonBaseType.class);
        TblCommonBaseType tblCommonBaseType1 = new TblCommonBaseType();
        tblCommonBaseType1.setId(1L);
        TblCommonBaseType tblCommonBaseType2 = new TblCommonBaseType();
        tblCommonBaseType2.setId(tblCommonBaseType1.getId());
        assertThat(tblCommonBaseType1).isEqualTo(tblCommonBaseType2);
        tblCommonBaseType2.setId(2L);
        assertThat(tblCommonBaseType1).isNotEqualTo(tblCommonBaseType2);
        tblCommonBaseType1.setId(null);
        assertThat(tblCommonBaseType1).isNotEqualTo(tblCommonBaseType2);
    }
}
