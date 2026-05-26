package ivan.dev.manager.service;

import ivan.dev.manager.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(String title, Integer quantity, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, Integer quantity, String details);

    void deleteProduct(Integer id);
}
