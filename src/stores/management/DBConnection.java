/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class DBConnection {
 
    private String URI,USERNAME,PASSWORD;
    
    
    public void checkConnection(String uri,String username,String password) {
     URI = uri;
     USERNAME = username;
     PASSWORD = password;
     try {
         Connection conn = (Connection) DriverManager.getConnection(uri, username, password);
         JOptionPane.showMessageDialog(null,"Connected To Database");
     }catch(Exception e) {
         JOptionPane.showMessageDialog(null,"Not Connected ! Please Check Xampp Control Panel");
     }   
    }
  
 public void createConnection(String uri,String username,String password) {
     URI = uri;
     USERNAME = username;
     PASSWORD = password;
     try {
         Connection conn = (Connection) DriverManager.getConnection(uri, username, password);
         Statement st = conn.createStatement();
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
     
 }
 
 public void addData(String id,String cname,String address,String pcode,String phone,String country,String email,String objectno,String astore,String ordern,String pfw,String sd,String ed, String door, int reprice, int rpps, int sellingp,
 int ssps,int earning, String mopp, String pickdate, String dom, String dos, String paymentSts,String pid) {
     try {
         Connection conn = (Connection) DriverManager.getConnection(URI, USERNAME, PASSWORD);
         Statement st  = conn.createStatement();
         String mysql = "INSERT INTO `customerinfo`.`cust_info` (`customer id`,`customer name`,`address`,`postal code`,`phone`,`country`,`email`,`object number`,`auctionstore`,`ordernumber`,`pfwa`,`startdate`,`enddate`,`dateoforder`,`retailprice`,`retailpriceshipping`,`sellingprice`,`sellingpriceshipping`,`earnings`,`methododpayment`,`dateofpayment`,`dosManufacuturer`,`dosCompany`,`status`,`productid`) "
                 + "VALUES ('"+ id +"','"+ cname +"','"+ address +"','"+ pcode +"','"+ phone +"','"+ country +"','"+ email +"','"+objectno+"','"+astore+"','"+ordern+"','"+pfw+"','"+sd+"','"+ed+"','"+door+"','"+reprice+"','"+rpps+"','"+sellingp+"'"
                 + ",'"+ssps+"','"+earning+"','"+mopp+"','"+pickdate+"','"+dom+"','"+dos+"','"+paymentSts+"','"+pid+"');";
         st.executeUpdate(mysql);
         JOptionPane.showMessageDialog(null,"Record Added !");
     } catch(Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
     }
     
     
 }
 public void updateData(String id,String cname,String address,String pcode,String phone,String country,String email,String objectno,String astore,String ordern,String pfw,String sd,String ed, String door, int reprice, int rpps, int sellingp,
 int ssps,int earning, String mopp, String pickdate, String dom, String dos, String paymentSts,String pid) {
     try {
         Connection conn = (Connection) DriverManager.getConnection(URI, USERNAME, PASSWORD);
         Statement st  = conn.createStatement();
         String mysql = "UPDATE `cust_info` SET (`customer id`,`customer name`,`address`,`postal code`,`phone`,`country`,`email`,`object number`,`auctionstore`,`ordernumber`,`pfwa`,`startdate`,`enddate`,`dateoforder`,`retailprice`,`retailpriceshipping`,`sellingprice`,`sellingpriceshipping`,`earnings`,`methododpayment`,`dateofpayment`,`dosManufacuturer`,`dosCompany`,`status`,`productid`) "
                 + "VALUES ('"+ id +"','"+ cname +"','"+ address +"','"+ pcode +"','"+ phone +"','"+ country +"','"+ email +"','"+objectno+"','"+astore+"','"+ordern+"','"+pfw+"','"+sd+"','"+ed+"','"+door+"','"+reprice+"','"+rpps+"','"+sellingp+"'"
                 + ",'"+ssps+"','"+earning+"','"+mopp+"','"+pickdate+"','"+dom+"','"+dos+"','"+paymentSts+"','"+pid+"');";
         st.executeUpdate(mysql);
         JOptionPane.showMessageDialog(null,"Record Updated !");
     } catch(Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
     }
     
     
 }
 
 public String getData() {
 
     String values = "";
     try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `custinfo`";
         rs = st.executeQuery(mysqlQuery);
         while(rs.next()) {
              values = rs.getString("customer id");
         }
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
         return values;
 }
}



