package net.sparkzz.workwiz.repository;

import net.sparkzz.workwiz.model.ShoppingItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link ShoppingItem} entities.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

    /**
     * Find all {@link ShoppingItem} entities.
     *
     * @param pageable the pagination information
     * @param query    the query to search
     * @return a page of {@link ShoppingItem} entities
     */
    @Query("SELECT i FROM ShoppingItem i WHERE i.name LIKE %?1%")
    Page<ShoppingItem> findByNameContaining(Pageable pageable, String query);
}
