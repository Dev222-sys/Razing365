package com.razinggroups.domain.model;

public class SingleIdRequest {

    private String id;

    public SingleIdRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
