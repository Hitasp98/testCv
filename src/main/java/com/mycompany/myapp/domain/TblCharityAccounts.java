package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "tbl_charity_accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCharityAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "bank_id", nullable = false)
    private Integer bankId;

    @NotNull
    @Size(max = 500)
    @Column(name = "branch_name", length = 500, nullable = false)
    private String branchName;

    @NotNull
    @Size(max = 1000)
    @Column(name = "owner_name", length = 1000, nullable = false)
    private String ownerName;

    @Size(min = 20, max = 20)
    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "account_number", length = 10, nullable = false, unique = true)
    private String accountNumber;

    @Size(min = 0, max = 500)
    @Column(name = "account_name", length = 500)
    private String accountName;

    @JsonIgnoreProperties(value = { "commonBaseTypeId", "commonBaseDataId", "bankId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblCommonBaseData commonBaseDataId;

    @JsonIgnoreProperties(value = { "charityAccountId", "cashAssistanceDetailId", "donatorId", "needyId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblPayment charityAccountId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblCharityAccounts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBankId() {
        return this.bankId;
    }

    public TblCharityAccounts bankId(Integer bankId) {
        this.setBankId(bankId);
        return this;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public TblCharityAccounts branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public TblCharityAccounts ownerName(String ownerName) {
        this.setOwnerName(ownerName);
        return this;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public TblCharityAccounts cardNumber(String cardNumber) {
        this.setCardNumber(cardNumber);
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public TblCharityAccounts accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public TblCharityAccounts accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public TblCommonBaseData getCommonBaseDataId() {
        return this.commonBaseDataId;
    }

    public void setCommonBaseDataId(TblCommonBaseData tblCommonBaseData) {
        this.commonBaseDataId = tblCommonBaseData;
    }

    public TblCharityAccounts commonBaseDataId(TblCommonBaseData tblCommonBaseData) {
        this.setCommonBaseDataId(tblCommonBaseData);
        return this;
    }

    public TblPayment getCharityAccountId() {
        return this.charityAccountId;
    }

    public void setCharityAccountId(TblPayment tblPayment) {
        this.charityAccountId = tblPayment;
    }

    public TblCharityAccounts charityAccountId(TblPayment tblPayment) {
        this.setCharityAccountId(tblPayment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCharityAccounts)) {
            return false;
        }
        return id != null && id.equals(((TblCharityAccounts) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCharityAccounts{" +
            "id=" + getId() +
            ", bankId=" + getBankId() +
            ", branchName='" + getBranchName() + "'" +
            ", ownerName='" + getOwnerName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            "}";
    }
}
