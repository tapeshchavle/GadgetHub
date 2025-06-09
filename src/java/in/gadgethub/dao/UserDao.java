/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.UserPojo;

/**
 *
 * @author hp
 */
public interface UserDao {
    
    String registerUser(UserPojo user);
    
    boolean isRegistered(String emailId);
    
    String isValidCredentials(String emailId,String password); //login me check hoga
    
    UserPojo getUserDetails(String emailId);
    
    String getUserFirstName(String emailId);
    
    String getUserAddr(String emailId);
    
    
    
}
