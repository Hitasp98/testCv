package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblCharityAccountsMapperTest {

    private TblCharityAccountsMapper tblCharityAccountsMapper;

    @BeforeEach
    public void setUp() {
        tblCharityAccountsMapper = new TblCharityAccountsMapperImpl();
    }
}
