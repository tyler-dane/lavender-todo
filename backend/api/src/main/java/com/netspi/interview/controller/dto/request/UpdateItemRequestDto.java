package com.netspi.interview.controller.dto.request;

import com.netspi.interview.model.Status;

public class UpdateItemRequestDto {
    private Status status;

    public UpdateItemRequestDto() {
    }

    public UpdateItemRequestDto(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getStatusOrder() {
        return status.ordinal();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
