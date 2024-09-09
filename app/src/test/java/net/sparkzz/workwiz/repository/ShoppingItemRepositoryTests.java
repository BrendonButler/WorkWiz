package net.sparkzz.workwiz.repository;

import net.sparkzz.workwiz.model.ShoppingItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingItemRepositoryTests {

    @Mock
    private ShoppingItemRepository repository;

    private final Pageable pageable = PageRequest.of(0, 10);
    private ShoppingItem item1;
    private ShoppingItem item2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        item1 = new ShoppingItem();
        item1.setName("Apple");
        item1.setDescription("A juicy red apple");
        item1.setPrice(BigDecimal.valueOf(1.0));

        item2 = new ShoppingItem();
        item2.setName("Banana");
        item2.setDescription("A ripe yellow banana");
        item2.setPrice(BigDecimal.valueOf(0.5));
    }

    @Test
    void testFindByNameContaining() {
        when(repository.findByNameContaining(pageable, "Apple")).thenReturn(new PageImpl<>(List.of(item1)));

        Page<ShoppingItem> result = repository.findByNameContaining(pageable, "Apple");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Apple", result.getContent().getFirst().getName());
    }

    @Test
    void testFindAll() {
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(item1, item2)));

        Page<ShoppingItem> result = repository.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        List<ShoppingItem> items = result.getContent();
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}
