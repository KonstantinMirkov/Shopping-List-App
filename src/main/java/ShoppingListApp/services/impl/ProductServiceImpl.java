package ShoppingListApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ShoppingListApp.data.entities.ProductEntity;
import ShoppingListApp.data.entities.enums.CategoryName;
import ShoppingListApp.data.services.ProductServiceModel;
import ShoppingListApp.data.views.ProductViewModel;
import ShoppingListApp.repositories.ProductRepository;
import ShoppingListApp.services.CategoryService;
import ShoppingListApp.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity productEntity = modelMapper.map(productServiceModel, ProductEntity.class);

        productEntity.setCategory(categoryService.getCategoryByName(productServiceModel.getCategory()));

        productRepository.save(productEntity);
    }

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal value = productRepository.findTotalPriceOfAllProducts();
        return value != null ? value : BigDecimal.ZERO;
    }

    @Override
    public List<ProductViewModel> getProductsByCategory(CategoryName categoryName) {
        return productRepository.findAllByCategoryName(categoryName).stream()
                .map(p -> modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
