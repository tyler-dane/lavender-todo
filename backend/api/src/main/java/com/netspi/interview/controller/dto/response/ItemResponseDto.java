package com.netspi.interview.controller.dto.response;

import com.netspi.interview.model.Item;

public class ItemResponseDto {
    private Long id;
    private String name;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
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
}
