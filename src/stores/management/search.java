/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import com.sun.glass.events.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author home
 */
public class search extends javax.swing.JFrame {

    /**
     * Creates new form search
     */
    public search() {
        initComponents();
    }
    
    
    public void Search() {
        
        String column = jcombo2.getSelectedItem().toString();
        String value = key.getText();
        DefaultTableModel model = (DefaultTableModel) customerinfo.getModel();
        DefaultTableModel model2 = (DefaultTableModel) item.getModel();
        
        String ci,cn,ad,phn,eml,pc,cntry,obn,aas,orn,pfwa,sd,ed,dor="";
        String rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts;
        
        
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `cust_info` WHERE `"+column+"` ='"+value+"'";
         rs =st.executeQuery(mysqlQuery);
         if(rs.next() && rs.getInt(1) !=0) {
             ci = rs.getString("customer id");
             cn = rs.getString("customer name");
             ad = rs.getString("address");
             pc = rs.getString("postal code");
             phn = rs.getString("phone");
             cntry = rs.getString("country");
             eml=rs.getString("email");
             obn = rs.getString("object number");
             aas  =rs.getString("auctionstore");
             orn = rs.getString("ordernumber");
             pfwa = rs.getString("pfwa");
             sd = rs.getString("startdate");
             ed = rs.getString("enddate");
             dor = rs.getString("dateoforder");
             rp = rs.getString("retailprice");
             rsp = rs.getString("retailpriceshipping");
             sp = rs.getString("sellingprice");
             sps = rs.getString("sellingpriceshipping");
             er = rs.getString("earnings");
             mop = rs.getString("methododpayment");
             pd = rs.getString("pickdate");
             dosm = rs.getString("dosManufacuturer");
             dosc = rs.getString("dosCompany");
             sts = rs.getString("status");
             model.addRow(new Object[] {ci,cn,ad,pc,phn,cntry,eml});
             model2.addRow(new Object[] {obn,aas,orn,pfwa,sd,ed,dor,rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts});
                     
         } else {
             JOptionPane.showMessageDialog(null, "Not found");
         }
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
    }
    
    
    public void Search2() {
        
        String column = jcombo3.getSelectedItem().toString();
        String value = key1.getText();
        DefaultTableModel model = (DefaultTableModel) customerinfo.getModel();
        DefaultTableModel model2 = (DefaultTableModel) item.getModel();
        
        String ci,cn,ad,phn,eml,pc,cntry,obn,aas,orn,pfwa,sd,ed,dor="";
        String rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts;
        
        
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `cust_info` WHERE `"+column+"` ='"+value+"'";
         rs =st.executeQuery(mysqlQuery);
         if(rs.next() && rs.getInt(1) !=0) {
             ci = rs.getString("customer id");
             cn = rs.getString("customer name");
             ad = rs.getString("address");
             pc = rs.getString("postal code");
             phn = rs.getString("phone");
             cntry = rs.getString("country");
             eml=rs.getString("email");
             obn = rs.getString("object number");
             aas  =rs.getString("auctionstore");
             orn = rs.getString("ordernumber");
             pfwa = rs.getString("pfwa");
             sd = rs.getString("startdate");
             ed = rs.getString("enddate");
             dor = rs.getString("dateoforder");
             rp = rs.getString("retailprice");
             rsp = rs.getString("retailpriceshipping");
             sp = rs.getString("sellingprice");
             sps = rs.getString("sellingpriceshipping");
             er = rs.getString("earnings");
             mop = rs.getString("methododpayment");
             pd = rs.getString("pickdate");
             dosm = rs.getString("dosManufacuturer");
             dosc = rs.getString("dosCompany");
             sts = rs.getString("status");
             model.addRow(new Object[] {ci,cn,ad,pc,phn,cntry,eml});
             model2.addRow(new Object[] {obn,aas,orn,pfwa,sd,ed,dor,rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts});
                     
         } else {
             JOptionPane.showMessageDialog(null, "Not found");
         }
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
    }
    
    public void Search3() {
        
        String column = jcombo4.getSelectedItem().toString();
        String value = key2.getText();
        DefaultTableModel model = (DefaultTableModel) customerinfo.getModel();
        DefaultTableModel model2 = (DefaultTableModel) item.getModel();
        
        String ci,cn,ad,phn,eml,pc,cntry,obn,aas,orn,pfwa,sd,ed,dor="";
        String rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts;
        
        
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `cust_info` WHERE `"+column+"` ='"+value+"'";
         rs =st.executeQuery(mysqlQuery);
         if(rs.next() && rs.getInt(1) !=0) {
             ci = rs.getString("customer id");
             cn = rs.getString("customer name");
             ad = rs.getString("address");
             pc = rs.getString("postal code");
             phn = rs.getString("phone");
             cntry = rs.getString("country");
             eml=rs.getString("email");
             obn = rs.getString("object number");
             aas  =rs.getString("auctionstore");
             orn = rs.getString("ordernumber");
             pfwa = rs.getString("pfwa");
             sd = rs.getString("startdate");
             ed = rs.getString("enddate");
             dor = rs.getString("dateoforder");
             rp = rs.getString("retailprice");
             rsp = rs.getString("retailpriceshipping");
             sp = rs.getString("sellingprice");
             sps = rs.getString("sellingpriceshipping");
             er = rs.getString("earnings");
             mop = rs.getString("methododpayment");
             pd = rs.getString("pickdate");
             dosm = rs.getString("dosManufacuturer");
             dosc = rs.getString("dosCompany");
             sts = rs.getString("status");
             model.addRow(new Object[] {ci,cn,ad,pc,phn,cntry,eml});
             model2.addRow(new Object[] {obn,aas,orn,pfwa,sd,ed,dor,rp,rsp,sp,sps,er,mop,pd,dosm,dosc,sts});
                     
         } else {
             JOptionPane.showMessageDialog(null, "Not found");
         }
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcombo2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerinfo = new javax.swing.JTable();
        key = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        item = new javax.swing.JTable();
        jcombo3 = new javax.swing.JComboBox<>();
        jcombo4 = new javax.swing.JComboBox<>();
        key1 = new javax.swing.JTextField();
        key2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search");

        jLabel1.setText("Search By:");

        jcombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "customer id", "customer name", "object number", "ordernumber", "status", "auctionstore", "phone", " " }));
        jcombo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombo2ActionPerformed(evt);
            }
        });

        customerinfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Id", "Customer Name", "Address", "Postal Code", "Contact No", "Country", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(customerinfo);

        key.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyKeyPressed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "object No", "auction Store", "order No", "pfwa", "start date", "end date", "date of order", "retail price", "rps", "selling price", "sps", "earning", "MOP", "pick date", "DOS_M", "DOS_C", "Status"
            }
        ));
        jScrollPane2.setViewportView(item);

        jcombo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "customer id", "customer name", "object number", "ordernumber", "status", "auctionstore", "phone", " " }));
        jcombo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombo3ActionPerformed(evt);
            }
        });

        jcombo4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "customer id", "customer name", "object number", "ordernumber", "status", "auctionstore", "phone", " " }));
        jcombo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombo4ActionPerformed(evt);
            }
        });

        key1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                key1KeyPressed(evt);
            }
        });

        key2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                key2KeyPressed(evt);
            }
        });

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcombo4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(key2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(key1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jcombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jcombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(key1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jcombo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(key2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jcombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombo2ActionPerformed
        
    }//GEN-LAST:event_jcombo2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void keyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyKeyPressed
     if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
         Search();
     }

    }//GEN-LAST:event_keyKeyPressed

    private void jcombo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcombo3ActionPerformed

    private void jcombo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcombo4ActionPerformed

    private void key1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_key1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Search2();
        }
    }//GEN-LAST:event_key1KeyPressed

    private void key2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_key2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Search3();
        }
    }//GEN-LAST:event_key2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Search2();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Search3();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable customerinfo;
    private javax.swing.JTable item;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcombo2;
    private javax.swing.JComboBox<String> jcombo3;
    private javax.swing.JComboBox<String> jcombo4;
    private javax.swing.JTextField key;
    private javax.swing.JTextField key1;
    private javax.swing.JTextField key2;
    // End of variables declaration//GEN-END:variables
}
