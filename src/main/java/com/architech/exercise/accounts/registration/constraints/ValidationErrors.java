package com.architech.exercise.accounts.registration.constraints;

import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.ArrayListMultimap.create;

public class ValidationErrors {

    private final Multimap<String, String> fieldAndMessage = create();

    private void putAll(Multimap<String, String> fieldAndMessage) {
        this.fieldAndMessage.putAll(fieldAndMessage);
    }

    public void validationErrorFor(String field, String message) {
        fieldAndMessage.put(field, message);
    }

    public boolean containsErrors() {
        return fieldAndMessage.size() > 0;
    }

    public List<String> valuesForField(String fieldName) {
        return new ArrayList<>(fieldAndMessage.get(fieldName));
    }

    public ValidationErrors merge(ValidationErrors p) {
        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.putAll(this.fieldAndMessage);
        validationErrors.putAll(p.fieldAndMessage);
        return validationErrors;
    }

    public int size() {
        return fieldAndMessage.size();
    }

}
