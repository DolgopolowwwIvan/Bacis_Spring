package ivan.dev.manager.config;

import ivan.dev.manager.client.impl.ProductsRestClientImpl;
import ivan.dev.manager.security.OAuthClientRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public ProductsRestClientImpl productsRestClient(
            @Value("${ivdev.services.catalogue.uri}") String catalogueBaseUrl,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${ivdev.services.catalogue.registration-id:keycloak}") String registrationId
    ){
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUrl)
                .requestInterceptor(
                        new OAuthClientRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository), registrationId))
                .build());
    }
}
