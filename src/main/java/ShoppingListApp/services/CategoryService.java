package ShoppingListApp.services;

import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.data.entities.CategoryEntity;

public interface CategoryService {
    void initCategories();

    CategoryEntity getCategoryByName(CategoryName category);
}
