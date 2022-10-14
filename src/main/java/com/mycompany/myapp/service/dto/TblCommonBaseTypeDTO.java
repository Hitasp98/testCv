package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblCommonBaseType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCommonBaseTypeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 800)
    private String baseTypeTitle;

    @Size(min = 3, max = 3)
    private String baseTypeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseTypeTitle() {
        return baseTypeTitle;
    }

    public void setBaseTypeTitle(String baseTypeTitle) {
        this.baseTypeTitle = baseTypeTitle;
    }

    public String getBaseTypeCode() {
        return baseTypeCode;
    }

    public void setBaseTypeCode(String baseTypeCode) {
        this.baseTypeCode = baseTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCommonBaseTypeDTO)) {
            return false;
        }

        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = (TblCommonBaseTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblCommonBaseTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCommonBaseTypeDTO{" +
            "id=" + getId() +
            ", baseTypeTitle='" + getBaseTypeTitle() + "'" +
            ", baseTypeCode='" + getBaseTypeCode() + "'" +
            "}";
    }
}
