package org.remast.baralga.repository;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BaralgaRestRepositoryTest {

    private MockWebServer mockWebServer;

    private BaralgaRestRepository repository;

    @BeforeEach
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        repository = new BaralgaRestRepository(
                mockWebServer.url("/").toString(),
                "admin",
                "adm1n"
        );
        repository.initialize();
    }

    @Test
    void getAllProjects() {
        // Arrange
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("[ {\"id\": \"1234\", \"title\":\"My Title\", \"description\":\"My Description\", \"active\": true} ]");
        mockWebServer.enqueue(mockResponse);

        // Act
        List<ProjectVO> projects = repository.getAllProjects();

        // Assert
        assertNotNull(projects);
        assertEquals(1, projects.size());

        ProjectVO project = projects.get(0);
        assertEquals("1234", project.getId());
        assertEquals("My Title", project.getTitle());
        assertEquals("My Description", project.getDescription());
        assertEquals(true, project.isActive());
    }

    @Test
    void findProjectById() {
        // Arrange
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("{\"id\": \"1234\", \"title\":\"My Title\", \"description\":\"My Description\", \"active\": true}");
        mockWebServer.enqueue(mockResponse);

        // Act
        Optional<ProjectVO> projectOpt = repository.findProjectById("1234");

        // Assert
        assertEquals(true, projectOpt.isPresent());

        ProjectVO project = projectOpt.get();
        assertEquals("1234", project.getId());
        assertEquals("My Title", project.getTitle());
        assertEquals("My Description", project.getDescription());
        assertEquals(true, project.isActive());
    }

    @Test
    void getActivities() {
        // Arrange
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("{ \"data\": [ { \"id\": \"5678\", \"start\": \"2020-11-21T10:46:28\", \"end\": \"2020-11-21T16:46:28\", \"projectRef\": \"1234\", \"description\": \"My Desc\"} ], \"projectRefs\": [ {\"id\": \"1234\", \"title\":\"My Title\", \"description\":\"My Description\", \"active\": true} ] }");
        mockWebServer.enqueue(mockResponse);

        // Act
        List<ActivityVO> activities = repository.getActivities();

        // Assert
        assertNotNull(activities);
        assertEquals(1, activities.size());

        ActivityVO activity = activities.get(0);
        assertNotNull(activity);
        assertEquals("5678", activity.getId());
        assertNotNull(activity.getProject());
    }

}
