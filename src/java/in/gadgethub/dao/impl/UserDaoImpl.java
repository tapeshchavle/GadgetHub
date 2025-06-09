package in.gadgethub.dao.impl;

import java.sql.PreparedStatement;
import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.UserPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.MailMessage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserDaoImpl implements UserDao {

    @Override
    public String registerUser(UserPojo user) {
        String status = "Registration Failed";
        /* boolean result=isRegistered(user.getUseremail());
          if(result){
              status="Email Already Registered.Try Again";                            
              return status;
          }
         */
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into users values(?,?,?,?,?,?)");
            ps.setString(1, user.getUseremail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getPinCode());
            ps.setString(6, user.getPassword());
            int count = ps.executeUpdate();
            if (count == 1) {
                status = "Registration Successfull";
                // Asynchronous email sending
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> {
                    try {
                        MailMessage.registrationSuccess(user.getUseremail(), user.getUsername());
                    } catch (Exception ex) {
                        System.out.println("Error sending email: " + ex);
                    }
                });
                executor.shutdown();
               // MailMessage.registrationSuccess(user.getUseremail(), user.getUsername());
            }

        } catch (Exception ex) {
            System.out.println("Error in registerUser:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public boolean isRegistered(String emailId) {
        //checks if email id is already registered of not if registered then it returs the true
        boolean flag = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from users where useremail=?"); //can write 1 in the place of * when it true then return 1
            ps.setString(1, emailId);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error in isRegistered:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return flag;
    }

    @Override
    public String isValidCredentials(String emailId, String password) {

        //checks emailid and password insert by user matches in database or not
        Connection conn = DBUtil.getConnection();
        ResultSet rs = null;
        String status = "Login Denied.Invalid Username or Password";
        PreparedStatement ps = null;
        try {
            System.out.println(emailId + " ," + password);
            ps = conn.prepareStatement("select * from users where useremail=? and password=?");
            ps.setString(1, emailId);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = "Login Succesfull";
            }
        } catch (SQLException ex) {
            System.out.println("hello");
            System.out.println("Error in isVAlidCredentials" + ex);
            ex.printStackTrace();
        }
        //DBUtil.closeResultSet(rs);
        //DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public UserPojo getUserDetails(String emailId) {
        //returns the all details about user with the help of emailID
        UserPojo user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = DBUtil.getConnection();
        try {
            ps = conn.prepareStatement("select * from users where useremail=?");

            ps.setString(1, emailId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserPojo();
                user.setUseremail(rs.getString("useremail"));
                user.setUsername(rs.getString("username"));
                user.setMobile(rs.getString("mobile"));
                user.setAddress(rs.getString("address"));
                user.setPinCode(rs.getInt("pincode"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException ex) {
            System.out.println("Error in getUserDetails:" + ex);
            ex.printStackTrace();

        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return user;
    }

    @Override
    public String getUserFirstName(String emailId) {
        //return the first name of user
        String name = "";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select username from users where useremail=?");
            ps.setString(1, emailId);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("username");
                String[] fullname = name.split(" ");
                name = fullname[0];
            }

        } catch (SQLException ex) {
            System.out.println("Error in getUserFirstName method:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return name;
    }

    @Override
    public String getUserAddr(String emailId) {
        //return the address of the user
        String address = "";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select address from users where useremail=?");
            ps.setString(1, emailId);
            rs = ps.executeQuery();
            address = emailId;
            if (rs.next()) {
                address = rs.getString("address");
            }

        } catch (SQLException ex) {
            System.out.println("Error in getUserAddr method:" + ex);
            ex.printStackTrace();
        }
        //D

        DBUtil.closeResultSet(rs);
        //DBUtil.closeStatement(ps);
        return address;

    }

}
