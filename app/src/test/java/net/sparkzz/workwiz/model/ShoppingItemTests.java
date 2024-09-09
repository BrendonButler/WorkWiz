package net.sparkzz.workwiz.model;

import net.sparkzz.workwiz.repository.ShoppingItemRepository;
import net.sparkzz.workwiz.service.ShoppingItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingItemTests {

    @Mock
    private ShoppingItemRepository repository;

    @InjectMocks
    private ShoppingItemServiceImpl service;

    @Test
    public void test_ShoppingItemEntity() {
        ShoppingItem item = new ShoppingItem();
        item.setId(1L);
        item.setName("Test Item");
        item.setDescription("This is a test item");
        item.setPrice(new BigDecimal("9.99"));

        when(repository.save(item)).thenReturn(item);

        ShoppingItem savedItem = service.createItem(item);

        assertNotNull(savedItem.getId());
        assertEquals("Test Item", savedItem.getName());
        assertEquals("This is a test item", savedItem.getDescription());
        assertEquals(new BigDecimal("9.99"), savedItem.getPrice());

        verify(repository, times(1)).save(item);
    }

    @Test
    public void test_ShoppingItem_gettersAndSetters() {
        ShoppingItem item = new ShoppingItem();
        item.setId(1L);
        item.setName("Test Item");
        item.setDescription("This is a test item");
        item.setPrice(new BigDecimal("9.99"));

        assertEquals(1L, item.getId());
        assertEquals("Test Item", item.getName());
        assertEquals("This is a test item", item.getDescription());
        assertEquals(new BigDecimal("9.99"), item.getPrice());
    }
}