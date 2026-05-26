package com.ivdev.catalogueservice.service;

import com.ivdev.catalogueservice.entity.Product;
import com.ivdev.catalogueservice.entity.ProductStatus;
import com.ivdev.catalogueservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
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
    public void deleteProduct(
            Integer id
    ){
        this.productRepository.deleteById(id);
    }
}
