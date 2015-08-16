package com.architext.accounts.registration;

import org.json.JSONObject;

public class RegistrationRequestBuilder {

    private String username;
    private String password;

    public static RegistrationRequestBuilder registrationRequest() {
        return new RegistrationRequestBuilder();
    }

    public RegistrationRequestBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public RegistrationRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public String build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return jsonObject.toString(1);
    }

}
