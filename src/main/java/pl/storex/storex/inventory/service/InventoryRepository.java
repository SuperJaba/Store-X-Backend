package pl.storex.storex.inventory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.inventory.model.Inventory;
import pl.storex.storex.inventory.model.InventoryDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<InventoryDto> findByProductId(Long productId);

    Optional<InventoryDto> findByAddedBy(Long addedBy);

    Optional<InventoryDto> findByLocationId(Long locationId);

    List<InventoryDto> findByExpirationDateBefore(Date expirationDateBefore);
}
