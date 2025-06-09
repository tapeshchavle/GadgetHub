/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.Contact;

/**
 *
 * @author hp
 */
public interface ContactDao {
    
    public boolean saveMessage(Contact contact);
    public boolean checkAlreadyExistOrNot(String email);
    public String getResponse(String email);
    public boolean updateResponse(Contact contact);
    public void deleteResponse(String email);
    
}
