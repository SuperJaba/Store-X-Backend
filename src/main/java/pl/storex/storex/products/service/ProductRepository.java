package pl.storex.storex.products.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.products.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByBarcode(String barcode);
}
