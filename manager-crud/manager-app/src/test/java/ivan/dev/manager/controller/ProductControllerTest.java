package ivan.dev.manager.controller;

import ivan.dev.manager.client.ProductsRestClient;
import ivan.dev.manager.entity.Product;
import ivan.dev.manager.entity.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductsRestClient productsRestClient;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    ProductController controller;

    @Test
    void product_ProductExists_ReturnsProduct() {
        // given
        var product = new Product(1, "Товар №1", 3,"Описание товара №1", ProductStatus.ACTIVE);

        doReturn(Optional.of(product)).when(this.productsRestClient).findProductById(1);

        // when
        var result = this.controller.product(1);

        // then
        assertEquals(product, result);

        verify(this.productsRestClient).findProductById(1);
        verifyNoMoreInteractions(this.productsRestClient);
    }

    @Test
    void product_ProductDoesNotExist_ThrowsNoSuchElementException() {
        // when
        var exception = assertThrows(NoSuchElementException.class, () -> this.controller.product(1));

        // then
        assertEquals("catalogue.errors.product.not_found", exception.getMessage());

        verify(this.productsRestClient).findProductById(1);
        verifyNoMoreInteractions(this.productsRestClient);
    }

    @Test
    void getProduct_ReturnsProductPage() {
        // when
        var result = this.controller.getProduct();

        // then
        assertEquals("catalogue/products/product", result);

        verifyNoInteractions(this.productsRestClient);
    }

    @Test
    void getProductEditPage_ReturnsProductEditPage() {
        // when
        var result = this.controller.getProductEditPage();

        // then
        assertEquals("catalogue/products/edit", result);

        verifyNoInteractions(this.productsRestClient);
    }


    @Test
    void deleteProduct_RedirectsToProductsListPage() {
        // given
        var product = new Product(1, "Товар №1", 1, "Описание товара №1", ProductStatus.INACTIVE);

        // when
        var result = this.controller.deleteProduct(product);

        // then
        assertEquals("redirect:/catalogue/products/list", result);

        verify(this.productsRestClient).deleteProductById(1);
        verifyNoMoreInteractions(this.productsRestClient);
    }

}
