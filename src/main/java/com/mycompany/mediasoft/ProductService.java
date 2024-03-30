package com.mycompany.mediasoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с продуктами.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Конструктор сервиса продуктов.
     * @param productRepository Репозиторий продуктов.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получить все продукты.
     * @return Список всех продуктов.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Получить продукт по его ID.
     * @param id ID продукта.
     * @return Найденный продукт или null, если продукт не найден.
     */
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Сохранить или обновить информацию о продукте.
     * @param product Объект продукта для сохранения или обновления.
     * @return Сохраненный или обновленный продукт.
     */
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Удалить продукт по его идентификатору.
     * @param id Идентификатор продукта.
     */
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    /**
     * Проверить существование продукта по его идентификатору.
     * @param id Идентификатор продукта.
     * @return true, если продукт существует, иначе false.
     */
    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }
    public Product findProductByArticle(String article) {
         return productRepository.findByArticle(article).orElse(null);
    }
    public boolean existsByArticle(String article) {
        return productRepository.existsByArticle(article);
    }
}
