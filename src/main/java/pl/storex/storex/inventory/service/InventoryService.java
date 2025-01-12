package pl.storex.storex.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.storex.storex.inventory.model.Inventory;
import pl.storex.storex.inventory.model.InventoryDto;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;


    public List<InventoryDto> findAll() {
        return inventoryRepository.findAll()
                .stream().map(Inventory::toDTO)
                .collect(Collectors.toList());
    }

    public InventoryDto findById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Inventory not found with id: " + id))
                .toDTO();
    }

    public Optional<InventoryDto> findByProductId(Long id) {
        return inventoryRepository.findByProductId(id);
    }
    
    public Optional<InventoryDto> findByLocationId(Long id) {
        return inventoryRepository.findByLocationId(id);
    }
    public Optional<InventoryDto> findByAddedById(Long id) {
        return inventoryRepository.findByAddedBy(id);
    }

    public List<InventoryDto> findByExpiredDate() {
        return inventoryRepository.findByExpirationDateBefore(Date.from(Instant.now()));
    }

    public Optional<InventoryDto> update(InventoryDto dto) {
        return null;
    }
}
