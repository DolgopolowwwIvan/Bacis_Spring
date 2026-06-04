# Manager CRUD
Manager CRUD - учебный проект на Spring Boot для управления каталогом товаров.

Проект разделен на два Maven-модуля:

- `manager-app` - веб-приложение со страницами Thymeleaf для менеджеров каталога.
- `catalogue-service` - REST API, который хранит товары в PostgreSQL.

Сервисы используют OAuth2/OpenID Connect через Keycloak.
1. `manager-app` выполняет вход пользователей
и обращается к `catalogue-service`.
2. `catalogue-service` проверяет JWT-токены и предоставляет
операции с каталогом.

## Стек

- Java 21
- Spring Boot
- Spring MVC, Thymeleaf
- Spring Security OAuth2
- Spring Data JPA
- PostgreSQL
- Keycloak
- JUnit, Mockito, MockMvc, WireMock
