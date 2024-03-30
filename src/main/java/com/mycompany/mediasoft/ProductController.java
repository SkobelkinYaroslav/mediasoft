package com.mycompany.mediasoft;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Контроллер для обработки запросов, связанных с продуктами.
 */
@RestController
@RequestMapping("/products")
@Api(tags = "Product Controller")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Получает все продукты и возвращает их в виде списка.
     * Если продукты отсутствуют, возвращает статус 404 (Not Found).
     *
     * @return ResponseEntity со списком продуктов или статусом 404.
     */
    @ApiOperation(value = "Получить все продукты")
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Получает продукт по его ID и возвращает его в виде строки.
     * Если продукт не найден, возвращает статус 404 (Not Found).
     *
     * @param id ID продукта.
     * @param model Модель для передачи данных на страницу.
     * @return ResponseEntity с продуктом в виде строки или статусом 404.
     */
    @ApiOperation(value = "Получить продукт по ID")
    @GetMapping("/id")
    public ResponseEntity<Product> getProductById(@RequestParam UUID id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Сохраняет или обновляет информацию о продукте.
     * Возвращает статус 200 (OK) после успешного сохранения или обновления.
     *
     * @param product Объект продукта.
     * @param result Результаты проверки данных.
     * @return ResponseEntity со статусом 200.
     */
    @ApiOperation(value = "Сохранить или обновить информацию о продукте")
    @PostMapping("/")
    public ResponseEntity<Void> saveProduct(@Valid @ModelAttribute Product product, BindingResult result) {
        if(productService.existsByArticle(product.getArticle())){
            if(product.getQuantity()!=productService.findProductByArticle(product.getArticle()).getQuantity()){
                product.setLastQuantityChangeTimestamp(LocalDateTime.now());
                product.setCreationTimestamp(productService.findProductByArticle(product.getArticle()).getCreationTimestamp());
            }else{
                product.setCreationTimestamp(productService.findProductByArticle(product.getArticle()).getCreationTimestamp());
                product.setLastQuantityChangeTimestamp(productService.findProductByArticle(product.getArticle()).getLastQuantityChangeTimestamp());
            }
        }else{
            product.setCreationTimestamp(LocalDateTime.now());
            product.setLastQuantityChangeTimestamp(LocalDateTime.now());
        }
        productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Удаляет продукт по его ID.
     * Если продукт успешно удален, возвращает статус 200 (OK).
     * Если продукт не найден, возвращает статус 404 (Not Found).
     *
     * @param id ID продукта.
     * @return ResponseEntity со статусом 200 или 404.
     */
    @ApiOperation(value = "Удалить продукт по ID")
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduct(@RequestParam UUID id) {
        if (productService.existsById(id)) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
