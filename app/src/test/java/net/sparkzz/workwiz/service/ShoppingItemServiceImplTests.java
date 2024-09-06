package net.sparkzz.workwiz.service;

import net.sparkzz.workwiz.model.ShoppingItem;
import net.sparkzz.workwiz.repository.ShoppingItemRepository;
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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingItemServiceImplTests {

    @Mock private ShoppingItemRepository repository;
    @InjectMocks private ShoppingItemServiceImpl service;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void test_getAllItems() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ShoppingItem> page = new PageImpl<>(Collections.emptyList());
        when(repository.findAll(pageable)).thenReturn(page);

        Page<ShoppingItem> result = service.getAllItems(pageable);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void test_searchItems() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ShoppingItem> page = new PageImpl<>(Collections.emptyList());
        when(repository.findByNameContaining(pageable, "query")).thenReturn(page);

        Page<ShoppingItem> result = service.searchItems(pageable, "query");

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(repository, times(1)).findByNameContaining(pageable, "query");
    }

    @Test
    void test_getItemById() {
        ShoppingItem item = new ShoppingItem();
        when(repository.findById(1L)).thenReturn(Optional.of(item));

        ShoppingItem result = service.getItemById(1L);

        assertNotNull(result);
        assertEquals(item, result);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void test_createItem() {
        ShoppingItem item = new ShoppingItem();
        when(repository.save(item)).thenReturn(item);

        ShoppingItem result = service.createItem(item);

        assertNotNull(result);
        assertEquals(item, result);
        verify(repository, times(1)).save(item);
    }

    @Test
    void test_updateItem() {
        ShoppingItem item = new ShoppingItem();
        item.setName("Updated Name");
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        when(repository.save(item)).thenReturn(item);

        Optional<ShoppingItem> result = service.updateItem(1L, item);

        assertTrue(result.isPresent());
        assertEquals("Updated Name", result.get().getName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(item);
    }

    @Test
    void test_deleteItem() {
        doNothing().when(repository).deleteById(1L);

        service.deleteItem(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
