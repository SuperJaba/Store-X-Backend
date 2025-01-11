package pl.storex.storex.products.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByBarcode(String barcode);

    List<Product> findByCategory_id(Long categoryId);
}
