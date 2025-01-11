package pl.storex.storex.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long category_id;
    private String barcode;

    public ProductDto toDTO() {
        return ProductDto.builder()
                .name(this.name)
                .category_id(this.category_id)
                .name(this.name)
                .build();
    }

    public static Product fromDTO(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .name(productDto.getName())
                .barcode(productDto.getBarcode())
                .build();
    }

}
