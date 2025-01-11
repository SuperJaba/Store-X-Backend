package pl.storex.storex.products.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.storex.storex.products.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByBarcode(String barcode);

    @Query("select p from Product p where p.categoryId = :categoryId")
    Optional<List<Product>> getAllByCategoryId(@Param("categoryId") Long categoryId);
}
