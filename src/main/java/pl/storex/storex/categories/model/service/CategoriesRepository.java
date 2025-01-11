package pl.storex.storex.categories.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.categories.model.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

}
