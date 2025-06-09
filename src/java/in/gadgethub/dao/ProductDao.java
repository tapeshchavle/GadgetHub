/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.ProductPojo;
import java.util.List;

/**
 *
 * @author hp
 */
public interface ProductDao {
    //add Product in DB
    public String addProduct(ProductPojo product);
    //update Product in DB
    public String updateProduct(ProductPojo prevProduct,ProductPojo udatedProduct);
    //update Product price in Db
    public String updateProductPrice(String prodId , double updatedPrice);
   //return allProduct
    public List<ProductPojo>getAllProducts();
   
    public List<ProductPojo>getAllProductsByType(String type);
  
    public List<ProductPojo>searchAllProducts(String search);
   
    public ProductPojo getProductDetails(String prodId);
    
    public int getProductQuantity(String proId);
    
    public String updateProductWithoutImage(String prevProductId,ProductPojo updatedProduct);
    
    public double getProductPrice(String prodId);
    
    public boolean sellNProduct(String prodId,int n);
    
    public List<String> getAllProductsType();
    
    public byte[] getImage(String prodId);
    
    public String removeProduct(String prodId);
    
}
