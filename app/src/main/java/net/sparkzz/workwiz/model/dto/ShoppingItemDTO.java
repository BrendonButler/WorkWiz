package net.sparkzz.workwiz.model.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for ShoppingItem.
 *
 * @param id the ID of the item
 * @param name the name of the item
 * @param description the description of the item
 * @param price the price of the item
 */
public record ShoppingItemDTO(Long id, String name, String description, BigDecimal price) {
}
