package ivan.dev.manager.entity;

public record Product(
        int id,
        String title,
        int quantity,
        String details,
        ProductStatus status) {
}
