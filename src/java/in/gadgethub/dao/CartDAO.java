/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.CartPojo;
import java.util.List;

/**
 *
 * @author hp
 */
public interface CartDAO {
    public String addProductInCart(CartPojo cart);
    public String updateProductInCart(CartPojo cart);
    public List<CartPojo>getAllCartItems(String userId);
    public int getCartItemCount(String userId,String itemId);
    public String removeProductFromCart(String userId,String prodId);
    public Boolean removeAProduct(String userId,String prodId);
    
}
