package com.razinggroups.data.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginNw {


    @SerializedName("msg")
    @Expose
    public String msg;

    @SerializedName("type")
    public String type;

    @SerializedName("emp_id")
    public String employeeId;

    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginNw(String msg, String type) {
        this.msg = msg;
        this.type = type;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
