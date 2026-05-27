package ivan.dev.manager.controller.payload;

import ivan.dev.manager.entity.ProductStatus;

public record NewProductPayload(
        String title,
        Integer quantity,
        String details,
        ProductStatus status) {
}
