package com.irvings.controller;

import com.irvings.order.Cart;
import com.irvings.order.CartItem;
import com.irvings.order.ItemUnavailableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    // 创建新购物车
    @PostMapping("/create")
    public String createCart() {
        Cart cart = new Cart();
        return cart.getCartId();
    }

    // 加购商品
    @PostMapping("/{cartId}/item")
    public String addItem(@PathVariable String cartId, @RequestBody Map<String, Object> request) throws ItemUnavailableException {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);

        String itemId = (String) request.get("itemId");
        Map<String, String> customization = (Map<String, String>) request.get("customization");
        int quantity = (Integer) request.get("quantity");
        return cart.addItem(itemId, customization, quantity);
    }

    // 修改商品数量
    @PutMapping("/{cartId}/item/{cartItemId}")
    public void updateQuantity(@PathVariable String cartId, @PathVariable String cartItemId, @RequestParam int quantity) {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);
        cart.updateQuantity(cartItemId, quantity);
    }

    // 删除购物车商品
    @DeleteMapping("/{cartId}/item/{cartItemId}")
    public void removeItem(@PathVariable String cartId, @PathVariable String cartItemId) {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);
        cart.removeItem(cartItemId);
    }

    // 获取购物车商品列表
    @GetMapping("/{cartId}/items")
    public List<CartItem> getItems(@PathVariable String cartId) {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);
        return cart.getItems();
    }

    // 计算购物车总价
    @GetMapping("/{cartId}/total")
    public double calculateTotal(@PathVariable String cartId) {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);
        return cart.calculateTotal();
    }

    // 清空购物车
    @PostMapping("/{cartId}/clear")
    public void clearCart(@PathVariable String cartId) {
        Cart cart = Cart.getCartById(cartId);
        if (cart == null) throw new IllegalArgumentException("购物车不存在：" + cartId);
        cart.clear();
    }
}