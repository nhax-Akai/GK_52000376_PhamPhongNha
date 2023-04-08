package tdtu.edu.springcommerce.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tdtu.edu.springcommerce.Entity.CartItem;
import tdtu.edu.springcommerce.Entity.Product;
import tdtu.edu.springcommerce.Service.CartItemServiceImpl;
import tdtu.edu.springcommerce.Service.CategoryServiceImpl;
import tdtu.edu.springcommerce.Service.ProductServiceImpl;

@Controller
public class HomeController {

    @Autowired
    public ProductServiceImpl productService;
    @Autowired
    public CategoryServiceImpl categoryService;

    @Autowired
    public CartItemServiceImpl cartItemService;

    @Autowired
    public HomeController(ProductServiceImpl productService, CategoryServiceImpl categoryService,
            CartItemServiceImpl cartItemService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartItemService = cartItemService;
    }

    @RequestMapping("/home")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home";
    }

    // get product by id
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/home";
        } else {
            model.addAttribute("message", "Username or password is incorrect");
            return "index";
        }
    }

    // delete product by id
    @RequestMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/home";
    }

    // filter
    @RequestMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Double price,
            Model model) {
        List<Product> products = productService.getAllProducts();
        if (!Objects.equals(category, "") && !Objects.equals(brand, "") && !Objects.equals(color, "") && price != 0) {
            products = productService.getProductsByCategoryAndBrandAndColorAndPrice(
                    categoryService.findByName(category), brand, color, price);
        } else if (!Objects.equals(category, "") && !Objects.equals(brand, "") && !Objects.equals(color, "")) {
            products = productService.getProductsByCategoryAndBrandAndColor(categoryService.findByName(category), brand,
                    color);
        } else if (!Objects.equals(category, "") && !Objects.equals(brand, "")) {
            products = productService.getProductsByCategoryAndBrand(categoryService.findByName(category), brand);
        } else if (!Objects.equals(category, "") && !Objects.equals(color, "")) {
            products = productService.getProductsByCategoryAndColor(categoryService.findByName(category), color);
        } else if (!Objects.equals(category, "") && price != 0) {
            products = productService.getProductsByCategoryAndPrice(categoryService.findByName(category), price);
        } else if (!Objects.equals(brand, "") && !Objects.equals(color, "")) {
            products = productService.getProductsByBrandAndColor(brand, color);
        } else if (!Objects.equals(brand, "") && price != 0) {
            products = productService.getProductsByBrandAndPrice(brand, price);
        } else if (!Objects.equals(color, "") && price != 0) {
            products = productService.getProductsByColorAndPrice(color, price);
        } else if (!Objects.equals(category, "")) {
            products = productService.getProductsByCategory(categoryService.findByName(category));
        } else if (!Objects.equals(brand, "")) {
            products = productService.getProductsByBrand(brand);
        } else if (!Objects.equals(color, "")) {
            products = productService.getProductsByColor(color);
        } else if (price != 0) {
            products = productService.getProductsByPrice(price);
        }

        model.addAttribute("products", products);
        return "home";
    }

    // thêm sản phẩm vào cartItem
    @RequestMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long product_id) {
        Product product = productService.getProductById(product_id);
        // using isExistInCart
        if (cartItemService.isExistInCart(product)) {
            CartItem cartItem = cartItemService.findByProduct(product);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            cartItemService.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(product.getPrice());
            cartItemService.save(cartItem);
        }
        return "redirect:/cart";

    }

    // show all products in cart and total price
    @RequestMapping("/cart")
    public String getAllCartItems(Model model) {
        List<CartItem> cartItems = cartItemService.findAll();
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    // delete cartItem
    @RequestMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable("id") Long cartItem_id) {
        cartItemService.deleteById(cartItem_id);
        return "redirect:/cart";
    }

    // update cartItem
    @PostMapping("/update/{id}")
    public String updateCartItem(@PathVariable("id") Long cartItemId, @RequestParam("quantity") int quantity) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
        cartItemService.save(cartItem);
        return "redirect:/cart";
    }

    // show checkout page
    @RequestMapping("/checkout")
    public String checkout(Model model) {
        List<CartItem> cartItems = cartItemService.findAll();
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "checkout";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String brand,
            @RequestParam String color, @RequestParam String category,
            @RequestParam("image") MultipartFile multipartFile)
            throws IOException {

        if (multipartFile.getSize() > 10485760) { // 10MB in bytes
            System.out.println("kich thuoc vuot muc cho phep");
        }

        productService.saveProduct2DB(name, price, brand, color, categoryService.findByName(category), multipartFile);
        return "redirect:/home";
    }

    @RequestMapping("/addproduct")
    public String addProduct(Model model) {
        return "addproduct";
    }

    // search by keyword
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long product_id, Model model) {
        Product product = productService.getProductById(product_id);
        model.addAttribute("product", product);
        return "details";
    }

    // edit product form
    @RequestMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long product_id, Model model) {
        Product product = productService.getProductById(product_id);
        model.addAttribute("product", product);
        return "editproduct";
    }

    // update name, color, brand, price, category
    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long product_id, @RequestParam String name,
            @RequestParam double price,
            @RequestParam String brand, @RequestParam String color, @RequestParam String category) {
        Product product = productService.getProductById(product_id);
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setColor(color);
        product.setCategory(categoryService.findByName(category));
        productService.save(product);
        return "redirect:/home";
    }

}
