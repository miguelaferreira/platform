package com.me.miguelferreira.platform.web.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.me.miguelferreira.platform.data.repositories.ProviderRepository;
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
public class ProviderControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProviderRepository providerRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ProviderController(providerRepository)).build();
    }

    @Test
    public void test_getRoot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void test_get() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/provider"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("provider", HasPropertyWithValue.hasProperty("id", is(0L))))
           .andExpect(MockMvcResultMatchers.model().attribute("provider", HasPropertyWithValue.hasProperty("name", nullValue())))
           .andExpect(MockMvcResultMatchers.model().attribute("providers", IsEmptyCollection.empty()))
           .andExpect(MockMvcResultMatchers.forwardedUrl("providerView"));
    }

    @Test
    @Transactional
    public void test_post() throws Exception {
        final String providerName = "testProvider";
        mvc.perform(MockMvcRequestBuilders.post("/provider").param("name", providerName))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("providers", IsCollectionWithSize.hasSize(1)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("providerView"));
        mvc.perform(MockMvcRequestBuilders.post("/provider").param("name", providerName))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("providers", IsCollectionWithSize.hasSize(2)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("providerView"));
    }
}
