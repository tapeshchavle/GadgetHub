/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author hp
 */
public class IDUtil {
    public static String generateProdId(){
        //but same second can generate for multiple items so we can use UUID library
        //System.currentTimeMillis()
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String prodId="P"+sdf.format(d);
        return prodId;
    }
    public static String generateTransId(){
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String transId="T"+sdf.format(d);
        return transId;
    }
    
}
