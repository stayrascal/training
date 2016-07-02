package com.thoughtworks.model;

import com.thoughtworks.validation.annotation.ValidText;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;

@Entity
public class Greeting extends BaseEntity {

    private static final long serialVersionUID = 8807080843569617685L;

    @ValidText(field = "just for test", message = "{text.other}")
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
