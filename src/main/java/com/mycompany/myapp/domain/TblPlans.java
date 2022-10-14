package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblPlans.
 */
@Entity
@Table(name = "tbl_plans")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPlans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 10, max = 1000)
    @Column(name = "plan_name", length = 1000, nullable = false, unique = true)
    private String planName;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "plan_nature", nullable = false)
    private Boolean planNature;

    @Lob
    @Column(name = "icon")
    private byte[] icon;

    @Column(name = "icon_content_type")
    private String iconContentType;

    @Size(min = 10, max = 10)
    @Column(name = "fdate", length = 10)
    private String fdate;

    @Size(min = 10, max = 10)
    @Column(name = "tdate", length = 10)
    private String tdate;

    @NotNull
    @Column(name = "needed_login", nullable = false)
    private Boolean neededLogin;

    @OneToMany(mappedBy = "parentPlanId")
    @JsonIgnoreProperties(value = { "planIds", "tblCashAssistancedetails", "tblAssignNeedyToPlans", "parentPlanId" }, allowSetters = true)
    private Set<TblPlans> planIds = new HashSet<>();

    @OneToMany(mappedBy = "planId")
    @JsonIgnoreProperties(value = { "cashAssistanceDetailId", "assignNeedyPlanId", "planId" }, allowSetters = true)
    private Set<TblCashAssistancedetail> tblCashAssistancedetails = new HashSet<>();

    @OneToMany(mappedBy = "personId")
    @JsonIgnoreProperties(value = { "assignNeedyPlanId", "personId", "needyId" }, allowSetters = true)
    private Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "planIds", "tblCashAssistancedetails", "tblAssignNeedyToPlans", "parentPlanId" }, allowSetters = true)
    private TblPlans parentPlanId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblPlans id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return this.planName;
    }

    public TblPlans planName(String planName) {
        this.setPlanName(planName);
        return this;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return this.description;
    }

    public TblPlans description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPlanNature() {
        return this.planNature;
    }

    public TblPlans planNature(Boolean planNature) {
        this.setPlanNature(planNature);
        return this;
    }

    public void setPlanNature(Boolean planNature) {
        this.planNature = planNature;
    }

    public byte[] getIcon() {
        return this.icon;
    }

    public TblPlans icon(byte[] icon) {
        this.setIcon(icon);
        return this;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getIconContentType() {
        return this.iconContentType;
    }

    public TblPlans iconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
        return this;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
    }

    public String getFdate() {
        return this.fdate;
    }

    public TblPlans fdate(String fdate) {
        this.setFdate(fdate);
        return this;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getTdate() {
        return this.tdate;
    }

    public TblPlans tdate(String tdate) {
        this.setTdate(tdate);
        return this;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public Boolean getNeededLogin() {
        return this.neededLogin;
    }

    public TblPlans neededLogin(Boolean neededLogin) {
        this.setNeededLogin(neededLogin);
        return this;
    }

    public void setNeededLogin(Boolean neededLogin) {
        this.neededLogin = neededLogin;
    }

    public Set<TblPlans> getPlanIds() {
        return this.planIds;
    }

    public void setPlanIds(Set<TblPlans> tblPlans) {
        if (this.planIds != null) {
            this.planIds.forEach(i -> i.setParentPlanId(null));
        }
        if (tblPlans != null) {
            tblPlans.forEach(i -> i.setParentPlanId(this));
        }
        this.planIds = tblPlans;
    }

    public TblPlans planIds(Set<TblPlans> tblPlans) {
        this.setPlanIds(tblPlans);
        return this;
    }

    public TblPlans addPlanId(TblPlans tblPlans) {
        this.planIds.add(tblPlans);
        tblPlans.setParentPlanId(this);
        return this;
    }

    public TblPlans removePlanId(TblPlans tblPlans) {
        this.planIds.remove(tblPlans);
        tblPlans.setParentPlanId(null);
        return this;
    }

    public Set<TblCashAssistancedetail> getTblCashAssistancedetails() {
        return this.tblCashAssistancedetails;
    }

    public void setTblCashAssistancedetails(Set<TblCashAssistancedetail> tblCashAssistancedetails) {
        if (this.tblCashAssistancedetails != null) {
            this.tblCashAssistancedetails.forEach(i -> i.setPlanId(null));
        }
        if (tblCashAssistancedetails != null) {
            tblCashAssistancedetails.forEach(i -> i.setPlanId(this));
        }
        this.tblCashAssistancedetails = tblCashAssistancedetails;
    }

    public TblPlans tblCashAssistancedetails(Set<TblCashAssistancedetail> tblCashAssistancedetails) {
        this.setTblCashAssistancedetails(tblCashAssistancedetails);
        return this;
    }

    public TblPlans addTblCashAssistancedetail(TblCashAssistancedetail tblCashAssistancedetail) {
        this.tblCashAssistancedetails.add(tblCashAssistancedetail);
        tblCashAssistancedetail.setPlanId(this);
        return this;
    }

    public TblPlans removeTblCashAssistancedetail(TblCashAssistancedetail tblCashAssistancedetail) {
        this.tblCashAssistancedetails.remove(tblCashAssistancedetail);
        tblCashAssistancedetail.setPlanId(null);
        return this;
    }

    public Set<TblAssignNeedyToPlans> getTblAssignNeedyToPlans() {
        return this.tblAssignNeedyToPlans;
    }

    public void setTblAssignNeedyToPlans(Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans) {
        if (this.tblAssignNeedyToPlans != null) {
            this.tblAssignNeedyToPlans.forEach(i -> i.setPersonId(null));
        }
        if (tblAssignNeedyToPlans != null) {
            tblAssignNeedyToPlans.forEach(i -> i.setPersonId(this));
        }
        this.tblAssignNeedyToPlans = tblAssignNeedyToPlans;
    }

    public TblPlans tblAssignNeedyToPlans(Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans) {
        this.setTblAssignNeedyToPlans(tblAssignNeedyToPlans);
        return this;
    }

    public TblPlans addTblAssignNeedyToPlans(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        this.tblAssignNeedyToPlans.add(tblAssignNeedyToPlans);
        tblAssignNeedyToPlans.setPersonId(this);
        return this;
    }

    public TblPlans removeTblAssignNeedyToPlans(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        this.tblAssignNeedyToPlans.remove(tblAssignNeedyToPlans);
        tblAssignNeedyToPlans.setPersonId(null);
        return this;
    }

    public TblPlans getParentPlanId() {
        return this.parentPlanId;
    }

    public void setParentPlanId(TblPlans tblPlans) {
        this.parentPlanId = tblPlans;
    }

    public TblPlans parentPlanId(TblPlans tblPlans) {
        this.setParentPlanId(tblPlans);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblPlans)) {
            return false;
        }
        return id != null && id.equals(((TblPlans) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPlans{" +
            "id=" + getId() +
            ", planName='" + getPlanName() + "'" +
            ", description='" + getDescription() + "'" +
            ", planNature='" + getPlanNature() + "'" +
            ", icon='" + getIcon() + "'" +
            ", iconContentType='" + getIconContentType() + "'" +
            ", fdate='" + getFdate() + "'" +
            ", tdate='" + getTdate() + "'" +
            ", neededLogin='" + getNeededLogin() + "'" +
            "}";
    }
}
