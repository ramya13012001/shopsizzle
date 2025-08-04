package com.Shop.Sizzle.repository;

import com.Shop.Sizzle.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findBycategoryName(String categoryName);
}
