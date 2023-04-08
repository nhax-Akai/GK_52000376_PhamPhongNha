package tdtu.edu.springcommerce.Service;

import java.util.List;

import tdtu.edu.springcommerce.Entity.CartItem;

public interface CartItemService {

    CartItem findById(Long id);

    CartItem save(CartItem cartItem);

    void deleteById(Long id);

    void updateQuantity(Long id, int quantity);

    List<CartItem> findAll();
}
