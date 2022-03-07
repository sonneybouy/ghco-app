package com.sonney.ghco.ghcoapp.model;

public enum Action {
    NEW("NEW"),
    AMEND("AMEND"),
    CANCEL("CANCEL");

    private String action;

    Action(String action) {
        this.action = action;
    }
}
