package com.thoughtworks.model.errors;

import java.util.List;

public class Errors {

    private final List<Error> errors;

    public Errors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
