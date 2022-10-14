package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblPayment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPaymentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer paymentId;

    @NotNull
    private Double paymentPrice;

    @Size(min = 10, max = 10)
    private String paymentGatewayId;

    @NotNull
    private String paymentDate;

    @NotNull
    private String paymentTime;

    @NotNull
    @Size(min = 0, max = 500)
    private String paymentStatus;

    @Size(min = 10, max = 10)
    private String sourceAccoutNumber;

    @NotNull
    @Size(min = 10, max = 10)
    private String targetAccountNumber;

    @NotNull
    @Size(min = 10, max = 10)
    private String followCode;

    private TblPersonalDTO donatorId;

    private TblPersonalDTO needyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Double paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentGatewayId() {
        return paymentGatewayId;
    }

    public void setPaymentGatewayId(String paymentGatewayId) {
        this.paymentGatewayId = paymentGatewayId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getSourceAccoutNumber() {
        return sourceAccoutNumber;
    }

    public void setSourceAccoutNumber(String sourceAccoutNumber) {
        this.sourceAccoutNumber = sourceAccoutNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getFollowCode() {
        return followCode;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode;
    }

    public TblPersonalDTO getDonatorId() {
        return donatorId;
    }

    public void setDonatorId(TblPersonalDTO donatorId) {
        this.donatorId = donatorId;
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
        if (!(o instanceof TblPaymentDTO)) {
            return false;
        }

        TblPaymentDTO tblPaymentDTO = (TblPaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblPaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPaymentDTO{" +
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
            ", donatorId=" + getDonatorId() +
            ", needyId=" + getNeedyId() +
            "}";
    }
}
