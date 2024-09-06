package net.sparkzz.workwiz.model.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingItemDTOTests {

    @Test
    public void test_ShoppingItemDTO() {
        Long id = 1L;
        String name = "Test Item";
        String description = "This is a test item";
        BigDecimal price = new BigDecimal("9.99");

        ShoppingItemDTO itemDTO = new ShoppingItemDTO(id, name, description, price);

        assertEquals(id, itemDTO.id());
        assertEquals(name, itemDTO.name());
        assertEquals(description, itemDTO.description());
        assertEquals(price, itemDTO.price());
    }
}
