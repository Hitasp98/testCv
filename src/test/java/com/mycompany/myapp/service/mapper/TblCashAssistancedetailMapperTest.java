package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblCashAssistancedetailMapperTest {

    private TblCashAssistancedetailMapper tblCashAssistancedetailMapper;

    @BeforeEach
    public void setUp() {
        tblCashAssistancedetailMapper = new TblCashAssistancedetailMapperImpl();
    }
}
