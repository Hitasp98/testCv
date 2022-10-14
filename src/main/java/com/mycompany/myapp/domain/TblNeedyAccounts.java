package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblNeedyAccounts.
 */
@Entity
@Table(name = "tbl_needy_accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblNeedyAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "needy_account_id", nullable = false)
    private Integer needyAccountId;

    @NotNull
    @Size(min = 10, max = 1000)
    @Column(name = "owner_name", length = 1000, nullable = false)
    private String ownerName;

    @Size(min = 20, max = 20)
    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "account_number", length = 10, nullable = false, unique = true)
    private String accountNumber;

    @Size(min = 10, max = 500)
    @Column(name = "account_name", length = 500)
    private String accountName;

    @NotNull
    @Size(min = 26, max = 26)
    @Column(name = "sheba_number", length = 26, nullable = false, unique = true)
    private String shebaNumber;

    @JsonIgnoreProperties(value = { "commonBaseTypeId", "commonBaseDataId", "bankId" }, allowSetters = true)
    @OneToOne(mappedBy = "commonBaseDataId")
    private TblCommonBaseData bankId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "personIds", "tblAssignNeedyToPlans", "tblNeedyAccounts", "tblPayments" }, allowSetters = true)
    private TblPersonal needyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblNeedyAccounts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNeedyAccountId() {
        return this.needyAccountId;
    }

    public TblNeedyAccounts needyAccountId(Integer needyAccountId) {
        this.setNeedyAccountId(needyAccountId);
        return this;
    }

    public void setNeedyAccountId(Integer needyAccountId) {
        this.needyAccountId = needyAccountId;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public TblNeedyAccounts ownerName(String ownerName) {
        this.setOwnerName(ownerName);
        return this;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public TblNeedyAccounts cardNumber(String cardNumber) {
        this.setCardNumber(cardNumber);
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public TblNeedyAccounts accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public TblNeedyAccounts accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getShebaNumber() {
        return this.shebaNumber;
    }

    public TblNeedyAccounts shebaNumber(String shebaNumber) {
        this.setShebaNumber(shebaNumber);
        return this;
    }

    public void setShebaNumber(String shebaNumber) {
        this.shebaNumber = shebaNumber;
    }

    public TblCommonBaseData getBankId() {
        return this.bankId;
    }

    public void setBankId(TblCommonBaseData tblCommonBaseData) {
        if (this.bankId != null) {
            this.bankId.setCommonBaseDataId(null);
        }
        if (tblCommonBaseData != null) {
            tblCommonBaseData.setCommonBaseDataId(this);
        }
        this.bankId = tblCommonBaseData;
    }

    public TblNeedyAccounts bankId(TblCommonBaseData tblCommonBaseData) {
        this.setBankId(tblCommonBaseData);
        return this;
    }

    public TblPersonal getNeedyId() {
        return this.needyId;
    }

    public void setNeedyId(TblPersonal tblPersonal) {
        this.needyId = tblPersonal;
    }

    public TblNeedyAccounts needyId(TblPersonal tblPersonal) {
        this.setNeedyId(tblPersonal);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblNeedyAccounts)) {
            return false;
        }
        return id != null && id.equals(((TblNeedyAccounts) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblNeedyAccounts{" +
            "id=" + getId() +
            ", needyAccountId=" + getNeedyAccountId() +
            ", ownerName='" + getOwnerName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", shebaNumber='" + getShebaNumber() + "'" +
            "}";
    }
}
