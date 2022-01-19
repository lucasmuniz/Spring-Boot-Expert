package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<Product, Integer> {
}
