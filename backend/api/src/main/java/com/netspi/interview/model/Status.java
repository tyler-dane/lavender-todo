package com.netspi.interview.model;

public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETE("COMPLETE");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}