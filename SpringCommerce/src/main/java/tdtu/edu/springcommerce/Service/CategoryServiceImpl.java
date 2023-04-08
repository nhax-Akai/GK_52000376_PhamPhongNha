package tdtu.edu.springcommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tdtu.edu.springcommerce.Entity.Category;
import tdtu.edu.springcommerce.Repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
