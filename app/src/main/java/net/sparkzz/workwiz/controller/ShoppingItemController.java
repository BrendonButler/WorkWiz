package net.sparkzz.workwiz.controller;

import net.sparkzz.workwiz.model.ResponseBody;
import net.sparkzz.workwiz.model.ShoppingItem;
import net.sparkzz.workwiz.model.dto.ShoppingItemDTO;
import net.sparkzz.workwiz.service.ShoppingItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing {@link ShoppingItem}s.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/api/shopping/items")
@Validated
public class ShoppingItemController {

    private final ShoppingItemServiceImpl service;

    private ShoppingItemDTO convertToDTO(ShoppingItem item) {
        return new ShoppingItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice());
    }

    /**
     * Constructor for the controller.
     *
     * @param service the {@link ShoppingItemServiceImpl} to use
     */
    public ShoppingItemController(@Autowired ShoppingItemServiceImpl service) {
        this.service = service;
    }

    /**
     * Get all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @return the list of {@link ShoppingItemDTO} entities
     */
    @GetMapping
    public ResponseEntity<List<ShoppingItemDTO>> getAllItems(Pageable pageable) {
        Page<ShoppingItemDTO> page = service.getAllItems(pageable).map(this::convertToDTO);

        return ResponseEntity.ok(page.getContent());
    }

    /**
     * Get a {@link ShoppingItem} by id.
     *
     * @param id the id of the {@link ShoppingItem}
     * @return the {@link ShoppingItemDTO} or a 404 response if the {@link ShoppingItem} was not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        ShoppingItem item = service.getItemById(id);

        return item != null ? ResponseEntity.ok(convertToDTO(item)) : ResponseEntity.status(404).body(new ResponseBody(404, String.format("Item not found with ID (%d)", id)));
    }

    /**
     * Search all {@link ShoppingItem}s.
     *
     * @param pageable the pagination information
     * @param query    the query to search
     * @return the list of {@link ShoppingItemDTO} entities
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/search")
    public ResponseEntity<List<ShoppingItemDTO>> searchItems(Pageable pageable, @RequestParam String query) {
        Page<ShoppingItemDTO> page = service.searchItems(pageable, query).map(this::convertToDTO);
        return ResponseEntity.ok(page.getContent());
    }

    /**
     * Create a {@link ShoppingItem}.
     *
     * @param item the {@link ShoppingItem} to create
     * @return the created {@link ShoppingItemDTO}
     */
    @PostMapping
    public ResponseEntity<ShoppingItemDTO> createItem(@RequestBody ShoppingItem item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(service.createItem(item)));
    }

    /**
     * Update a {@link ShoppingItem}.
     *
     * @param id   the id of the {@link ShoppingItem} to update
     * @param item the {@link ShoppingItem} to update
     * @return the updated {@link ShoppingItemDTO} or a 404 response if the {@link ShoppingItem} was not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ShoppingItem item) {
        Optional<ShoppingItem> updatedItem = service.updateItem(id, item);

        return updatedItem.isPresent() ? ResponseEntity.ok(convertToDTO(updatedItem.get())) : ResponseEntity.status(404).body(new ResponseBody(404, String.format("Item not found with ID (%d)", id)));
    }

    /**
     * Delete a {@link ShoppingItem}.
     *
     * @param id the id of the {@link ShoppingItem} to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
    }
}
