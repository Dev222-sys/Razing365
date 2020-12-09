package com.razinggroups.domain.model;

public class Login {

    String msg;
    String type;
    String empId;
    String name;
    String email;

    public Login(String msg, String type, String empId, String name) {
        this.msg = msg;
        this.type = type;
        this.empId = empId;
        this.name = name;
    }
    public Login(String msg, String type, String empId, String name,String email) {
        this.email=email;
        this.msg = msg;
        this.type = type;
        this.empId = empId;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
