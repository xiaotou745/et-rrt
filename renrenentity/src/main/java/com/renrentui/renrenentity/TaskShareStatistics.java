package com.renrentui.renrenentity;

import java.util.Date;

public class TaskShareStatistics {
    private Long id;

    private Long taskid;

    private Long clienterid;

    private Date createdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Long getClienterid() {
        return clienterid;
    }

    public void setClienterid(Long clienterid) {
        this.clienterid = clienterid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}