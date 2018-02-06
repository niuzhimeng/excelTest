package com.nzm.model.po;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PartnerCompany {
    private String id;

    private String companyId;

    private String name;

    private Integer tenure;

    private Integer experience;

    @SerializedName("type")
    private String nzm;

    private List<Test> testList;

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public String getNzm() {
        return nzm;
    }

    public void setNzm(String nzm) {
        this.nzm = nzm;
    }

    @Override
    public String toString() {
        return "PartnerCompany{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", name='" + name + '\'' +
                ", tenure=" + tenure +
                ", experience=" + experience +
                ", nzm='" + nzm + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

}