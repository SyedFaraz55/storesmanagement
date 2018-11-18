/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Syed
 */
public class helperClass {
     
    
    java.util.Date date,date2,date3;
    java.sql.Date sqldate,sqldate2,sqldate3,sqldate4;
    
    public int sales(Date startdate, Date enddate) {
       int totalSales = 0;
        sqldate  = startdate;
        sqldate2=enddate;
         
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysql = "SELECT * FROM `cust_info` WHERE `dateoforder` BETWEEN '"+sqldate+"' AND '"+sqldate2+"'";
         rs =st.executeQuery(mysql);
         while(rs.next()) {
              totalSales += rs.getInt("sellingprice");    
         }
         
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return totalSales;
        
    }
    
    public int retail(Date startdate, Date enddate) {
       int retail = 0;
        sqldate  = startdate;
        sqldate2=enddate;
         
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysql = "SELECT * FROM `cust_info` WHERE `dateoforder` BETWEEN '"+sqldate+"' AND '"+sqldate2+"'";
         rs =st.executeQuery(mysql);
         while(rs.next()) {
              retail += rs.getInt("retailprice");    
         }
      
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return retail;
        
    }
    
    public int grossProfit(int totalsale, int totalretail) {
        int totalSale = totalsale;
        int totalRetail  = totalretail;
        int gross = totalSale - totalRetail;
        return gross;
    }
    
    public int retailpriceShipping(Date startdate, Date enddate) {
       int retail = 0;
        sqldate  = startdate;
        sqldate2=enddate;
         
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysql = "SELECT * FROM `cust_info` WHERE `dateoforder` BETWEEN '"+sqldate+"' AND '"+sqldate2+"'";
         rs =st.executeQuery(mysql);
         while(rs.next()) {
              retail += rs.getInt("retailprice");    
         }
      
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return retail;
        
    }
    
}
