package com.example.demo3glovo.integrationTests;

import com.example.demo3glovo.Demo3GlovoApplication;
import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Demo3GlovoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndGetItem() throws Exception {
        ItemDto itemDto = ItemDto.builder()
                .id(1)
                .name("Pizza")
                .quantity(2)
                .price(10.0)
                .produkt(1)
                .build();


        HttpEntity<ItemDto> request = new HttpEntity<>(itemDto);
        ResponseEntity<ItemDto> response = restTemplate.exchange(
                "http://localhost:" + port + "/items",
                HttpMethod.POST,
                request,
                ItemDto.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        ItemDto savedItem = response.getBody();
        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getId()).isEqualTo(itemDto.getId());

        ResponseEntity<ItemDto> getItemResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/items/" + savedItem.getId(),
                ItemDto.class
        );

        assertThat(getItemResponse.getStatusCode().is2xxSuccessful()).isTrue();
        ItemDto retrievedItem = getItemResponse.getBody();
        assertThat(retrievedItem).isNotNull();
        assertThat(retrievedItem.getId()).isEqualTo(itemDto.getId());
        assertThat(retrievedItem.getName()).isEqualTo(itemDto.getName());

        itemRepository.deleteById(savedItem.getId());
    }
}
