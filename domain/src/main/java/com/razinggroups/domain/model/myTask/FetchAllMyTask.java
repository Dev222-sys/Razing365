package com.razinggroups.domain.model.myTask;

import java.util.List;

public class FetchAllMyTask {

    public List<FetchAllMyTaskRecords> records = null;

    public FetchAllMyTask(List<FetchAllMyTaskRecords> records) {
        this.records = records;
    }

    public List<FetchAllMyTaskRecords> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllMyTaskRecords> records) {
        this.records = records;
    }
}
