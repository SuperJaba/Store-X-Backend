package pl.storex.storex.products.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private Long category_id;
    private String barcode;

}
