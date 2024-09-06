package net.sparkzz.workwiz.repository;

import net.sparkzz.workwiz.model.ShoppingItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class ShoppingItemRepositoryTests {

    @Autowired
    private ShoppingItemRepository repository;

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

        repository.save(item1);
        repository.save(item2);
    }

    @Test
    void testFindByNameContaining() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ShoppingItem> result = repository.findByNameContaining(pageable, "Apple");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Apple", result.getContent().getFirst().getName());
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ShoppingItem> result = repository.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        List<ShoppingItem> items = result.getContent();
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}
