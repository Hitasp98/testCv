package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblCommonBaseData} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCommonBaseDataDTO implements Serializable {

    private Long id;

    @Size(min = 6, max = 6)
    private String baseCode;

    @Size(max = 800)
    private String baseValue;

    private TblCommonBaseTypeDTO commonBaseTypeId;

    private TblNeedyAccountsDTO commonBaseDataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }

    public TblCommonBaseTypeDTO getCommonBaseTypeId() {
        return commonBaseTypeId;
    }

    public void setCommonBaseTypeId(TblCommonBaseTypeDTO commonBaseTypeId) {
        this.commonBaseTypeId = commonBaseTypeId;
    }

    public TblNeedyAccountsDTO getCommonBaseDataId() {
        return commonBaseDataId;
    }

    public void setCommonBaseDataId(TblNeedyAccountsDTO commonBaseDataId) {
        this.commonBaseDataId = commonBaseDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCommonBaseDataDTO)) {
            return false;
        }

        TblCommonBaseDataDTO tblCommonBaseDataDTO = (TblCommonBaseDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblCommonBaseDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCommonBaseDataDTO{" +
            "id=" + getId() +
            ", baseCode='" + getBaseCode() + "'" +
            ", baseValue='" + getBaseValue() + "'" +
            ", commonBaseTypeId=" + getCommonBaseTypeId() +
            ", commonBaseDataId=" + getCommonBaseDataId() +
            "}";
    }
}
