package pl.storex.storex.products.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
