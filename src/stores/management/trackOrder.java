/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author home
 */
public class trackOrder extends javax.swing.JFrame {

    /**
     * Creates new form trackOrder
     */
    String month;
    int  value;
    long t;
    public void getdata() {
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            System.out.println("connected");
            String query = "SELECT * FROM `cust_info`";
            rs =st.executeQuery(query);
            while(rs.next()) {
                model.addRow(new Object[] {rs.getString("customer id"),rs.getString("customer name"),rs.getString("object number"),rs.getString("auctionstore"),rs.getString("ordernumber"),rs.getString("pfwa"),rs.getDate("startdate"),rs.getString("enddate"),rs.getDate("dateoforder"),rs.getDate("dateofpayment"),rs.getDate("dosManufacuturer"),rs.getDate("dosCompany"),rs.getString("status")});
            }
         }catch(Exception e) {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
    }
    public trackOrder() {
        initComponents();
        
    }
    public void filter(String key) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(key));
    }
   /* public ArrayList<data> data() {
        ArrayList<data> datalist = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            System.out.println("connected");
            String query = "SELECT * FROM `custo_info`";
            rs =st.executeQuery(query);
            data d;
            while(rs.next()){
//                d = new data(rs.getString("object number"),rs.getString("auctionstore"),rs.getString("ordernumber"),rs.getString("pfwa"),rs.getDate("startdate"));
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    public void searchUpdate() {
        String id = key.getText();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String mysqlQuery = "SELECT * FROM `cust_info` WHERE `customer id` ='"+id+"'";
            rs = st.executeQuery(mysqlQuery);
            if(rs.next()) {
                    String idd = rs.getString("customer id");
                 
                 String name = rs.getString("customer name");
                 String add = rs.getString ("address");
                 String pc = rs.getString("postal code");
                 String phn = rs.getString("phone");
                 String cn = rs.getString("country");
                 String eml = rs.getString("email");
                 String objno = rs.getString("object number");
                 String astore = rs.getString("auctionstore");
                 String ornumber = rs.getString("ordernumber");
                 String pfwa = rs.getString("pfwa");
                 Date sd = rs.getDate("startdate");
                 Date ed = rs.getDate("enddate");
                 Date dor = rs.getDate("dateoforder");
                 int rp = rs.getInt("retailprice");
                 int rpss = rs.getInt("retailpriceshipping");
                 int spp = rs.getInt("sellingprice");
                 int spss = rs.getInt("sellingpriceshipping");
                 int er = rs.getInt("earnings");
                 String mop = rs.getString("methododpayment");
                 Date pdd = rs.getDate("dateofpayment");
                 Date dosm = rs.getDate("dosManufacuturer");
                 Date dosc  = rs.getDate("dosCompany");
                 String ss = rs.getString("status");
                 String pid = rs.getString("productid");
                 model.addRow(new Object[] {idd,name,objno,astore,ornumber,pfwa,sd,ed,dor,pdd,dosm,dosc,ss});
           
                 }else {
                     JOptionPane.showMessageDialog(null,"Not Found.");
                 }
            
    
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
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
        key = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        search = new javax.swing.JButton();
        combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Track Order");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Search By ID:");

        key.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keyKeyReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Id", "Customer Name", "Object No", "Auction Store", "Order No", "PFWA", "Start Date", "End Date", "Date Of Order", "DOP", "DOM", "DOC", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

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

        jLabel2.setText("Search By Month");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(86, 86, 86))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    getdata();
      
    }//GEN-LAST:event_formWindowOpened

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
        searchUpdate();
    }//GEN-LAST:event_searchActionPerformed

    private void keyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyKeyReleased
        String k = key.getText();
        filter(k);
    }//GEN-LAST:event_keyKeyReleased

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
       DefaultTableModel model = (DefaultTableModel) table.getModel();
        int option = combo.getSelectedIndex();
       try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
           Statement st = con.createStatement();
           ResultSet rs;
           String sql = "SELECT * FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"'";
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
               
               model.addRow(new Object[] {cid,cname,obj,astore,orderno,pfwaa,sd,ed,dor,dop,m,c,s});
               
               
           }
       }catch(Exception e) {
           System.out.println(e.getMessage());
       }
    }//GEN-LAST:event_comboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
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
            java.util.logging.Logger.getLogger(trackOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(trackOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(trackOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(trackOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new trackOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField key;
    private javax.swing.JButton search;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
