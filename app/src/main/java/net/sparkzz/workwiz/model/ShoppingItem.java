package net.sparkzz.workwiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

/**
 * Entity for shopping items.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
@Entity
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    /**
     * Get the ID of the item.
     *
     * @return the ID of the item
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the item.
     *
     * @param id the ID of the item
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item.
     *
     * @param name the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the item.
     *
     * @param description the description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the item.
     *
     * @return the price of the item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the price of the item.
     *
     * @param price the price of the item
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
