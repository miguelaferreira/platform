package com.me.miguelferreira.platform.data.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import com.me.miguelferreira.platform.data.TestConfiguration;
import com.me.miguelferreira.platform.model.Assignment;
import com.me.miguelferreira.platform.model.Requester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository repository;

    @Test
    public void save_testObject() throws Exception {
        final Assignment assignment = new Assignment();
        assignment.setName("assignment");
        assignment.setRequester(new Requester());

        repository.save(assignment);

        assertThat(assignment.getId(), notNullValue());
    }
}
