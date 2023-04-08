package tdtu.edu.springcommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.edu.springcommerce.Entity.CartItem;
import tdtu.edu.springcommerce.Entity.Product;
import tdtu.edu.springcommerce.Repository.CartItemRepository;

@Service
@Transactional
public class CartItemServiceImpl {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void updateQuantity(Long id, int quantity) {
        CartItem cartItem = findById(id);
        cartItem.setQuantity(quantity);
        save(cartItem);
    }

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    public boolean isExistInCart(Product product) {
        List<CartItem> cartItems = findAll();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == null ? product.getId() == null
                    : cartItem.getProduct().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    public CartItem findByProduct(Product product) {
        List<CartItem> cartItems = findAll();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == product.getId()) {
                return cartItem;
            }
        }
        return null;
    }

    public void deleteAll() {
        cartItemRepository.deleteAll();
    }

}
