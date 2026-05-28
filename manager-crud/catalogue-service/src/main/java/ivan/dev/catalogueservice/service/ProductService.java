package ivan.dev.catalogueservice.service;

import ivan.dev.catalogueservice.entity.Product;
import ivan.dev.catalogueservice.entity.ProductStatus;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAllProducts(String filter);

    Product createProduct(String title, Integer quantity, String details, ProductStatus status);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, Integer quantity, String details, ProductStatus status);

    void deleteProduct(Integer id);
}
