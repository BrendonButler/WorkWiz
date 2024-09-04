package net.sparkzz.workwiz.service.interfaces;

import net.sparkzz.workwiz.model.ShoppingItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing {@link ShoppingItem}s.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
public interface ShoppingItemService {

    /**
     * Get all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @return the list of {@link ShoppingItem} entities
     */
    Page<ShoppingItem> getAllItems(Pageable pageable);

    /**
     * Search all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @param query    the query to search
     * @return the list of {@link ShoppingItem} entities
     */
    Page<ShoppingItem> searchItems(Pageable pageable, String query);

    /**
     * Get a {@link ShoppingItem} by id.
     *
     * @param id the id of the {@link ShoppingItem}
     * @return the {@link ShoppingItem}
     */
    ShoppingItem getItemById(Long id);

    /**
     * Create a {@link ShoppingItem}.
     *
     * @param item the {@link ShoppingItem} to create
     * @return the created {@link ShoppingItem}
     */
    ShoppingItem createItem(ShoppingItem item);

    /**
     * Update a {@link ShoppingItem}.
     *
     * @param id   the id of the {@link ShoppingItem} to update
     * @param item the {@link ShoppingItem} to update
     * @return the updated {@link ShoppingItem}
     */
    Optional<ShoppingItem> updateItem(Long id, ShoppingItem item);

    /**
     * Delete a {@link ShoppingItem}.
     *
     * @param id the id of the {@link ShoppingItem} to delete
     */
    void deleteItem(Long id);
}
