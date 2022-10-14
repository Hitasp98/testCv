package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblAssignNeedyToPlansMapperTest {

    private TblAssignNeedyToPlansMapper tblAssignNeedyToPlansMapper;

    @BeforeEach
    public void setUp() {
        tblAssignNeedyToPlansMapper = new TblAssignNeedyToPlansMapperImpl();
    }
}
