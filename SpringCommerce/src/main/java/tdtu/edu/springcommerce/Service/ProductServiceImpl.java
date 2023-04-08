package tdtu.edu.springcommerce.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tdtu.edu.springcommerce.Entity.Category;
import tdtu.edu.springcommerce.Entity.Product;
import tdtu.edu.springcommerce.Repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByPrice(double price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColor(color);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandAndColorAndPrice(Category category, String brand, String color,
            Double price) {
        return productRepository.findByCategoryAndBrandAndColorAndPrice(category, brand, color, price);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandAndColor(Category category, String brand, String color) {
        return productRepository.findByCategoryAndBrandAndColor(category, brand, color);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(Category category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndColor(Category category, String color) {
        return productRepository.findByCategoryAndColor(category, color);
    }

    @Override
    public List<Product> getProductsByCategoryAndPrice(Category category, Double price) {
        return productRepository.findByCategoryAndPrice(category, price);
    }

    @Override
    public List<Product> getProductsByBrandAndColor(String brand, String color) {
        return productRepository.findByBrandAndColor(brand, color);
    }

    @Override
    public List<Product> getProductsByBrandAndPrice(String brand, Double price) {
        return productRepository.findByBrandAndPrice(brand, price);
    }

    @Override
    public List<Product> getProductsByColorAndPrice(String color, Double price) {
        return productRepository.findByColorAndPrice(color, price);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void saveProduct2DB(String name, double price, String brand, String color, Category category,
            MultipartFile file) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Sorry!");
        } else {
            try {
                product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setColor(color);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

}