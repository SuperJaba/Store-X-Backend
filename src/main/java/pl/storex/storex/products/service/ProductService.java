package pl.storex.storex.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.storex.storex.products.model.Product;
import pl.storex.storex.products.model.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

//    private static final Logger logger = LoggerFactory.getLogger("Slf4j");

    private final ProductRepository productRepository;


    public List<Product> getAll() {
        List<Product> all;
//        logger.info(Marker.ANY_MARKER, "GET ALL PRODUCTS", all = productRepository.findAll());
        all = productRepository.findAll();
        return all;
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

    public Product findByBarcode(String barcode) {
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
        if (optionalProduct.isEmpty()) {
            return new Product().toDTOFromModel(productRepository.save(Product.fromDTOtoModel(productDto)));
        }
        optionalProduct.stream().map(product -> {
            product.setBarcode(productDto.getBarcode());
            product.setName(productDto.getName());
            product.setCategoryId(productDto.getCategoryId());
            return new Product().toDTOFromModel(productRepository.save(product));
        });
        return null;
    }


    public List<ProductDto> findByCategoryId(ProductDto dto) {
        Optional<List<Product>> byCategoryId = productRepository.getAllByCategoryId(dto.getCategoryId());
        if (byCategoryId.isPresent()) {
            List<ProductDto> productDtoArrayList = new ArrayList<>();
            for (Product product : byCategoryId.get()) {
                productDtoArrayList.add(new Product().toDTOFromModel(product));
            }
            return productDtoArrayList;
        }
        return null;
    }
}
