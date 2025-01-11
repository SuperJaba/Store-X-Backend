package pl.storex.storex.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.storex.storex.products.model.Product;
import pl.storex.storex.products.model.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductDto save(ProductDto productDto) {
        Product save = productRepository.save(Product.fromDTO(productDto));
        return new Product().toDTO();
    }
}
