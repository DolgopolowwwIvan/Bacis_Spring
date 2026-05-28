package ivan.dev.catalogueservice.repository;

import ivan.dev.catalogueservice.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Integer> {

     @Query(value = "select p from Product p where p.title ilike :filter")
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
