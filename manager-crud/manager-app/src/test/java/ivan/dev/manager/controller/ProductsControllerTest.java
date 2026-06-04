package ivan.dev.manager.controller;

import ivan.dev.manager.client.BadRequestException;
import ivan.dev.manager.client.ProductsRestClient;
import ivan.dev.manager.controller.payload.NewProductPayload;
import ivan.dev.manager.entity.Product;
import ivan.dev.manager.entity.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты для ProductsControllerTest")
class ProductsControllerTest {

    @Mock
    ProductsRestClient productsRestClient;

    @InjectMocks
    ProductsController productsController;

    @Test
    void createProduct_RequestIsValid_ReturnsRedirectionToProductPage(){
        // given
        var payload = new NewProductPayload("title", 2, "details", ProductStatus.ACTIVE);
        var model = new ConcurrentModel();

        doReturn(new Product(1, "title", 2, "details", ProductStatus.ACTIVE))
                .when(this.productsRestClient)
                .createProduct("title", 2, "details", ProductStatus.ACTIVE);
        // when
        var result = this.productsController.createProduct(payload, model);
        //then
        assertEquals("redirect:/catalogue/products/1", result);

        verify(this.productsRestClient).createProduct("title", 2, "details", ProductStatus.ACTIVE);
        verifyNoMoreInteractions(this.productsRestClient);
    }

    @Test
    void createProduct_RequestIsInvalid_ReturnsErrorPage(){
        // given
        var payload = new NewProductPayload("   ", -2, "null", ProductStatus.ACTIVE);
        var model = new ConcurrentModel();

        doThrow(new BadRequestException(List.of("Error 1", "Error 2")))
                .when(this.productsRestClient)
                .createProduct("   ", -2, "null", ProductStatus.ACTIVE);
        // when
        var result = this.productsController.createProduct(payload, model);
        //then
        assertEquals("catalogue/products/new_product", result);
        assertEquals(payload, model.getAttribute("payload"));
        assertEquals(List.of("Error 1", "Error 2"), model.getAttribute("errors"));

        verify(this.productsRestClient).createProduct("   ", -2, "null", ProductStatus.ACTIVE);
        verifyNoMoreInteractions(this.productsRestClient);
    }

}
