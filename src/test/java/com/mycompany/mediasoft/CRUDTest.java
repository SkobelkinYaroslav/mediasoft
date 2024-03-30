package com.mycompany.mediasoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class CRUDTest {
    private ProductService productService;
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        // Создание списка фиктивных продуктов
        List<Product> productList = new ArrayList<>();
        productList.add(ProductTestFactory.createProduct("AAA"));
        productList.add(ProductTestFactory.createProduct("BBB"));

        // Мокирование вызова findAll() у репозитория
        when(productRepository.findAll()).thenReturn(productList);

        // Вызов метода getAllProducts()
        List<Product> retrievedProducts = productService.getAllProducts();

        // Проверка, что список продуктов не пустой и содержит ожидаемое количество элементов
        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testGetProductById() {
        // Создание фиктивного продукта
        Product product = ProductTestFactory.createProduct("AAA");

        // Мокирование вызова findById() у репозитория
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Вызов метода getProductById()
        Product retrievedProduct = productService.getProductById(product.getId());

        // Проверка, что полученный продукт не равен null
        assertNotNull(retrievedProduct);
    }

    @Test
    public void testSaveOrUpdateProduct() {
        // Создание фиктивного продукта
        Product product = ProductTestFactory.createProduct("AAA");

        // Мокирование вызова save() у репозитория
        when(productRepository.save(product)).thenReturn(product);

        // Вызов метода saveOrUpdateProduct()
        Product savedProduct = productService.saveOrUpdateProduct(product);

        // Проверка, что сохраненный продукт не равен null
        assertNotNull(savedProduct);
    }

    @Test
    public void testDeleteProduct() {
        // Вызов метода deleteProduct()
        UUID uuid = UUID.randomUUID();
        productService.deleteProduct(uuid);

        // Проверка, что метод deleteById() у репозитория был вызван один раз с аргументом 1L
        verify(productRepository, times(1)).deleteById(uuid);
}
}
