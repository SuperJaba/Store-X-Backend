package pl.storex.storex.products;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.storex.storex.products.model.Product;
import pl.storex.storex.products.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController("/products")
@RequiredArgsConstructor
@Tag(name = "Store-X Product Controller")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.of(Optional.ofNullable(productService.getAll()));
    }

}
