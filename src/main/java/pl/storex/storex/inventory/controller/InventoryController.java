package pl.storex.storex.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.inventory.model.Inventory;
import pl.storex.storex.inventory.model.InventoryDto;
import pl.storex.storex.inventory.service.InventoryService;

@RestController("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/getAll")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventoryService.findById(id));
    }

    @GetMapping("/getByProduct/{id}")
    ResponseEntity<?> findByProductId(@PathVariable("id") Long id) {
        return ResponseEntity.ofNullable(inventoryService.findByProductId(id));
    }

    @GetMapping("/getByLocation/{id}")
    ResponseEntity<?> findByLocationId(@PathVariable("id") Long id) {
        return ResponseEntity.ofNullable(inventoryService.findByLocationId(id));
    }

    @GetMapping("/getByAddedBy/{id}")
    ResponseEntity<?> findByAddedById(@PathVariable("id") Long id) {
        return ResponseEntity.ofNullable(inventoryService.findByAddedById(id));
    }

    @GetMapping("/getExpiredNow")
    ResponseEntity<?> findByExpiredDate() {
        return ResponseEntity.ofNullable(inventoryService.findByExpiredDate());
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@RequestBody InventoryDto dto) {
        return ResponseEntity.ofNullable(inventoryService.update(dto));
    }


}
