package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblAssignNeedyToPlansTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblAssignNeedyToPlans.class);
        TblAssignNeedyToPlans tblAssignNeedyToPlans1 = new TblAssignNeedyToPlans();
        tblAssignNeedyToPlans1.setId(1L);
        TblAssignNeedyToPlans tblAssignNeedyToPlans2 = new TblAssignNeedyToPlans();
        tblAssignNeedyToPlans2.setId(tblAssignNeedyToPlans1.getId());
        assertThat(tblAssignNeedyToPlans1).isEqualTo(tblAssignNeedyToPlans2);
        tblAssignNeedyToPlans2.setId(2L);
        assertThat(tblAssignNeedyToPlans1).isNotEqualTo(tblAssignNeedyToPlans2);
        tblAssignNeedyToPlans1.setId(null);
        assertThat(tblAssignNeedyToPlans1).isNotEqualTo(tblAssignNeedyToPlans2);
    }
}
