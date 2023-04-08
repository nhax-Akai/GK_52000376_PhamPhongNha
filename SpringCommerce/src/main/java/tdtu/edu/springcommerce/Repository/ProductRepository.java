package tdtu.edu.springcommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdtu.edu.springcommerce.Entity.Category;
import tdtu.edu.springcommerce.Entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

    List<Product> findByBrand(String brand);

    List<Product> findByPrice(double price);

    List<Product> findByColor(String color);

    List<Product> findByCategoryAndBrandAndColorAndPrice(Category category, String brand, String color, Double price);

    List<Product> findByCategoryAndBrandAndColor(Category category, String brand, String color);

    List<Product> findByCategoryAndBrand(Category category, String brand);

    List<Product> findByCategoryAndColor(Category category, String color);

    List<Product> findByCategoryAndPrice(Category category, Double price);

    List<Product> findByBrandAndColor(String brand, String color);

    List<Product> findByBrandAndPrice(String brand, Double price);

    List<Product> findByColorAndPrice(String color, Double price);

    Product getProductById(Long productId);

    List<Product> findByNameContaining(String keyword);

}
