package com.mycompany.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblCharityAccounts} entity.
 */
@Schema(description = "not an ignored comment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCharityAccountsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer bankId;

    @NotNull
    @Size(max = 500)
    private String branchName;

    @NotNull
    @Size(max = 1000)
    private String ownerName;

    @Size(min = 20, max = 20)
    private String cardNumber;

    @NotNull
    @Size(min = 10, max = 10)
    private String accountNumber;

    @Size(min = 0, max = 500)
    private String accountName;

    private TblCommonBaseDataDTO commonBaseDataId;

    private TblPaymentDTO charityAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public TblCommonBaseDataDTO getCommonBaseDataId() {
        return commonBaseDataId;
    }

    public void setCommonBaseDataId(TblCommonBaseDataDTO commonBaseDataId) {
        this.commonBaseDataId = commonBaseDataId;
    }

    public TblPaymentDTO getCharityAccountId() {
        return charityAccountId;
    }

    public void setCharityAccountId(TblPaymentDTO charityAccountId) {
        this.charityAccountId = charityAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCharityAccountsDTO)) {
            return false;
        }

        TblCharityAccountsDTO tblCharityAccountsDTO = (TblCharityAccountsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblCharityAccountsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCharityAccountsDTO{" +
            "id=" + getId() +
            ", bankId=" + getBankId() +
            ", branchName='" + getBranchName() + "'" +
            ", ownerName='" + getOwnerName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", commonBaseDataId=" + getCommonBaseDataId() +
            ", charityAccountId=" + getCharityAccountId() +
            "}";
    }
}
