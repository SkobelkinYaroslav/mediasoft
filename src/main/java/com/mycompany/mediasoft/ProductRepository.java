package com.mycompany.mediasoft;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс хранилища для работы с объектами Product в базе данных.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Проверяет наличие товара в базе данных по его ID.
     * @param id ID товара.
     * @return true, если товар существует, иначе false.
     */
    boolean existsById(UUID id);

    /**
     * Удаляет товар из базы данных по его ID.
     * @param id ID товара.
     */
    void deleteById(UUID id);
    Optional<Product> findByArticle(String article);
    boolean existsByArticle(String article);
}