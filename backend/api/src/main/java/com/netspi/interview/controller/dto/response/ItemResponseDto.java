package com.netspi.interview.controller.dto.response;

import com.netspi.interview.model.Item;

public class ItemResponseDto {
    private Long id;
    private String name;
    private String status;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.status = item.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
