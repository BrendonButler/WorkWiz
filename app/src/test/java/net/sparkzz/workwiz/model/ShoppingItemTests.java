// ShoppingItemTests.java
package net.sparkzz.workwiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(SpringExtension.class)
public class ShoppingItemTests {

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.clear();
    }

    @Test
    public void test_ShoppingItemEntity() {
        ShoppingItem item = new ShoppingItem();
        item.setName("Test Item");
        item.setDescription("This is a test item");
        item.setPrice(new BigDecimal("9.99"));

        ShoppingItem savedItem = entityManager.persistAndFlush(item);

        assertNotNull(savedItem.getId());
        assertEquals("Test Item", savedItem.getName());
        assertEquals("This is a test item", savedItem.getDescription());
        assertEquals(new BigDecimal("9.99"), savedItem.getPrice());
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