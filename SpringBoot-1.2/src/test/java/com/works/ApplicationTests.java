package com.works;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    public final String adminAuthorities = "[\"READ\"," + "\"WRITE\"," + "\"DELETE\"]";
    public final String guestAuthorities = "[\"WRITE\"]";

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> firstMyappContainer = new GenericContainer<>("devapp").withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> secondMyappContainer = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        firstMyappContainer.start();
        secondMyappContainer.start();
    }

    @Test
    void contextLoads() {
        int firstMyappContainerPort = firstMyappContainer.getMappedPort(8080);
		ResponseEntity<String> firstMyappContainerResponse = restTemplate.getForEntity("http://localhost:"
				+ firstMyappContainerPort
				+ "/authorize?user=Igor&password=1234", String.class);
		String firstMyappContainerResult = firstMyappContainerResponse.getBody();
        Assertions.assertEquals(adminAuthorities, firstMyappContainerResult);

        int secondMyappContainerPort = secondMyappContainer.getMappedPort(8081);
        ResponseEntity<String> secondMyappContainerResponse = restTemplate.getForEntity("http://localhost:"
				+ secondMyappContainerPort
				+ "/authorize?user=Alex&password=5678", String.class);
		String secondMyappContainerResult = secondMyappContainerResponse.getBody();
		Assertions.assertEquals(guestAuthorities, secondMyappContainerResult);
    }

}