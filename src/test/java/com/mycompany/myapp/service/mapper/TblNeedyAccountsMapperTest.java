package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblNeedyAccountsMapperTest {

    private TblNeedyAccountsMapper tblNeedyAccountsMapper;

    @BeforeEach
    public void setUp() {
        tblNeedyAccountsMapper = new TblNeedyAccountsMapperImpl();
    }
}
