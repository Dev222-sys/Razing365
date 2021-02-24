package com.razinggroups.domain.model.CustomerQuery;

public class FamilyDetails {


    public String id;
    public String relation;
    public String full_name;
    public String passport_no;
    public String age;
    public String Landline;
    public String passport_copy;
    public String update_at;

    public FamilyDetails(String id, String relation, String full_name, String passport_no, String age, String landline, String passport_copy, String update_at) {
        this.id = id;
        this.relation = relation;
        this.full_name = full_name;
        this.passport_no = passport_no;
        this.age = age;
        Landline = landline;
        this.passport_copy = passport_copy;
        this.update_at = update_at;

    }

    public FamilyDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLandline() {
        return Landline;
    }

    public void setLandline(String landline) {
        Landline = landline;
    }

    public String getPassport_copy() {
        return passport_copy;
    }

    public void setPassport_copy(String passport_copy) {
        this.passport_copy = passport_copy;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}