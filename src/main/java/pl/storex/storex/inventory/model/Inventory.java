package pl.storex.storex.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "location_id")
    private Long locationId;
    @Column(name = "added_at")
    private Date addedAt;
    @Column(name = "added_by")
    private Long addedBy;
    @Column(name = "expiration_date")
    private Date expirationDate;

    public InventoryDto toDTO() {
        return InventoryDto.builder()
                .id(this.id)
                .productId(this.productId)
                .locationId(this.locationId)
                .addedBy(this.addedBy)
                .addedBy(this.addedBy)
                .expirationDate(this.expirationDate)
                .build();
    }
    public static Inventory toEntity(InventoryDto inventoryDto) {
        return Inventory.builder()
                .id(inventoryDto.getId())
                .productId(inventoryDto.getProductId())
                .locationId(inventoryDto.getLocationId())
                .addedBy(inventoryDto.getAddedBy())
                .addedAt(inventoryDto.getAddedAt())
                .expirationDate(inventoryDto.getExpirationDate())
                .build();
    }

}
