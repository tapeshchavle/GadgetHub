/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.pojo;

/**
 *
 * @author hp
 */
public class UserPojo {
    private String useremail;
    private String username;
    private String mobile;

    
    private String address;
    private int pinCode;
    private String password;

    public UserPojo() {
    }

    public UserPojo(String useremail, String username, String mobile, String address, int pinCode, String password) {
        this.useremail = useremail;
        this.username = username;
        this.mobile = mobile;
        this.address = address;
        this.pinCode = pinCode;
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPojo{" + "useremail=" + useremail + ", username=" + username + ", mobile=" + mobile + ", address=" + address + ", pinCode=" + pinCode + ", password=" + password + '}';
    }
    
    
}
