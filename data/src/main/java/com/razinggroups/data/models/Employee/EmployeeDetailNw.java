package com.razinggroups.data.models.Employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeDetailNw {


    @SerializedName("count")
    public String count="";

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("designation")
    @Expose
    public String designation;
    @SerializedName("landline")
    @Expose
    public String landline;
    @SerializedName("official_mail")
    @Expose
    public String officialMail;
    @SerializedName("personal_mail")
    @Expose
    public String personalMail;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("dol")
    @Expose
    public String dol;
    @SerializedName("appointment_letter")
    @Expose
    public String appointmentLetter;
    @SerializedName("office_address")
    @Expose
    public String officeAddress;
    @SerializedName("residence_address")
    @Expose
    public String residenceAddress;
    @SerializedName("salary")
    @Expose
    public String salary;
    @SerializedName("emergency_name")
    @Expose
    public String emergencyName;
    @SerializedName("emergency_no")
    @Expose
    public String emergencyNo;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("doj")
    @Expose
    public String doj;
    @SerializedName("aadhar_no")
    @Expose
    public String aadharNo;
    @SerializedName("aadhar_card")
    @Expose
    public String aadharCard;
    @SerializedName("pan_no")
    @Expose
    public String panNo;
    @SerializedName("pan_card")
    @Expose
    public String panCard;
    @SerializedName("education")
    @Expose
    public String education;
    @SerializedName("profile_img")
    @Expose
    public String profileImg;
    @SerializedName("emp_status")
    @Expose
    public String empStatus;
    @SerializedName("brand_name")
    @Expose
    public String brandName;
    @SerializedName("brand_id")
    @Expose
    public String brandId;

    public EmployeeDetailNw(String id, String location, String name, String designation, String landline, String officialMail, String personalMail, String mobile, String dol, String appointmentLetter, String officeAddress, String residenceAddress, String salary, String emergencyName, String emergencyNo, String dob, String doj, String aadharNo, String aadharCard, String panNo, String panCard, String education, String profileImg, String empStatus, String brandName, String brandId) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.designation = designation;
        this.landline = landline;
        this.officialMail = officialMail;
        this.personalMail = personalMail;
        this.mobile = mobile;
        this.dol = dol;
        this.appointmentLetter = appointmentLetter;
        this.officeAddress = officeAddress;
        this.residenceAddress = residenceAddress;
        this.salary = salary;
        this.emergencyName = emergencyName;
        this.emergencyNo = emergencyNo;
        this.dob = dob;
        this.doj = doj;
        this.aadharNo = aadharNo;
        this.aadharCard = aadharCard;
        this.panNo = panNo;
        this.panCard = panCard;
        this.education = education;
        this.profileImg = profileImg;
        this.empStatus = empStatus;
        this.brandName = brandName;
        this.brandId = brandId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getOfficialMail() {
        return officialMail;
    }

    public void setOfficialMail(String officialMail) {
        this.officialMail = officialMail;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDol() {
        return dol;
    }

    public void setDol(String dol) {
        this.dol = dol;
    }

    public String getAppointmentLetter() {
        return appointmentLetter;
    }

    public void setAppointmentLetter(String appointmentLetter) {
        this.appointmentLetter = appointmentLetter;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyNo() {
        return emergencyNo;
    }

    public void setEmergencyNo(String emergencyNo) {
        this.emergencyNo = emergencyNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
