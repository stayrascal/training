package com.thoughtworks.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Greeting extends BaseEntity {

    private static final long serialVersionUID = 8807080843569617685L;

    @NotNull
    private String text;

    public Greeting() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
