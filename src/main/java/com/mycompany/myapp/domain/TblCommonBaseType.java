package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblCommonBaseType.
 */
@Entity
@Table(name = "tbl_common_base_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCommonBaseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 800)
    @Column(name = "base_type_title", length = 800, nullable = false)
    private String baseTypeTitle;

    @Size(min = 3, max = 3)
    @Column(name = "base_type_code", length = 3, unique = true)
    private String baseTypeCode;

    @JsonIgnoreProperties(value = { "commonBaseTypeId", "commonBaseDataId", "bankId" }, allowSetters = true)
    @OneToOne(mappedBy = "commonBaseTypeId")
    private TblCommonBaseData commonBaseTypeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblCommonBaseType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseTypeTitle() {
        return this.baseTypeTitle;
    }

    public TblCommonBaseType baseTypeTitle(String baseTypeTitle) {
        this.setBaseTypeTitle(baseTypeTitle);
        return this;
    }

    public void setBaseTypeTitle(String baseTypeTitle) {
        this.baseTypeTitle = baseTypeTitle;
    }

    public String getBaseTypeCode() {
        return this.baseTypeCode;
    }

    public TblCommonBaseType baseTypeCode(String baseTypeCode) {
        this.setBaseTypeCode(baseTypeCode);
        return this;
    }

    public void setBaseTypeCode(String baseTypeCode) {
        this.baseTypeCode = baseTypeCode;
    }

    public TblCommonBaseData getCommonBaseTypeId() {
        return this.commonBaseTypeId;
    }

    public void setCommonBaseTypeId(TblCommonBaseData tblCommonBaseData) {
        if (this.commonBaseTypeId != null) {
            this.commonBaseTypeId.setCommonBaseTypeId(null);
        }
        if (tblCommonBaseData != null) {
            tblCommonBaseData.setCommonBaseTypeId(this);
        }
        this.commonBaseTypeId = tblCommonBaseData;
    }

    public TblCommonBaseType commonBaseTypeId(TblCommonBaseData tblCommonBaseData) {
        this.setCommonBaseTypeId(tblCommonBaseData);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCommonBaseType)) {
            return false;
        }
        return id != null && id.equals(((TblCommonBaseType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCommonBaseType{" +
            "id=" + getId() +
            ", baseTypeTitle='" + getBaseTypeTitle() + "'" +
            ", baseTypeCode='" + getBaseTypeCode() + "'" +
            "}";
    }
}
