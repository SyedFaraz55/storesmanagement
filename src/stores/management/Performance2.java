/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author home
 */
public class Performance2 extends javax.swing.JFrame {

    /**
     * Creates new form Performance2
     */
    public Performance2() {
        initComponents();
        
       
    }
    
    java.util.Date date,date2;
    java.sql.Date sqldate,sqldate2;
    
    
    // Total Expenses
    public void ttl() {
        int s =Integer.parseInt(sold.getText());
    int e = Integer.parseInt(exp.getText());
    int total_exp = s+e;
    totalExpenses.setText(Integer.toString(total_exp));
    }
    
    
    // Gross profit
    
    public void grp() {
        int gp = Integer.parseInt(gprofit.getText());
    int totale = Integer.parseInt(exp.getText());
    int net = gp - totale;
    if(net<0) {
        nprofit.setForeground(Color.red);
        nprofit.setText(Integer.toString(net));
    } 
    }
    
    public void range() {
         date = startdate.getDate();
        sqldate = new java.sql.Date(date.getTime());
        
        date2 = enddate.getDate();
        sqldate2 = new java.sql.Date(date2.getTime());
        try {
            Connection con  = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String mysql = "SELECT * FROM `cust_info` WHERE `dateoforder` BETWEEN '"+sqldate+"' AND '"+sqldate2+"'";
            rs = st.executeQuery(mysql);
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    public int retailPrices(){
        int retailPriceShipping = 0;
        try {
            Connection con  =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String mysql = "SELECT * FROM cust_info";
            rs = st.executeQuery(mysql);
            while(rs.next()) {
                 retailPriceShipping = rs.getInt("retailpriceshipping");
            }
            retailP.setText(Integer.toString(retailPriceShipping));
        }catch(Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retailPriceShipping;
    }
    
    public int sellingPrices(){
        int sellingpriceShipping = 0;
        try {
            Connection con  =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String mysql = "SELECT * FROM cust_info";
            rs = st.executeQuery(mysql);
            while(rs.next()) {
                 sellingpriceShipping = rs.getInt("sellingpriceshipping");
            }
            sellingP.setText(Integer.toString(sellingpriceShipping));
        }catch(Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return sellingpriceShipping;
    }
    
    public int totalE() {
        int rp = totalRetail;
        int sp = totalSales;
        int cost= 0;
        try{
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st= con.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM other_expenses";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                 cost = cost + rs.getInt("cost");    
            }
            
            int mse = rp+sp+cost;
            exp.setText(Integer.toString(mse));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return cost;
    }
    
    
    public int totalRetails() {
        int option = combo.getSelectedIndex();
         totalRetail=0;
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
       
         ResultSet rs,rs1;
//         String mysqlQuery = "SELECT `retailprice` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"' ";
        String mysql = "SELECT `retailprice` FROM cust_info";
         rs =st.executeQuery(mysql);
         
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
    
    
    public int totalSale() {
        int option = combo.getSelectedIndex();
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs,rs1;
         String mysqlQuery2 = "SELECT `sellingprice` FROM `cust_info` WHERE MONTH(dateoforder) = '"+option+"'";
         String mysqlQuery = "SELECT * FROM `cust_info`";
         
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
    
    public int gross_profit() {
        int gp = totalSales - totalRetail;
        gprofit.setText(Integer.toString(gp));
        return gp;
    }
    
    
    
    
    
    public void performance() {
        DefaultTableModel model = (DefaultTableModel) addexp.getModel();
       int option = combo.getSelectedIndex();
       
       try {
           Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
           Statement st = con.createStatement();
           Statement st2 = con.createStatement();
           ResultSet rs,rs1;
         String mysqlQuery = "SELECT `cost` FROM `other_expenses` WHERE MONTH(dateoforder) = '"+option+"' ";
         String mysql2 = "SELECT * FROM `other_expenses` WHERE MONTH(dateoforder) = '"+option+"'";
         rs =st.executeQuery(mysqlQuery);
         rs1 = st2.executeQuery(mysql2);
         
         while(rs.next()) {
            cost += rs.getInt("cost");
            String s  = Integer.toString(cost);
            exp.setText(s);
            
         }
         while(rs1.next()) {
             String ename = rs1.getString("expenses name");
             int cost = rs1.getInt("cost");
             
             model.addRow(new Object[] {ename,cost});
         }
         
        
           
       }catch(Exception e)  {
           JOptionPane.showMessageDialog(null,e.getMessage());
       }
       
       int  grossprofit  = Integer.parseInt(gprofit.getText());
       int total_expenses = Integer.parseInt(exp.getText());
       
       int net_profit = grossprofit - total_expenses;
       if(net_profit<0) {
           nprofit.setForeground(Color.red);
           nprofit.setText(Integer.toString(net_profit));
       } 
       
       int valueofsold = Integer.parseInt(sold.getText());
       int total = Integer.parseInt(exp.getText());
       int te = valueofsold+total;
       totalExpenses.setText(Integer.toString(te));
       
       
       
       
    }
    
    public int cost = 0;  // Global Variable for tracking cost;
    
     public void FrameClose() {
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
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
    
    public int  te() {
        String value_sold = sold.getText();
     int value_s = Integer.parseInt(value_sold);
     
     String oe = exp.getText();
     int expenses = Integer.parseInt(oe);
     
     int totalExp = value_s + expenses;
     String te = Integer.toString(totalExp);
     
     totalExpenses.setText(te);
     return totalExp;
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
//        exp.setText(s);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
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
        jLabel8 = new javax.swing.JLabel();
        totalExpenses = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        retailP = new javax.swing.JTextField();
        sellingP = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jtable = new javax.swing.JScrollPane();
        addexp = new javax.swing.JTable();
        clear = new javax.swing.JButton();
        pfb = new javax.swing.JButton();
        startdate = new com.toedter.calendar.JDateChooser();
        enddate = new com.toedter.calendar.JDateChooser();
        performance = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        mcost = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Performance");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Performance"));

        exp.setEditable(false);
        exp.setText("0");
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });

        nprofit.setEditable(false);
        nprofit.setText("0");
        nprofit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nprofitActionPerformed(evt);
            }
        });

        sales.setEditable(false);
        sales.setText("0");
        sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesActionPerformed(evt);
            }
        });

        jLabel4.setText("Total Other Expenses:");

        sold.setEditable(false);
        sold.setText("0");
        sold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soldActionPerformed(evt);
            }
        });

        gprofit.setEditable(false);
        gprofit.setText("0");
        gprofit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gprofitActionPerformed(evt);
            }
        });

        jLabel2.setText("Value Of Product Sold:");

        jLabel3.setText("Gross Profit:");

        jLabel5.setText("Net Profit:");

        jLabel1.setText("Sales Revenue:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("Total Expenses:");

        totalExpenses.setEditable(false);
        totalExpenses.setForeground(new java.awt.Color(102, 0, 102));
        totalExpenses.setText("0");

        jLabel9.setText("Retail Price Shiping:");

        jLabel10.setText("Selling Price Shipping:");

        retailP.setEditable(false);
        retailP.setText("0");

        sellingP.setEditable(false);
        sellingP.setText("0");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(36, 36, 36)
                        .addComponent(sellingP))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(62, 62, 62)
                                .addComponent(sales, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(29, 29, 29)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(retailP)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gprofit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(retailP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(sellingP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nprofit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(totalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        addexp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Cost"
            }
        ));
        addexp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addexp.getTableHeader().setReorderingAllowed(false);
        jtable.setViewportView(addexp);

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        pfb.setText("Performance from Beginning");
        pfb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pfbMouseClicked(evt);
            }
        });
        pfb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfbActionPerformed(evt);
            }
        });

        performance.setText("Get Performance");
        performance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performanceActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Total :");

        mcost.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pfb, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(performance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mcost))
                    .addComponent(jtable, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(performance))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jtable, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mcost))
                .addGap(117, 117, 117))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(391, 391, 391))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(37, 37, 37)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expActionPerformed

    private void nprofitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nprofitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nprofitActionPerformed

    private void salesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesActionPerformed

    private void soldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soldActionPerformed

    private void gprofitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gprofitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gprofitActionPerformed

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
        
        int rpps = rps;
        String rps = Integer.toString(rpps);
        retailP.setText(rps);
        int spps = sps;
        String sps = Integer.toString(spps);
        sellingP.setText(sps);
        performance();
        
        

//        exp.setText(oee);
//        nprofit.setText(np);

//        te();

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
    }//GEN-LAST:event_formWindowOpened

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        Performance2 obj = new Performance2();
        FrameClose();
        obj.setVisible(true);

        int x=0,y =0,z=0;
        sales.setText(Integer.toString(0));
        sold.setText(Integer.toString(0));
        gprofit.setText(Integer.toString(x));
        exp.setText(Integer.toString(y));
        retailP.setText(Integer.toString(x));
        sellingP.setText(Integer.toString(x));
        nprofit.setText(Integer.toString(z));
        totalExpenses.setText(Integer.toString(0));
    }//GEN-LAST:event_clearActionPerformed

    private void pfbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfbActionPerformed
    totalSale();
    totalRetails();
    retailPrices();
    sellingPrices();
    gross_profit();
    totalE();
    grp();
    ttl();
    
    
    
    
    
    
    }//GEN-LAST:event_pfbActionPerformed

    private void performanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performanceActionPerformed
        date = startdate.getDate();
        sqldate = new java.sql.Date(date.getTime());
        
        date2 = enddate.getDate();
        sqldate2 = new java.sql.Date(date2.getTime());
        
        helperClass object = new helperClass();
        int saless = object.sales(sqldate, sqldate2);
        sales.setText(Integer.toString(saless));
        int retail = object.retail(sqldate, sqldate2);
        System.out.println("Total Sales: " + saless);
        System.out.println("total retail: " + retail);
        sold.setText(Integer.toString(retail));
        int gross = object.grossProfit(saless, retail);
        gprofit.setText(Integer.toString(gross));
        System.out.println("Gross: " + gross);
        int rps = object.retailpriceShipping(sqldate, sqldate2);
        retailP.setText(Integer.toString(rps));
        System.out.println("retail price shipping: " + rps);
        int sps = object.sellingpriceShipping(sqldate, sqldate2);
        System.out.println("selling price shipping: " + sps);
        sellingP.setText(Integer.toString(sps));
        int total_other_expenses  = object.totalOE(sqldate, sqldate2, retail, saless);
        int c = object.cost;
        System.out.println("Cost: " + c);
        exp.setText(Integer.toString(total_other_expenses));
        mcost.setText(Integer.toString(c));
        System.out.println("Total Other Expenses: " + total_other_expenses);
        int net_profit = gross - total_other_expenses;
        nprofit.setText(Integer.toString(net_profit));
        System.out.println("Net Profit: " + net_profit);
        int finalresult = saless + total_other_expenses;
        totalExpenses.setText(Integer.toString(finalresult));
        System.out.println("Total Expenses: " + finalresult);
        
    }//GEN-LAST:event_performanceActionPerformed

    private void pfbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pfbMouseClicked
        pfb.setVisible(false);
    }//GEN-LAST:event_pfbMouseClicked

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
            java.util.logging.Logger.getLogger(Performance2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Performance2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Performance2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Performance2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Performance2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable addexp;
    private javax.swing.JButton clear;
    private javax.swing.JComboBox<String> combo;
    private com.toedter.calendar.JDateChooser enddate;
    private javax.swing.JTextField exp;
    private javax.swing.JTextField gprofit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jtable;
    private javax.swing.JLabel mcost;
    private javax.swing.JTextField nprofit;
    private javax.swing.JPanel panel;
    private javax.swing.JButton performance;
    private javax.swing.JButton pfb;
    private javax.swing.JTextField retailP;
    private javax.swing.JTextField sales;
    private javax.swing.JTextField sellingP;
    private javax.swing.JTextField sold;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JTextField totalExpenses;
    // End of variables declaration//GEN-END:variables
}
