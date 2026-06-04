package ivan.dev.manager.config;

import ivan.dev.manager.client.impl.ProductsRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;


import static org.mockito.Mockito.mock;

@Configuration
public class TestingBeans {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return mock(ClientRegistrationRepository.class);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
        return mock(OAuth2AuthorizedClientRepository.class);
    }

    @Bean
    @Primary
    public ProductsRestClientImpl testProductsRestClient(
            @Value("${ivdev.services.catalogue.uri:http://localhost:54321}") String catalogueBaseUrl
    ){
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUrl)
                .requestFactory(new JdkClientHttpRequestFactory())
                .build());
    }
}
