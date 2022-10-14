package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblPersonalMapperTest {

    private TblPersonalMapper tblPersonalMapper;

    @BeforeEach
    public void setUp() {
        tblPersonalMapper = new TblPersonalMapperImpl();
    }
}
