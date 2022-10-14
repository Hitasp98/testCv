package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblPayment.
 */
@Entity
@Table(name = "tbl_payment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "payment_id", nullable = false)
    private Integer paymentId;

    @NotNull
    @Column(name = "payment_price", nullable = false)
    private Double paymentPrice;

    @Size(min = 10, max = 10)
    @Column(name = "payment_gateway_id", length = 10)
    private String paymentGatewayId;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @NotNull
    @Column(name = "payment_time", nullable = false)
    private String paymentTime;

    @NotNull
    @Size(min = 0, max = 500)
    @Column(name = "payment_status", length = 500, nullable = false)
    private String paymentStatus;

    @Size(min = 10, max = 10)
    @Column(name = "source_accout_number", length = 10)
    private String sourceAccoutNumber;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "target_account_number", length = 10, nullable = false)
    private String targetAccountNumber;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "follow_code", length = 10, nullable = false)
    private String followCode;

    @JsonIgnoreProperties(value = { "commonBaseDataId", "charityAccountId" }, allowSetters = true)
    @OneToOne(mappedBy = "charityAccountId")
    private TblCharityAccounts charityAccountId;

    @JsonIgnoreProperties(value = { "cashAssistanceDetailId", "assignNeedyPlanId", "planId" }, allowSetters = true)
    @OneToOne(mappedBy = "cashAssistanceDetailId")
    private TblCashAssistancedetail cashAssistanceDetailId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "personIds", "tblAssignNeedyToPlans", "tblNeedyAccounts", "tblPayments" }, allowSetters = true)
    private TblPersonal donatorId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "personIds", "tblAssignNeedyToPlans", "tblNeedyAccounts", "tblPayments" }, allowSetters = true)
    private TblPersonal needyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentId() {
        return this.paymentId;
    }

    public TblPayment paymentId(Integer paymentId) {
        this.setPaymentId(paymentId);
        return this;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentPrice() {
        return this.paymentPrice;
    }

    public TblPayment paymentPrice(Double paymentPrice) {
        this.setPaymentPrice(paymentPrice);
        return this;
    }

    public void setPaymentPrice(Double paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentGatewayId() {
        return this.paymentGatewayId;
    }

    public TblPayment paymentGatewayId(String paymentGatewayId) {
        this.setPaymentGatewayId(paymentGatewayId);
        return this;
    }

    public void setPaymentGatewayId(String paymentGatewayId) {
        this.paymentGatewayId = paymentGatewayId;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public TblPayment paymentDate(String paymentDate) {
        this.setPaymentDate(paymentDate);
        return this;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return this.paymentTime;
    }

    public TblPayment paymentTime(String paymentTime) {
        this.setPaymentTime(paymentTime);
        return this;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public TblPayment paymentStatus(String paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getSourceAccoutNumber() {
        return this.sourceAccoutNumber;
    }

    public TblPayment sourceAccoutNumber(String sourceAccoutNumber) {
        this.setSourceAccoutNumber(sourceAccoutNumber);
        return this;
    }

    public void setSourceAccoutNumber(String sourceAccoutNumber) {
        this.sourceAccoutNumber = sourceAccoutNumber;
    }

    public String getTargetAccountNumber() {
        return this.targetAccountNumber;
    }

    public TblPayment targetAccountNumber(String targetAccountNumber) {
        this.setTargetAccountNumber(targetAccountNumber);
        return this;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getFollowCode() {
        return this.followCode;
    }

    public TblPayment followCode(String followCode) {
        this.setFollowCode(followCode);
        return this;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode;
    }

    public TblCharityAccounts getCharityAccountId() {
        return this.charityAccountId;
    }

    public void setCharityAccountId(TblCharityAccounts tblCharityAccounts) {
        if (this.charityAccountId != null) {
            this.charityAccountId.setCharityAccountId(null);
        }
        if (tblCharityAccounts != null) {
            tblCharityAccounts.setCharityAccountId(this);
        }
        this.charityAccountId = tblCharityAccounts;
    }

    public TblPayment charityAccountId(TblCharityAccounts tblCharityAccounts) {
        this.setCharityAccountId(tblCharityAccounts);
        return this;
    }

    public TblCashAssistancedetail getCashAssistanceDetailId() {
        return this.cashAssistanceDetailId;
    }

    public void setCashAssistanceDetailId(TblCashAssistancedetail tblCashAssistancedetail) {
        if (this.cashAssistanceDetailId != null) {
            this.cashAssistanceDetailId.setCashAssistanceDetailId(null);
        }
        if (tblCashAssistancedetail != null) {
            tblCashAssistancedetail.setCashAssistanceDetailId(this);
        }
        this.cashAssistanceDetailId = tblCashAssistancedetail;
    }

    public TblPayment cashAssistanceDetailId(TblCashAssistancedetail tblCashAssistancedetail) {
        this.setCashAssistanceDetailId(tblCashAssistancedetail);
        return this;
    }

    public TblPersonal getDonatorId() {
        return this.donatorId;
    }

    public void setDonatorId(TblPersonal tblPersonal) {
        this.donatorId = tblPersonal;
    }

    public TblPayment donatorId(TblPersonal tblPersonal) {
        this.setDonatorId(tblPersonal);
        return this;
    }

    public TblPersonal getNeedyId() {
        return this.needyId;
    }

    public void setNeedyId(TblPersonal tblPersonal) {
        this.needyId = tblPersonal;
    }

    public TblPayment needyId(TblPersonal tblPersonal) {
        this.setNeedyId(tblPersonal);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblPayment)) {
            return false;
        }
        return id != null && id.equals(((TblPayment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPayment{" +
            "id=" + getId() +
            ", paymentId=" + getPaymentId() +
            ", paymentPrice=" + getPaymentPrice() +
            ", paymentGatewayId='" + getPaymentGatewayId() + "'" +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", paymentTime='" + getPaymentTime() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", sourceAccoutNumber='" + getSourceAccoutNumber() + "'" +
            ", targetAccountNumber='" + getTargetAccountNumber() + "'" +
            ", followCode='" + getFollowCode() + "'" +
            "}";
    }
}
