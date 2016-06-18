package com.me.miguelferreira.platform.controllers;

import com.me.miguelferreira.platform.model.Requester;
import com.me.miguelferreira.platform.repositories.AssignmentRepository;
import com.me.miguelferreira.platform.repositories.RequesterRepository;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class AssignmentControllerTest {

    private MockMvc mvc;

    private final AssignmentRepository assignmentRepository = new AssignmentRepository();
    private final RequesterRepository requesterRepository = new RequesterRepository();
    private final Requester requester = new Requester();

    @Before
    public void setUp() throws Exception {
        requester.setId("requesterId");
        requester.setName("requesterName");
        requesterRepository.add(requester);

        final AssignmentController assignmentController = new AssignmentController(assignmentRepository, requesterRepository);
        mvc = MockMvcBuilders.standaloneSetup(assignmentController).build();

    }

    @Test
    public void test_getRoot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/assignment"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("assignment", hasProperty("id", nullValue())))
                .andExpect(model().attribute("assignment", hasProperty("name", nullValue())))
                .andExpect(model().attribute("assignments", empty()))
                .andExpect(forwardedUrl("assignmentView"));
    }

    @Test
    public void test_post() throws Exception {
        final String assignmentName = "testAssignment";
        final MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.post("/assignment")
                .param("name", assignmentName)
                .param("requester.id", requester.getId());
        mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(model().attribute("assignments", hasSize(1)))
                .andExpect(forwardedUrl("assignmentView"));
        final MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders.post("/assignment")
                .param("name", assignmentName)
                .param("requester.id", requester.getId());
        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(model().attribute("assignments", hasSize(2)))
                .andExpect(forwardedUrl("assignmentView"));

        assertThat(assignmentRepository.find("0").getRequester().getId(), is(requester.getId()));
        assertThat(assignmentRepository.find("1").getRequester().getId(), is(requester.getId()));
    }
}