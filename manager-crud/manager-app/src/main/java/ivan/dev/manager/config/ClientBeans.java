package ivan.dev.manager.config;

import ivan.dev.manager.client.impl.ProductsRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public ProductsRestClientImpl productsRestClient(
            @Value("${ivdev.services.catalogue.uri}") String catalogueBaseUrl
    ){
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUrl)
                .build());
    }
}
