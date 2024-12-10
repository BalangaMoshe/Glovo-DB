package com.example.demo3glovo.integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthTest() {
        String url = "http://localhost:" + port + "/health";
        String response = this.restTemplate.getForObject(url, String.class);
        assertThat(response).contains("I am alive");
    }
}
