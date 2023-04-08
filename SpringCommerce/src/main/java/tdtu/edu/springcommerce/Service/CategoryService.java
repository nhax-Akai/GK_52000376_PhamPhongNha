package tdtu.edu.springcommerce.Service;

import java.util.List;

import tdtu.edu.springcommerce.Entity.Category;

public interface CategoryService {
    Category findByName(String name);

    List<Category> getAllCategories();
}
