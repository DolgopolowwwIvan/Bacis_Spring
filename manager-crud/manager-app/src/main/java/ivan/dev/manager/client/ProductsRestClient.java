package ivan.dev.manager.client;

import ivan.dev.manager.entity.Product;
import ivan.dev.manager.entity.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Product> findAllProducts();

    Product createProduct(String title, Integer quantity, String details, ProductStatus status);

    Optional<Product> findProductById(Integer productId);

    void deleteProductById(Integer id);

    void updateProduct(Integer productId, String title, Integer quantity, String details, ProductStatus status);

}
