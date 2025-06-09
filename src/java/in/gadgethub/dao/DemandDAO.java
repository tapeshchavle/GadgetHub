/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.DemandPojo;
import java.util.List;

/**
 *
 * @author hp
 */
public interface DemandDAO {
    public boolean addProduct(DemandPojo demandPojo);
    public boolean removeProduct(String userId,String prodId);
    public List<DemandPojo> haveDemanded(String prodid);
    
}
