package ShoppingListApp.services;

import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.data.services.ProductServiceModel;
import ShoppingListApp.data.views.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    BigDecimal getTotalSum();

    List<ProductViewModel> getProductsByCategory(CategoryName categoryName);

    void buyById(String id);

    void buyAllProducts();
}
