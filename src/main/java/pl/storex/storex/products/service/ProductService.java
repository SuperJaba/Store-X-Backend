package pl.storex.storex.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.storex.storex.products.model.Product;
import pl.storex.storex.products.model.ProductDto;

import java.util.List;
import java.util.Optional;

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
        return new Product()
                .toDTOFromModel(productRepository
                        .save(Product.fromDTOtoModel(productDto)));
    }

    public void removeProduct(ProductDto productDto) {
        productRepository.deleteById(productDto.getId());
    }

    private Product findByBarcode(String barcode) {
        if (barcode != null) {
            Optional<Product> byBarcode = productRepository.findByBarcode(barcode);
            if (byBarcode.isPresent()) {
                return byBarcode.get();
            }
        }
        return null;
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = Optional.empty();
        if (productDto.getId() != null) {
            optionalProduct = productRepository.findById(productDto.getId());
        }
        if (productDto.getBarcode() != null) {
            optionalProduct = Optional.ofNullable(findByBarcode(productDto.getBarcode()));
        }
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setCategory_id(productDto.getCategory_id());
            product.setBarcode(productDto.getBarcode());
            return new Product().toDTOFromModel(productRepository.save(product));
        }
        return new Product().toDTOFromModel(productRepository.save(Product.fromDTOtoModel(productDto)));
    }


}
