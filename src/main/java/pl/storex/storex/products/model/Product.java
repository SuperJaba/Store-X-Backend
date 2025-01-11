package pl.storex.storex.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long category_id;
    private String barcode;

    public static Product fromDTOtoModel(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .category_id(productDto.getCategory_id())
                .barcode(productDto.getBarcode())
                .build();
        if(productDto.getId() != null) product.setId(productDto.getId());
        return product;
    }

    public ProductDto toDTOFromModel(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .category_id(product.getCategory_id())
                .barcode(product.getBarcode())
                .name(product.getName())
                .build();
    }

}
