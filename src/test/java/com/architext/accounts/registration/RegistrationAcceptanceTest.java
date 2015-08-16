package com.architext.accounts.registration;

import com.architech.exercise.MvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.architext.accounts.registration.RegistrationRequestBuilder.registrationRequest;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, LocalFileConfig.class})
public class RegistrationAcceptanceTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Users users;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        users.noUsers();
    }

    @Test
    public void when_user_is_already_registered_errors_will_be_returned() throws Exception {

        users.userAlreadyRegistered("validUsername");

        String requestJson = registrationRequest().withUsername("validUsername").withPassword("validPassword1").build();

        this.mockMvc.perform(post("/accounts/register").content(requestJson).accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.usernameErrors[0]").value("User already registered"));
    }

    @Test
    public void given_a_valid_password_and_username_user_is_registered() throws Exception {
        String requestJson = registrationRequest().withUsername("validUsername").withPassword("validPassword1").build();

        this.mockMvc.perform(post("/accounts/register").content(requestJson).accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void given_a_password_that_does_not_meet_constraints_will_return_error() throws Exception {
        String requestJson = registrationRequest().withUsername("validUsername").withPassword("invalid_password").build();

        this.mockMvc.perform(post("/accounts/register").content(requestJson).accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.passwordErrors[0]").value(containsString("Password must have ")));
    }

    @Test
    public void given_a_wrong_pass_and_username_will_return_two_errors() throws Exception {
        String requestJson = registrationRequest().withUsername("shrt").withPassword("invalid_password").build();

        this.mockMvc.perform(post("/accounts/register").content(requestJson).accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.usernameErrors[0]").value(containsString("Username length should be more ")))
                .andExpect(jsonPath("$.passwordErrors[0]").value(containsString("Password must have ")));
    }

}
