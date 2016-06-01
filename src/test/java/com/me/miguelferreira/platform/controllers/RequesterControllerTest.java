package com.me.miguelferreira.platform.controllers;

import com.me.miguelferreira.platform.repositories.RequesterRepository;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class RequesterControllerTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new RequesterController(new RequesterRepository())).build();
    }

    @Test
    public void test_getRoot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_getRequester() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/requester"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("requester", hasProperty("id", nullValue())))
                .andExpect(model().attribute("requester", hasProperty("name", nullValue())))
                .andExpect(model().attribute("requesters", empty()))
                .andExpect(forwardedUrl("requesterView"));
    }

    @Test
    public void test_postRequester() throws Exception {
        final String providerName = "testRequester";
        mvc.perform(MockMvcRequestBuilders.post("/requester").param("name", providerName))
                .andExpect(status().isOk())
                .andExpect(model().attribute("requesters", hasSize(1)))
                .andExpect(forwardedUrl("requesterView"));
        mvc.perform(MockMvcRequestBuilders.post("/requester").param("name", providerName))
                .andExpect(status().isOk())
                .andExpect(model().attribute("requesters", hasSize(2)))
                .andExpect(forwardedUrl("requesterView"));
    }

}