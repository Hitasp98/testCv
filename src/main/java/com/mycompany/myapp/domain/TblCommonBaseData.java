package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblCommonBaseData.
 */
@Entity
@Table(name = "tbl_common_base_data")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCommonBaseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 6, max = 6)
    @Column(name = "base_code", length = 6)
    private String baseCode;

    @Size(max = 800)
    @Column(name = "base_value", length = 800)
    private String baseValue;

    @JsonIgnoreProperties(value = { "commonBaseTypeId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblCommonBaseType commonBaseTypeId;

    @JsonIgnoreProperties(value = { "bankId", "needyId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblNeedyAccounts commonBaseDataId;

    @JsonIgnoreProperties(value = { "commonBaseDataId", "charityAccountId" }, allowSetters = true)
    @OneToOne(mappedBy = "commonBaseDataId")
    private TblCharityAccounts bankId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblCommonBaseData id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseCode() {
        return this.baseCode;
    }

    public TblCommonBaseData baseCode(String baseCode) {
        this.setBaseCode(baseCode);
        return this;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getBaseValue() {
        return this.baseValue;
    }

    public TblCommonBaseData baseValue(String baseValue) {
        this.setBaseValue(baseValue);
        return this;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }

    public TblCommonBaseType getCommonBaseTypeId() {
        return this.commonBaseTypeId;
    }

    public void setCommonBaseTypeId(TblCommonBaseType tblCommonBaseType) {
        this.commonBaseTypeId = tblCommonBaseType;
    }

    public TblCommonBaseData commonBaseTypeId(TblCommonBaseType tblCommonBaseType) {
        this.setCommonBaseTypeId(tblCommonBaseType);
        return this;
    }

    public TblNeedyAccounts getCommonBaseDataId() {
        return this.commonBaseDataId;
    }

    public void setCommonBaseDataId(TblNeedyAccounts tblNeedyAccounts) {
        this.commonBaseDataId = tblNeedyAccounts;
    }

    public TblCommonBaseData commonBaseDataId(TblNeedyAccounts tblNeedyAccounts) {
        this.setCommonBaseDataId(tblNeedyAccounts);
        return this;
    }

    public TblCharityAccounts getBankId() {
        return this.bankId;
    }

    public void setBankId(TblCharityAccounts tblCharityAccounts) {
        if (this.bankId != null) {
            this.bankId.setCommonBaseDataId(null);
        }
        if (tblCharityAccounts != null) {
            tblCharityAccounts.setCommonBaseDataId(this);
        }
        this.bankId = tblCharityAccounts;
    }

    public TblCommonBaseData bankId(TblCharityAccounts tblCharityAccounts) {
        this.setBankId(tblCharityAccounts);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCommonBaseData)) {
            return false;
        }
        return id != null && id.equals(((TblCommonBaseData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCommonBaseData{" +
            "id=" + getId() +
            ", baseCode='" + getBaseCode() + "'" +
            ", baseValue='" + getBaseValue() + "'" +
            "}";
    }
}
