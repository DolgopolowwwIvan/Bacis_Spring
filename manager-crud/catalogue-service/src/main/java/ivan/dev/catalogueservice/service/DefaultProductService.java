package ivan.dev.catalogueservice.service;

import ivan.dev.catalogueservice.entity.Product;
import ivan.dev.catalogueservice.entity.ProductStatus;
import ivan.dev.catalogueservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(
            String filter
    ){
        if(filter != null && !filter.isBlank()){
            return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
        }else {
            return this.productRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Product createProduct(
            String title,
            Integer quantity,
            String details,
            ProductStatus status
    ){
        return this.productRepository.save(new Product(
                null,
                title,
                quantity,
                details,
                status));
    }

    @Override
    public Optional<Product> findProduct(
            int productId
    ){
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public void updateProduct(
            Integer id,
            String title,
            Integer quantity,
            String details,
            ProductStatus status
    ){
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setQuantity(quantity);
                    product.setDetails(details);
                    product.setStatus(status);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(
            Integer id
    ){
        this.productRepository.deleteById(id);
    }
}
