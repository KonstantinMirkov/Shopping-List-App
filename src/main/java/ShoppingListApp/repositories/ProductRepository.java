package ShoppingListApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.data.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal findTotalPriceOfAllProducts();

    List<ProductEntity> findAllByCategoryName(CategoryName categoryName);
}
