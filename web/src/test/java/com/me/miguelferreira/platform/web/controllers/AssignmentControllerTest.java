package com.me.miguelferreira.platform.web.controllers;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.Mockito.when;

import com.me.miguelferreira.platform.data.repositories.AssignmentRepository;
import com.me.miguelferreira.platform.data.repositories.RequesterRepository;
import com.me.miguelferreira.platform.model.Assignment;
import com.me.miguelferreira.platform.model.Requester;
import com.me.miguelferreira.platform.web.TestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class AssignmentControllerTest {

    private MockMvc mvc;

    @Mock
    private AssignmentRepository assignmentRepository;
    @Mock
    private RequesterRepository requesterRepository;

    private final Requester requester = new Requester();
    private final long requesterId = 0L;

    @Before
    public void setUp() throws Exception {
        requester.setId(requesterId);

        final AssignmentController assignmentController = new AssignmentController(assignmentRepository, requesterRepository);
        mvc = MockMvcBuilders.standaloneSetup(assignmentController).build();
    }

    @Test
    public void test_getRoot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void test_get() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/assignment"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("assignment", hasProperty("id", is(0L))))
           .andExpect(MockMvcResultMatchers.model().attribute("assignment", hasProperty("name", nullValue())))
           .andExpect(MockMvcResultMatchers.model().attribute("assignments", empty()))
           .andExpect(MockMvcResultMatchers.forwardedUrl("assignmentView"));
    }

    @Test
    public void test_post() throws Exception {
        final Assignment assignment1 = new Assignment();
        assignment1.setId(0L);
        assignment1.setRequester(requester);
        final Assignment assignment2 = new Assignment();
        assignment2.setId(1L);
        assignment2.setRequester(requester);
        when(assignmentRepository.findOne(0L)).thenReturn(assignment1);
        when(assignmentRepository.findOne(1L)).thenReturn(assignment2);
        when(assignmentRepository.findAll()).thenReturn(asList(assignment1))
                                            .thenReturn(asList(assignment1, assignment2));

        final String assignmentName = "testAssignment";
        final MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders
                .post("/assignment")
                .param("name", assignmentName)
                .param("requester.id", Long.toString(requester.getId()));
        mvc.perform(request1).andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("assignments", hasSize(1)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("assignmentView"));
        final MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders
                .post("/assignment")
                .param("name", assignmentName)
                .param("requester.id", Long.toString(requester.getId()));
        mvc.perform(request2).andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().attribute("assignments", hasSize(2)))
           .andExpect(MockMvcResultMatchers.forwardedUrl("assignmentView"));

        assertThat(assignmentRepository.findOne(0L).getRequester().getId(), is(requester.getId()));
        assertThat(assignmentRepository.findOne(1L).getRequester().getId(), is(requester.getId()));
    }
}
