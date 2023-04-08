package tdtu.edu.springcommerce.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tdtu.edu.springcommerce.Entity.Category;
import tdtu.edu.springcommerce.Entity.Product;

public interface ProductService {
        List<Product> getAllProducts();

        Product getProductById(Long id);

        List<Product> getProductsByCategory(Category category);

        List<Product> getProductsByBrand(String brand);

        List<Product> getProductsByPrice(double price);

        List<Product> getProductsByColor(String color);

        List<Product> getProductsByCategoryAndBrandAndColorAndPrice(Category category, String brand, String color,
                        Double price);

        List<Product> getProductsByCategoryAndBrandAndColor(Category category, String brand, String color);

        List<Product> getProductsByCategoryAndBrand(Category category, String brand);

        List<Product> getProductsByCategoryAndColor(Category byName, String color);

        List<Product> getProductsByCategoryAndPrice(Category byName, Double price);

        List<Product> getProductsByBrandAndColor(String brand, String color);

        List<Product> getProductsByBrandAndPrice(String brand, Double price);

        List<Product> getProductsByColorAndPrice(String color, Double price);

        Product save(Product product);

        void saveProduct2DB(String name, double price, String brand, String color, Category category,
                        MultipartFile file);

        List<Product> findByNameContaining(String keyword);

}
