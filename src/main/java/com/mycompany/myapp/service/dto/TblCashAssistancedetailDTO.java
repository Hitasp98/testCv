package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblCashAssistancedetail} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCashAssistancedetailDTO implements Serializable {

    private Long id;

    @NotNull
    private Double neededPrice;

    @NotNull
    private Double minPrice;

    private String description;

    private TblPaymentDTO cashAssistanceDetailId;

    private TblPlansDTO planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNeededPrice() {
        return neededPrice;
    }

    public void setNeededPrice(Double neededPrice) {
        this.neededPrice = neededPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TblPaymentDTO getCashAssistanceDetailId() {
        return cashAssistanceDetailId;
    }

    public void setCashAssistanceDetailId(TblPaymentDTO cashAssistanceDetailId) {
        this.cashAssistanceDetailId = cashAssistanceDetailId;
    }

    public TblPlansDTO getPlanId() {
        return planId;
    }

    public void setPlanId(TblPlansDTO planId) {
        this.planId = planId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCashAssistancedetailDTO)) {
            return false;
        }

        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = (TblCashAssistancedetailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblCashAssistancedetailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCashAssistancedetailDTO{" +
            "id=" + getId() +
            ", neededPrice=" + getNeededPrice() +
            ", minPrice=" + getMinPrice() +
            ", description='" + getDescription() + "'" +
            ", cashAssistanceDetailId=" + getCashAssistanceDetailId() +
            ", planId=" + getPlanId() +
            "}";
    }
}
