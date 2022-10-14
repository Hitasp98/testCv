package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblCommonBaseDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblCommonBaseData.class);
        TblCommonBaseData tblCommonBaseData1 = new TblCommonBaseData();
        tblCommonBaseData1.setId(1L);
        TblCommonBaseData tblCommonBaseData2 = new TblCommonBaseData();
        tblCommonBaseData2.setId(tblCommonBaseData1.getId());
        assertThat(tblCommonBaseData1).isEqualTo(tblCommonBaseData2);
        tblCommonBaseData2.setId(2L);
        assertThat(tblCommonBaseData1).isNotEqualTo(tblCommonBaseData2);
        tblCommonBaseData1.setId(null);
        assertThat(tblCommonBaseData1).isNotEqualTo(tblCommonBaseData2);
    }
}
