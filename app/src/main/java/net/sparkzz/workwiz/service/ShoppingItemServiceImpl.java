package net.sparkzz.workwiz.service;

import net.sparkzz.workwiz.model.ShoppingItem;
import net.sparkzz.workwiz.repository.ShoppingItemRepository;
import net.sparkzz.workwiz.service.interfaces.ShoppingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service implementation for managing {@link ShoppingItem}s.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
@Service
public class ShoppingItemServiceImpl implements ShoppingItemService {

    @Autowired
    private ShoppingItemRepository repository;

    /**
     * Get all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @return the list of shopping entities
     */
    @Override
    public Page<ShoppingItem> getAllItems(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Search all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @param query    the query to search
     * @return the list of {@link ShoppingItem} entities
     */
    @Override
    public Page<ShoppingItem> searchItems(Pageable pageable, String query) {
        return repository.findByNameContaining(pageable, query);
    }

    /**
     * Get a {@link ShoppingItem} by id.
     *
     * @param id the id of the {@link ShoppingItem}
     * @return the {@link ShoppingItem}
     */
    @Override
    public ShoppingItem getItemById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Create a {@link ShoppingItem}.
     *
     * @param item the {@link ShoppingItem} to create
     * @return the created {@link ShoppingItem}
     */
    @Override
    @Transactional
    public ShoppingItem createItem(ShoppingItem item) {
        return repository.save(item);
    }

    /**
     * Update a {@link ShoppingItem}.
     *
     * @param id   the id of the {@link ShoppingItem} to update
     * @param item the {@link ShoppingItem} to update
     * @return the updated {@link ShoppingItem} or an empty optional if the {@link ShoppingItem} was not found
     */
    @Override
    @Transactional
    public Optional<ShoppingItem> updateItem(Long id, ShoppingItem item) {
        return repository.findById(id).map(existingItem -> {
            if (item.getName() != null) existingItem.setName(item.getName());
            if (item.getDescription() != null) existingItem.setDescription(item.getDescription());
            if (item.getPrice() != null) existingItem.setPrice(item.getPrice());
            return repository.save(existingItem);
        });
    }

    /**
     * Delete a {@link ShoppingItem}.
     *
     * @param id the id of the {@link ShoppingItem} to delete
     */
    @Override
    @Transactional
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
