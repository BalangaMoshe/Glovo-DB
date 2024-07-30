package com.example.demo3glovo.integrationTests;

import com.example.demo3glovo.Demo3GlovoApplication;
import com.example.demo3glovo.converter.OrderConverter;
import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.entity.OrderEntity;
import com.example.demo3glovo.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Demo3GlovoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateAndGetOrder() throws Exception {

        OrderDto orderDto = OrderDto.builder()
                .id(1)
                .userName("John Doe")
                .checkoutDate(LocalDate.of(2024, 7, 30))
                .totalPrice(100.0)
                .items(List.of(1, 2))
                .build();

        HttpEntity<OrderDto> request = new HttpEntity<>(orderDto);
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                "http://localhost:" + port + "/orders",
                HttpMethod.POST,
                request,
                OrderDto.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        OrderDto savedOrder = response.getBody();
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isEqualTo(orderDto.getId());

        ResponseEntity<OrderDto> getOrderResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/orders/" + savedOrder.getId(),
                OrderDto.class
        );


        assertThat(getOrderResponse.getStatusCode().is2xxSuccessful()).isTrue();
        OrderDto retrievedOrder = getOrderResponse.getBody();
        assertThat(retrievedOrder).isNotNull();
        assertThat(retrievedOrder.getId()).isEqualTo(orderDto.getId());
        assertThat(retrievedOrder.getUserName()).isEqualTo(orderDto.getUserName());

        orderRepository.deleteById(savedOrder.getId());
    }
}