package com.example.internationaljobs.modles;

public class JobModle {
    private String id,title,description,requirment,company,applyLink,startDate,endDDAte,country,status,userID,CataID,createdDate;

    public JobModle(String id, String title, String description, String requirment,
                    String company, String applyLink, String startDate, String endDDAte, String country, String status, String userID, String cataID, String createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requirment = requirment;
        this.company = company;
        this.applyLink = applyLink;
        this.startDate = startDate;
        this.endDDAte = endDDAte;
        this.country = country;
        this.status = status;
        this.userID = userID;
        CataID = cataID;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirment() {
        return requirment;
    }

    public void setRequirment(String requirment) {
        this.requirment = requirment;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDDAte() {
        return endDDAte;
    }

    public void setEndDDAte(String endDDAte) {
        this.endDDAte = endDDAte;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCataID() {
        return CataID;
    }

    public void setCataID(String cataID) {
        CataID = cataID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
