package ivan.dev.manager.controller.payload;

import com.ivdev.catalogueservice.entity.ProductStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        @NotNull(message = "{catalogue.products.create.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.create.errors.title_size_is_invalid}")
        String title,
        @Positive(message = "{catalogue.products.create.errors.quantity_is_null}")
        @NotNull(message = "{catalogue.products.create.errors.quantity_is_null}")
        Integer quantity,
        @Size(max = 1000, message = "{catalogue.products.create.errors.details_size_is_invalid}")
        String details,
        ProductStatus status) {
}
