package com.me.miguelferreira.platform.web.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.me.miguelferreira.platform.data.repositories.RequesterRepository;
import com.me.miguelferreira.platform.web.TestConfiguration;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class RequesterControllerTest {

    private MockMvc mvc;

    @Autowired
    private RequesterRepository repository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new RequesterController(repository)).build();
    }

    @Test
    public void test_getRoot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void test_getRequester() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/requester"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("requester", HasPropertyWithValue.hasProperty("id", is(0L))))
           .andExpect(MockMvcResultMatchers.model().attribute("requester", HasPropertyWithValue.hasProperty("name", nullValue())))
           .andExpect(MockMvcResultMatchers.model().attribute("requesters", IsEmptyCollection.empty()))
           .andExpect(MockMvcResultMatchers.forwardedUrl("requesterView"));
    }

    @Test
    @Transactional
    public void test_postRequester() throws Exception {
        final String providerName = "testRequester";
        mvc.perform(MockMvcRequestBuilders.post("/requester").param("name", providerName))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("requesters", IsCollectionWithSize.hasSize(1)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("requesterView"));
        mvc.perform(MockMvcRequestBuilders.post("/requester").param("name", providerName))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("requesters", IsCollectionWithSize.hasSize(2)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("requesterView"));
    }
}
