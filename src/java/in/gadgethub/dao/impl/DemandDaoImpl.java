/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.DemandDAO;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.utility.DBUtil;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class DemandDaoImpl implements DemandDAO {

    @Override
    public boolean addProduct(DemandPojo demandPojo) {
        boolean status = false;
        Connection conn = DBUtil.getConnection();
        String update = "update userdemand set quentity=quentity+? where useremail=? and prodId=?";
        String insert = "insert into userdemand values(?,?,?)";
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            ps1 = conn.prepareStatement(update);
            ps1.setInt(1, demandPojo.getDemandQuantity());
            ps1.setString(2, demandPojo.getUseremail());
            ps1.setString(3, demandPojo.getProdId());
            int k = ps1.executeUpdate();
            if (k == 0) {
                ps2 = conn.prepareStatement(insert);
                ps2.setString(1, demandPojo.getUseremail());
                ps2.setString(2, demandPojo.getProdId());
                ps2.setInt(3, demandPojo.getDemandQuantity());
                ps2.executeUpdate();

            }
            status = true;

        } catch (SQLException ex) {

            System.out.println("Error is addProduct method" + ex.getMessage());
        }
        return status;

    }

    @Override
    public boolean removeProduct(String userId, String prodId){
        boolean flag=false;
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("delete from userdemand where userId=? and prodId=?");
            ps.setString(1,userId);
            ps.setString(2,prodId);
            int x=ps.executeUpdate();
            if(x>0)
                 flag=true;
        }catch(SQLException ex){
            System.out.println("Error in removeProduct method"+ex);
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<DemandPojo> haveDemanded(String prodid) {
        List<DemandPojo> list=new ArrayList<>();
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select * from userdemand where prodId=?");
            ps.setString(1,prodid);
            rs=ps.executeQuery();
            while(rs.next()){
                DemandPojo demandPojo=new DemandPojo();
                demandPojo.setUseremail(rs.getString("useremail"));
                demandPojo.setProdId(rs.getString("prodid"));
                demandPojo.setDemandQuantity(rs.getInt("quentity"));
                list.add(demandPojo);
            }
        }catch(Exception ex){
            System.out.println("Error in haveDemanded method"+ex.getMessage());
        }
        return list;
        
    }

}
