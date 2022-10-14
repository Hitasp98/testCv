package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblNeedyAccounts} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblNeedyAccountsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer needyAccountId;

    @NotNull
    @Size(min = 10, max = 1000)
    private String ownerName;

    @Size(min = 20, max = 20)
    private String cardNumber;

    @NotNull
    @Size(min = 10, max = 10)
    private String accountNumber;

    @Size(min = 10, max = 500)
    private String accountName;

    @NotNull
    @Size(min = 26, max = 26)
    private String shebaNumber;

    private TblPersonalDTO needyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNeedyAccountId() {
        return needyAccountId;
    }

    public void setNeedyAccountId(Integer needyAccountId) {
        this.needyAccountId = needyAccountId;
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

    public String getShebaNumber() {
        return shebaNumber;
    }

    public void setShebaNumber(String shebaNumber) {
        this.shebaNumber = shebaNumber;
    }

    public TblPersonalDTO getNeedyId() {
        return needyId;
    }

    public void setNeedyId(TblPersonalDTO needyId) {
        this.needyId = needyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblNeedyAccountsDTO)) {
            return false;
        }

        TblNeedyAccountsDTO tblNeedyAccountsDTO = (TblNeedyAccountsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblNeedyAccountsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblNeedyAccountsDTO{" +
            "id=" + getId() +
            ", needyAccountId=" + getNeedyAccountId() +
            ", ownerName='" + getOwnerName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", shebaNumber='" + getShebaNumber() + "'" +
            ", needyId=" + getNeedyId() +
            "}";
    }
}
