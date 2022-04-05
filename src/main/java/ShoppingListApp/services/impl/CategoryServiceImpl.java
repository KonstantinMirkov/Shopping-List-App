package ShoppingListApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.repositories.CategoryRepository;
import ShoppingListApp.services.CategoryService;
import ShoppingListApp.data.entities.CategoryEntity;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        CategoryEntity category = new CategoryEntity(categoryName,
                                "A description for the " + categoryName.name());

                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public CategoryEntity getCategoryByName(CategoryName category) {
        return categoryRepository.findByName(category);
    }
}
