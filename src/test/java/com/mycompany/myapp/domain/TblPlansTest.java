package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblPlansTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblPlans.class);
        TblPlans tblPlans1 = new TblPlans();
        tblPlans1.setId(1L);
        TblPlans tblPlans2 = new TblPlans();
        tblPlans2.setId(tblPlans1.getId());
        assertThat(tblPlans1).isEqualTo(tblPlans2);
        tblPlans2.setId(2L);
        assertThat(tblPlans1).isNotEqualTo(tblPlans2);
        tblPlans1.setId(null);
        assertThat(tblPlans1).isNotEqualTo(tblPlans2);
    }
}
