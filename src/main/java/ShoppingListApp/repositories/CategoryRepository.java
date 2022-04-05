package ShoppingListApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.data.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    CategoryEntity findByName(CategoryName name);
}
