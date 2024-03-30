package com.mycompany.mediasoft;

import java.util.UUID;

public class ProductTestFactory {

    public static Product createProduct(String Article) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setArticle(Article);
        product.setName("Example Product"+product.getId());
        product.setDescription("This is an example product" +product.getId());
        product.setCategory("Electronics");
        product.setPrice(99.99);
        product.setQuantity(10);
        return product;
    }
}