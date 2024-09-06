package net.sparkzz.workwiz.controller;

import net.sparkzz.workwiz.model.ResponseBody;
import net.sparkzz.workwiz.model.ShoppingItem;
import net.sparkzz.workwiz.model.dto.ShoppingItemDTO;
import net.sparkzz.workwiz.service.ShoppingItemServiceImpl;
import net.sparkzz.workwiz.util.MockShoppingItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ShoppingItemControllerTests {

    @Mock private ShoppingItemServiceImpl service;
    @InjectMocks private ShoppingItemController controller;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void test_getAllItems() {
        Page<ShoppingItem> page = new PageImpl<>(Collections.emptyList());

        when(service.getAllItems(any(Pageable.class))).thenReturn(page);

        ResponseEntity<List<ShoppingItemDTO>> response = controller.getAllItems(PageRequest.of(0, 10));
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void test_getAllItems_withMockItems() {
        List<ShoppingItem> mockItems = MockShoppingItems.generateRandomItems(2);

        Page<ShoppingItem> page = new PageImpl<>(mockItems);

        when(service.getAllItems(any(Pageable.class))).thenReturn(page);

        ResponseEntity<List<ShoppingItemDTO>> response = controller.getAllItems(PageRequest.of(0, 10));

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(mockItems.getFirst().getId(), response.getBody().getFirst().id());
        assertEquals(mockItems.getFirst().getName(), response.getBody().getFirst().name());
        assertEquals(mockItems.getFirst().getDescription(), response.getBody().getFirst().description());
        assertEquals(mockItems.getFirst().getPrice(), response.getBody().getFirst().price());
        assertEquals(mockItems.get(1).getId(), response.getBody().get(1).id());
        assertEquals(mockItems.get(1).getName(), response.getBody().get(1).name());
        assertEquals(mockItems.get(1).getDescription(), response.getBody().get(1).description());
        assertEquals(mockItems.get(1).getPrice(), response.getBody().get(1).price());
    }

    @Test
    void test_getItemById() {
        ShoppingItem mockItem = MockShoppingItems.generateRandomItem();

        when(service.getItemById(any(Long.class))).thenReturn(mockItem);

        ResponseEntity<?> response = controller.getItemById(mockItem.getId());

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(mockItem.getId(), ((ShoppingItemDTO) response.getBody()).id());
        assertEquals(mockItem.getName(), ((ShoppingItemDTO) response.getBody()).name());
        assertEquals(mockItem.getDescription(), ((ShoppingItemDTO) response.getBody()).description());
        assertEquals(mockItem.getPrice(), ((ShoppingItemDTO) response.getBody()).price());
    }

    @Test
    void test_getItemById_notFound() {
        when(service.getItemById(any(Long.class))).thenReturn(null);

        ResponseEntity<?> response = controller.getItemById(1L);

        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(404, ((ResponseBody) response.getBody()).getStatus());
        assertEquals("Item not found with ID (1)", ((ResponseBody) response.getBody()).getMessage());
    }

    @Test
    void test_searchItems() {
        Page<ShoppingItem> page = new PageImpl<>(Collections.emptyList());

        when(service.searchItems(any(Pageable.class), any(String.class))).thenReturn(page);

        ResponseEntity<List<ShoppingItemDTO>> response = controller.searchItems(PageRequest.of(0, 10), "test");

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void test_searchItems_withMockItems() {
        List<ShoppingItem> mockItems = MockShoppingItems.generateRandomItems(4);
        List<ShoppingItem> pagedItems = mockItems.subList(0, 2);
        Page<ShoppingItem> page = new PageImpl<>(pagedItems, PageRequest.of(0, 2), mockItems.size());

        when(service.searchItems(any(Pageable.class), any(String.class))).thenReturn(page);

        ResponseEntity<List<ShoppingItemDTO>> response = controller.searchItems(PageRequest.of(0, 2), "test");

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(pagedItems.getFirst().getId(), response.getBody().getFirst().id());
        assertEquals(pagedItems.getFirst().getName(), response.getBody().getFirst().name());
        assertEquals(pagedItems.getFirst().getDescription(), response.getBody().getFirst().description());
        assertEquals(pagedItems.getFirst().getPrice(), response.getBody().getFirst().price());
        assertEquals(pagedItems.get(1).getId(), response.getBody().get(1).id());
        assertEquals(pagedItems.get(1).getName(), response.getBody().get(1).name());
        assertEquals(pagedItems.get(1).getDescription(), response.getBody().get(1).description());
        assertEquals(pagedItems.get(1).getPrice(), response.getBody().get(1).price());
    }

    @Test
    void test_createItem() {
        ShoppingItem mockItem = MockShoppingItems.generateRandomItem();

        when(service.createItem(any(ShoppingItem.class))).thenReturn(mockItem);

        ResponseEntity<?> response = controller.createItem(mockItem);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(mockItem.getId(), ((ShoppingItemDTO) response.getBody()).id());
        assertEquals(mockItem.getName(), ((ShoppingItemDTO) response.getBody()).name());
        assertEquals(mockItem.getDescription(), ((ShoppingItemDTO) response.getBody()).description());
        assertEquals(mockItem.getPrice(), ((ShoppingItemDTO) response.getBody()).price());
    }

    @Test
    void test_updateItem() {
        ShoppingItem mockItem = MockShoppingItems.generateRandomItem();

        when(service.updateItem(any(Long.class), any(ShoppingItem.class))).thenReturn(java.util.Optional.of(mockItem));

        ResponseEntity<?> response = controller.updateItem(mockItem.getId(), mockItem);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(mockItem.getId(), ((ShoppingItemDTO) response.getBody()).id());
        assertEquals(mockItem.getName(), ((ShoppingItemDTO) response.getBody()).name());
        assertEquals(mockItem.getDescription(), ((ShoppingItemDTO) response.getBody()).description());
        assertEquals(mockItem.getPrice(), ((ShoppingItemDTO) response.getBody()).price());
    }

    @Test
    void test_updateItem_notFound() {
        ShoppingItem mockItem = MockShoppingItems.generateRandomItem();

        when(service.updateItem(any(Long.class), any(ShoppingItem.class))).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = controller.updateItem(mockItem.getId(), mockItem);

        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(404, ((ResponseBody) response.getBody()).getStatus());
        assertEquals("Item not found with ID (" + mockItem.getId() + ")", ((ResponseBody) response.getBody()).getMessage());
    }
}
