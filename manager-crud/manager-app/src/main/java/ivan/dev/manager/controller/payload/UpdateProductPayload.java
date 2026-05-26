package ivan.dev.manager.controller.payload;

import ivan.dev.manager.entity.ProductStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull(message = "{catalogue.products.update.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.update.errors.title_size_is_invalid}")
        String title,
        @Positive()
        @NotNull(message = "{catalogue.products.update.errors.quantity_is_null}")
        Integer quantity,
        @Size(max = 1000, message = "{catalogue.products.update.errors.details_size_is_invalid}")
        String details,
        ProductStatus status) {

}
