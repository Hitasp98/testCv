package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblCommonBaseTypeMapperTest {

    private TblCommonBaseTypeMapper tblCommonBaseTypeMapper;

    @BeforeEach
    public void setUp() {
        tblCommonBaseTypeMapper = new TblCommonBaseTypeMapperImpl();
    }
}
