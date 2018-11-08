/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class performance extends javax.swing.JFrame {

    /**
     * Creates new form performance
     */
    public performance() {
        initComponents();
    }
    
    
    
    public int totalSales=0,totalRetail=0,rps=0,sps=0,gp=0;
    public int totalSales() {
         int option = combo.getSelectedIndex();
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs,rs1;
         String mysqlQuery = "SELECT `sellingprice` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"'";
         
         String mysql = "SELECT `retailprice` FROM cust_info";
//         String mysql = "SELECT SUM(sellingprice) FROM cust_info";
         rs =st.executeQuery(mysqlQuery);
         
         while(rs.next()) {
            totalSales += rs.getInt("sellingprice");
            String s  = Integer.toString(totalSales);
            sales.setText(s);
         }
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return totalSales;
    }
    
    public void te() {
        String value_sold = sold.getText();
     int value_s = Integer.parseInt(value_sold);
     
     String oe = exp.getText();
     int expenses = Integer.parseInt(oe);
     
     int totalExp = value_s + expenses;
     String te = Integer.toString(totalExp);
     
     totalExpenses.setText(te);
    }
    
    public int totalRetail() {
        int option = combo.getSelectedIndex();
         totalRetail=0;
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs,rs1;
         String mysqlQuery = "SELECT `retailprice` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"' ";
         rs =st.executeQuery(mysqlQuery);
         
         while(rs.next()) {
            totalRetail += rs.getInt("retailprice");
            String s  = Integer.toString(totalRetail);
            sold.setText(s);
         }
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return totalRetail;
    }
    
    public int grossprofit() {
        int sales = totalSales;
        int retail =totalRetail;
        gp = sales-retail;
        int x = Math.abs(gp);
        String s = Integer.toString(x);
        gprofit.setText(s);
        return gp;
        
    }
    
    public int rps() {
         rps=0;
         int option = combo.getSelectedIndex();
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs,rs1;
         String mysqlQuery = "SELECT `retailpriceshipping` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"'  ";
         rs =st.executeQuery(mysqlQuery);
         
         while(rs.next()) {
            rps += rs.getInt("retailpriceshipping");
            String s  = Integer.toString(rps);
            
         }
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return rps;
    }
    
    
        
        public int sps() {
             sps=0;
             int option = combo.getSelectedIndex();
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs,rs1;
         String mysqlQuery = "SELECT `sellingpriceshipping` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"' ";
         rs =st.executeQuery(mysqlQuery);
         
         while(rs.next()) {
            sps += rs.getInt("sellingpriceshipping");
            String s  = Integer.toString(sps);
            
         }
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
        return sps;
        }
        
        public void oe() {
        int retailpriceshipping = rps;
        int sellingpriceshipping =sps;
        int oe = rps + sps;
        int x = Math.abs(oe);
        String s = Integer.toString(x);
        exp.setText(s);
        
    }
        public void FrameClose() {
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        exp = new javax.swing.JTextField();
        nprofit = new javax.swing.JTextField();
        sales = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sold = new javax.swing.JTextField();
        gprofit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalExpenses = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Performance");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Performance"));

        exp.setEditable(false);
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });

        nprofit.setEditable(false);
        nprofit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nprofitActionPerformed(evt);
            }
        });

        sales.setEditable(false);
        sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesActionPerformed(evt);
            }
        });

        jLabel4.setText("Other Expenses:");

        sold.setEditable(false);
        sold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soldActionPerformed(evt);
            }
        });

        gprofit.setEditable(false);
        gprofit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gprofitActionPerformed(evt);
            }
        });

        jLabel2.setText("Value Of Product Sold:");

        jLabel3.setText("Gross Profit:");

        jLabel5.setText("Net Profit:");

        jLabel1.setText("Sales Revenue:");

        jLabel6.setText("Miscellaneous Expense 1 ");

        jLabel7.setText("Miscellaneous Expense 2");

        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("Total Expenses:");

        totalExpenses.setEditable(false);
        totalExpenses.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(62, 62, 62)
                                    .addComponent(sales, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nprofit, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                        .addComponent(totalExpenses))))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(21, 21, 21)
                            .addComponent(jTextField2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gprofit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nprofit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(totalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesActionPerformed

    private void soldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soldActionPerformed

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expActionPerformed

    private void gprofitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gprofitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gprofitActionPerformed

    private void nprofitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nprofitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nprofitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened



       
     totalSales();
     totalRetail();
     int GPPP = grossprofit();
     int retailsp = rps();
     int sellingsp = sps();
     int otherExpenses = retailsp+sellingsp;
     int gpp_abs = Math.abs(GPPP);
     int oe_abs = Math.abs(otherExpenses);
     int netProfit = gpp_abs-oe_abs;
     int net_abs = Math.abs(netProfit);
     
     
     String oee = Integer.toString(oe_abs);
     String np = Integer.toString(net_abs);
     
     
     
     
     exp.setText(oee);
     nprofit.setText(np);
        
            
        
     
        
    
    }//GEN-LAST:event_formWindowOpened

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        
            totalSales();
     totalRetail();
     int GPPP = grossprofit();
     int retailsp = rps();
     int sellingsp = sps();
     int otherExpenses = retailsp+sellingsp;
     int gpp_abs = Math.abs(GPPP);
     int oe_abs = Math.abs(otherExpenses);
     int netProfit = gpp_abs-oe_abs;
     int net_abs = Math.abs(netProfit);
     
     
     String oee = Integer.toString(oe_abs);
     String np = Integer.toString(net_abs);
     
     exp.setText(oee);
     nprofit.setText(np);
     
     te();
        
     









 /*----------------------------------------------------------------------------------------------------*/        
       /* int option = combo.getSelectedIndex();
        try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"'";
            String mysqlQuery = "SELECT SUM(`sellingprice`) FROM cust_info";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Date d = rs.getDate("dateoforder");
                String cid = rs.getString("customer id");
                String cname = rs.getString("customer name");
                String obj = rs.getString("object number");
                String astore = rs.getString("auctionstore");
                String orderno = rs.getString("ordernumber");
                String pfwaa = rs.getString("pfwa");
                Date sd = rs.getDate("startdate");
                Date ed = rs.getDate("enddate");
                Date dor = rs.getDate("dateoforder");
                Date dop = rs.getDate("dateofpayment");
                Date m = rs.getDate("dosManufacuturer");
                Date c = rs.getDate("dosCompany");
                String s = rs.getString("status");

              System.out.println(cid + " " + cname);

            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }*/
    }//GEN-LAST:event_comboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        performance obj = new performance();
        FrameClose();
        obj.setVisible(true);
        
        int x=0,y =0,z=0;
        sales.setText("");
        sold.setText("");
        gprofit.setText(Integer.toString(x));
        exp.setText(Integer.toString(y));
        nprofit.setText(Integer.toString(z));
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(performance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(performance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(performance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(performance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new performance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JTextField exp;
    private javax.swing.JTextField gprofit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField nprofit;
    private javax.swing.JTextField sales;
    private javax.swing.JTextField sold;
    private javax.swing.JTextField totalExpenses;
    // End of variables declaration//GEN-END:variables
}
