package ivan.dev.manager.controller;

import ivan.dev.manager.entity.Product;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import ivan.dev.manager.entity.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 54321)
class ProductControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProduct_ProductExists_ReturnsProductPage() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1")
                .with(user("johndoe").roles("MANAGER"));

        WireMock.stubFor(WireMock.get("/catalogue-api/products/1")
                .willReturn(WireMock.okJson("""
                        {
                            "id": 1,
                            "title": "Товар",
                            "quantity": 2,
                            "details": "Описание товара",
                            "status": "ACTIVE"
                        }
                        """)));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        view().name("catalogue/products/product"),
                        model().attribute("product", new Product(1, "Товар", 2, "Описание товара", ProductStatus.ACTIVE))
                );
    }

    @Test
    void getProduct_ProductDoesNotExist_ReturnsError404Page() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1")
                .with(user("johndoe").roles("MANAGER"));

        WireMock.stubFor(WireMock.get("/catalogue-api/products/1")
                .willReturn(WireMock.notFound()));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isNotFound(),
                        view().name("errors/404"),
                        model().attribute("error", "Товар не найден")
                );
    }

    @Test
    void getProduct_UserIsNotAuthorized_ReturnsForbidden() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1")
                .with(user("johndoe"));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isForbidden()
                );
    }

    @Test
    void getProductEditPage_ProductExists_ReturnsProductEditPage() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1/edit")
                .with(user("johndoe").roles("MANAGER"));

        WireMock.stubFor(WireMock.get("/catalogue-api/products/1")
                .willReturn(WireMock.okJson("""
                        {
                            "id": 1,
                            "title": "Товар",
                            "quantity": 2,
                            "details": "Описание товара",
                            "status": "ACTIVE"
                        }
                        """)));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        view().name("catalogue/products/edit"),
                        model().attribute("product", new Product(1, "Товар", 2, "Описание товара", ProductStatus.ACTIVE))
                );
    }

    @Test
    void getProductEditPage_ProductDoesNotExist_ReturnsError404Page() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1/edit")
                .with(user("johndoe").roles("MANAGER"));

        WireMock.stubFor(WireMock.get("/catalogue-api/products/1")
                .willReturn(WireMock.notFound()));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isNotFound(),
                        view().name("errors/404"),
                        model().attribute("error", "Товар не найден")
                );
    }

    @Test
    void getProductEditPage_UserIsNotAuthorized_ReturnsForbidden() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/catalogue/products/1/edit")
                .with(user("johndoe"));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isForbidden()
                );
    }

}
