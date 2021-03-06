/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import com.mysql.jdbc.Connection;
import com.sun.glass.events.KeyEvent;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import stores.management.dao.*;
import stores.management.dbcon.DataBaseConnection;
import stores.management.entity.*;
import stores.management.dao.ProductDao;

/**
 *
 * @author home
 */
public class Place_an_order extends javax.swing.JFrame {

    /**
     * Creates new form Place_an_order
     */
    
    private Timer t;
    private ActionListener al;
    public Place_an_order() {
        initComponents();
        
     al = new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               progress.setIndeterminate(true);
               if(progress.getValue()<100) {
                   progress.setValue(progress.getValue()+2);
                   if(progress.getValue()==50)
                   progress.setString("Connecting Database..");
                   
               } else {
                   t.stop();
                   progress.setVisible(false);
                    ids();
                    auctionstores();
                    paymethods();
                    addAccounts();
                    records();
               }
           }
       };
       t = new Timer(200,al);
       t.start();
    
    }
    java.util.Date date,date2,date3,date4,date5,date6,dd=null;
    java.sql.Date sqldate,sqldate2,sqldate3,sqldate4,sqldate5,sqldate6,extra=null;
    
{
    
}
 public void FrameClose() {
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    
   
    public void records() {
        try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo","root","");
            Statement st = con.createStatement();
            ResultSet rs;
            String sql = "SELECT COUNT(*) FROM `cust_info`";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                int rowCount = rs.getInt(1);
            rec.setText(Integer.toString(rowCount));
            }
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    private void fillTable(String sName) throws IOException {
        try {
            long id = Long.parseLong(productid.getText());
        ProductDao pDao = new ProductDao();
        Product p = pDao.get(id);
            if(p.getPhoto1() != null && p.getPhoto1().getPhoto() != null) {
                Image image = ImageIO.read(p.getPhoto1().getPhoto());
            ImageIcon icon = new ImageIcon(image);
            imagePreview.setIcon(new ImageIcon(icon.getImage().getScaledInstance(imagePreview.getWidth(),
                    imagePreview.getHeight(), Image.SCALE_SMOOTH)));
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Invalid Id or this item doesn't exits...");
        }
        
        
    }
    public boolean validation() {
        if(email.getText().contains("@")) {
            return true;
        } else {
             return false;
        }
    }
    
    public void calculateEarning() {
        try {
            int retailprice = Integer.parseInt(retailP.getText());
            int retailshippingprice = Integer.parseInt(rps.getText());
            int sellingprice = Integer.parseInt(sp.getText());
            int sellingshippingprice = Integer.parseInt(sps.getText());
            int x,y,earning;
            x = sellingprice + sellingshippingprice;
            y = retailprice + retailshippingprice;
            earning = x - y;
            int earnings = Math.abs(earning);
            String erngs = Integer.toString(earnings);
            er.setText(erngs); 
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"please check the empty fields !");
        }
        
    }
    
    
    private String url = "jdbc:msql://localhost/customerinfo";
    private String username = "root";
    private String password ="";
    
    public String paymentSts;
    
    public void clearFields() {
        customerid.setText("");;
        address.setText("");
        postalcode.setText("");
        country.setText("");
        email.setText("");
        phonenumber.setText("");
        objectnumber.setText("");
        ordernumber.setText("");
        retailP.setText("");
        customername.setText("");
        rps.setText("");
        sps.setText("");
        sp.setText("");
        er.setText("");
        startdate.setCalendar(null);
        enddate.setCalendar(null);
        dateoforder.setCalendar(null);
        pd.setCalendar(null);
        dos_m.setCalendar(null);
        dos_c.setCalendar(null);
        
    }
    
    public void getData() {
        String id = customerid.getText();
        String name = customername.getText();
        String add = address.getText();
        String pc = postalcode.getText();
        String cntry = country.getText();
        String no = phonenumber.getText();
        String eml = email.getText();
        String objectno = objectnumber.getText();
        String aastore = auctionstorebox.getSelectedItem().toString();
        String ordern = ordernumber.getText();
        String pfw = pfwa.getSelectedItem().toString();
        
        date = startdate.getDate();
        sqldate = new java.sql.Date(date.getTime());
        
       date2  = enddate.getDate();
       sqldate2 = new java.sql.Date(date2.getTime());
       
       date3 = dateoforder.getDate();
       sqldate3 = new java.sql.Date(date3.getTime());
       
       date4 = pd.getDate();
       sqldate4 = new java.sql.Date(date4.getTime());
       
       date5 = dos_m.getDate();
       sqldate5 = new java.sql.Date(date5.getTime());
       
       date6 = dos_c.getDate();
       sqldate6 = new java.sql.Date(date6.getTime());
       
       dd = dateoforder.getDate();
       extra = new java.sql.Date(dd.getTime());
       
        
        int reprice =Integer.parseInt(retailP.getText());
        String mopp = mop.getSelectedItem().toString();
        int rpps = Integer.parseInt(rps.getText());
    
        int sellingp = Integer.parseInt(sp.getText());
        int earning = Integer.parseInt(er.getText());
        
        int ssps = Integer.parseInt(sps.getText());
        String pid = productid.getText();
        
        
            //Creating object for database class
        DBConnection dbObject = new DBConnection(); 
        dbObject.createConnection("jdbc:mysql://localhost/customerinfo", "root", "");
        dbObject.addData(id, name, add, pc, no, cntry, eml, objectno, aastore, ordern, pfw, sqldate, sqldate2, sqldate3, reprice, rpps, sellingp, ssps, earning, mopp, sqldate4, sqldate5, sqldate6, paymentSts,pid);
        
        
        
    }
    
    public void check() {
        String sd = startdate.getDate().toString();
        String sd1 = sd.substring(0,10);
        String sd2 = sd.substring(24,28);
        System.out.println(sd);
        System.out.println("SD1 " + sd1);
        System.out.println("SD2 " + sd2);
        String start_date = sd1.concat(" " + sd2);
        
    }
    
    /*public void itemInformation() {
        String objectno = objectnumber.getText();
        String astore = auctionstorebox.getSelectedItem().toString();
        String ordern = ordernumber.getText();
        String pfw = pfwa.getSelectedItem().toString();
        String sd = startdate.getDate().toString();
        String sd1 = sd.substring(0,10);
        String sd2 = sd.substring(24,28);
        System.out.println(sd1.concat(" " + sd2));
        String ed = enddate.getDate().toString();
        String ed1 = ed.substring(0,10);
        String ed2 = ed.substring(24,28);
        System.out.println(ed1.concat(" " + ed2));
        String door = dateoforder.getDate().toString();
        int reprice =Integer.parseInt(retailP.getText());
        String mopp = mop.getSelectedItem().toString();
        int rpps = Integer.parseInt(rps.getText());
        String pickdate = pd.getDate().toString();
        int sellingp = Integer.parseInt(sp.getText());
        int earning = Integer.parseInt(er.getText());
        String dom = dos_m.getDate().toString();
        String dos = dos_c.getDate().toString();
        int ssps = Integer.parseInt(sps.getText());
        
        try {
         Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st  = conn.createStatement();
         String mysql = "INSERT INTO `customerinfo`.`item info` (`objectnumber`,`auction store`,`order number`,`pfwa`,`startdate`,`enddate`,`dateoforder`,`retailprice`,`retailpriceshipping`,`sellingprice`,`sellingpriceshipping`,`earnings`,`methododpayment`,`pickdate`,`dosManufacuturer`,`dosCompany`,`status`) "
                 + "VALUES ('"+objectno+"','"+astore+"','"+ordern+"','"+pfw+"','"+sd+"','"+ed+"','"+door+"','"+reprice+"','"+rpps+"','"+sellingp+"'"
                 + ",'"+ssps+"','"+earning+"','"+mopp+"','"+pickdate+"','"+dom+"','"+dos+"','"+paymentSts+"');";
         st.executeUpdate(mysql);
         JOptionPane.showMessageDialog(null,"Record Added !");
     } catch(Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
     }
        
    } */
    
    public void ids() {
         DBConnection db = new DBConnection();
       try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         msg.setText("Connected");
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `cust_info`";
         rs = st.executeQuery(mysqlQuery);
         while(rs.next()) {
             String id = rs.getString("customer id");
              jcombo1.addItem(id);
         }
     }catch(Exception e) {
         msg.setText("Not Connected to Database !");
     }
    }
    
    public void auctionstores() {
         DBConnection db = new DBConnection();
       try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         msg.setText("Connected");
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `auctionstore`";
         rs = st.executeQuery(mysqlQuery);
         while(rs.next()) {
             String id = rs.getString("auction store");
              auctionstorebox.addItem(id);
         }
     }catch(Exception e) {
         msg.setText("not connected to Database");
     }
    }
    
    public void paymethods() {
         DBConnection db = new DBConnection();
       try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         msg.setText("Connected");
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `paymentmethods`";
         rs = st.executeQuery(mysqlQuery);
         while(rs.next()) {
             String id = rs.getString("payment Methods");
              mop.addItem(id);
         }
     }catch(Exception e) {
         msg.setText("Not Connected To Database");
     }
    }
    public void addAccounts() {
         DBConnection db = new DBConnection();
       try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         msg.setText("Connected");
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `pickfromwhichaccount`";
         rs = st.executeQuery(mysqlQuery);
         while(rs.next()) {
              String id = rs.getString("pfwa");
              pfwa.addItem(id);
         }
     }catch(Exception e) {
         msg.setText("Not Connected To Database");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        customerid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jcombo1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        postalcode = new javax.swing.JTextField();
        phonenumber = new javax.swing.JTextField();
        country = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        customername = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        mop = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        retailP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        auctionstorebox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rps = new javax.swing.JTextField();
        pfwa = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sps = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        sp = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        objectnumber = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        er = new javax.swing.JTextField();
        ordernumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        paid = new javax.swing.JRadioButton();
        unpaid = new javax.swing.JRadioButton();
        completed = new javax.swing.JRadioButton();
        pending = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        sts = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        startdate = new com.toedter.calendar.JDateChooser();
        enddate = new com.toedter.calendar.JDateChooser();
        dateoforder = new com.toedter.calendar.JDateChooser();
        pd = new com.toedter.calendar.JDateChooser();
        dos_m = new com.toedter.calendar.JDateChooser();
        dos_c = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        productid = new javax.swing.JTextField();
        imagePreview = new javax.swing.JLabel();
        getProduct = new javax.swing.JButton();
        jtable = new javax.swing.JScrollPane();
        addexp = new javax.swing.JTable();
        tdisplay = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        addexpbtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        msg = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rec = new javax.swing.JLabel();
        progress = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Place Order");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 283, -1, -1));
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 283, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Information"));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        customerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customeridActionPerformed(evt);
            }
        });
        customerid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customeridKeyTyped(evt);
            }
        });

        jLabel1.setText("New Customer ID:");

        jcombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jcombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombo1ActionPerformed(evt);
            }
        });

        jLabel8.setText("ID's");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(customerid, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(customerid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Address:");

        jLabel6.setText("Email:");

        postalcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postalcodeActionPerformed(evt);
            }
        });
        postalcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postalcodeKeyTyped(evt);
            }
        });

        phonenumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phonenumberKeyTyped(evt);
            }
        });

        jLabel4.setText("Country:");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });

        jLabel3.setText("Postal Code:");

        jLabel5.setText("Phone Number:");

        jLabel23.setText("Customer Name:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel23))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(postalcode, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(customername))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(customername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 27, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Information"));

        mop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mopActionPerformed(evt);
            }
        });

        jLabel18.setText("Selling Price Shipping:");

        retailP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retailPActionPerformed(evt);
            }
        });
        retailP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                retailPKeyTyped(evt);
            }
        });

        jLabel16.setText("Selling Price:");

        auctionstorebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auctionstoreboxActionPerformed(evt);
            }
        });

        jLabel14.setText("Date Of Order");

        jLabel9.setText("Auction Store:");

        rps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rpsKeyTyped(evt);
            }
        });

        jLabel17.setText("Retail Price Shipping:");

        jLabel10.setText("Order Number:");

        sps.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spsFocusLost(evt);
            }
        });
        sps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spsKeyTyped(evt);
            }
        });

        jLabel19.setText("Earnings:");

        jLabel22.setText("Date Of Shipping (Manufacturer):");

        jLabel20.setText("Medthod Of Payment:");

        jLabel21.setText("Date Of Payment:");

        sp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spKeyTyped(evt);
            }
        });

        jLabel28.setText("Date Of Shipping (Company):");

        objectnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                objectnumberKeyTyped(evt);
            }
        });

        jLabel12.setText("Start Date");

        jLabel15.setText("Retail Price:");

        jLabel7.setText("Object Number:");

        er.setEditable(false);
        er.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                erKeyTyped(evt);
            }
        });

        ordernumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordernumberActionPerformed(evt);
            }
        });
        ordernumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ordernumberKeyTyped(evt);
            }
        });

        jLabel13.setText("End Date");

        jLabel11.setText("Pick From Which Account:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Status"));

        buttonGroup1.add(paid);
        paid.setText("Paid");
        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });

        buttonGroup1.add(unpaid);
        unpaid.setText("Unpaid");
        unpaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpaidActionPerformed(evt);
            }
        });

        buttonGroup1.add(completed);
        completed.setText("Completed");
        completed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completedActionPerformed(evt);
            }
        });

        buttonGroup1.add(pending);
        pending.setText("Pending");
        pending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingActionPerformed(evt);
            }
        });

        jLabel25.setText("Status:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(paid)
                        .addGap(18, 18, 18)
                        .addComponent(unpaid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(completed))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pending)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paid)
                    .addComponent(unpaid)
                    .addComponent(completed)
                    .addComponent(pending))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(sts, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("SAVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Calculate Earnings");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(er, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sps, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rps, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(retailP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel21))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mop, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(dos_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dos_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28))))
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(objectnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(pfwa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(auctionstorebox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(ordernumber, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)
                            .addComponent(dateoforder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(objectnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(auctionstorebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(ordernumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pfwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateoforder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(retailP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(rps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel28)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(sps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(er, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dos_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(dos_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 289, 880, 389));

        jLabel27.setText("Product Id:");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 31, -1, -1));
        getContentPane().add(productid, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 28, 160, -1));

        imagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagePreview.setText("Image");
        imagePreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(imagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 96, 192, 181));

        getProduct.setText("Get Product");
        getProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getProductActionPerformed(evt);
            }
        });
        getContentPane().add(getProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 27, -1, -1));

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

        getContentPane().add(jtable, new org.netbeans.lib.awtextra.AbsoluteConstraints(876, 83, 283, 174));

        tdisplay.setText("Total:");
        getContentPane().add(tdisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(876, 265, 42, 11));

        total.setText("0");
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 263, 80, -1));

        addexpbtn.setText("Add Expenses");
        addexpbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addexpbtnActionPerformed(evt);
            }
        });
        getContentPane().add(addexpbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1058, 289, -1, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel29.setText("Status:");

        jLabel30.setText("Records:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rec, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30)
                    .addComponent(rec, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 482, 200, 100));

        progress.setString("Please Wait....");
        progress.setStringPainted(true);
        getContentPane().add(progress, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 670, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //sd
    }//GEN-LAST:event_formWindowOpened

    private void postalcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postalcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_postalcodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void customeridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customeridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customeridActionPerformed

    private void retailPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retailPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_retailPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try {
      if(customerid.getText().equals(" ") || phonenumber.getText().equals("") || email.getText().equals("") || retailP.getText().equals("") 
               || sp.getText().equals("")||rps.getText().equals("")||sps.getText().equals("") || productid.getText().equals(""))
      {
          JOptionPane.showMessageDialog(null, "Please fill all fields !");
      } else {
          getData();
          clearFields();
      }
      }catch(Exception e) {
       JOptionPane.showMessageDialog(null,"All Fields are required");
      }
        

    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    
    }//GEN-LAST:event_formWindowActivated

    private void jcombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombo1ActionPerformed
        String var = jcombo1.getSelectedItem().toString();
        try {
         Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
         Statement st = con.createStatement();
         ResultSet rs;
         String mysqlQuery = "SELECT * FROM `cust_info` WHERE `customer id` ='"+var+"'";
         rs =st.executeQuery(mysqlQuery);
         while(rs.next()) {
             customerid.setText(rs.getString("customer id"));
             customername.setText(rs.getString("customer name"));
             address.setText(rs.getString("address"));
             country.setText(rs.getString("country"));
             postalcode.setText(rs.getString("postal code"));
             phonenumber.setText(rs.getString("phone"));
             email.setText(rs.getString("email"));
             
             sts.setText(paymentSts);
             
             
             
             
         }
         
     }catch(Exception e) {
         System.out.println(e.getMessage());
     }
    }//GEN-LAST:event_jcombo1ActionPerformed

    private void ordernumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordernumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ordernumberActionPerformed

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        paymentSts = "paid";
        System.out.println(paymentSts);
    }//GEN-LAST:event_paidActionPerformed

    private void unpaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unpaidActionPerformed
       paymentSts = "unpaid";
        System.out.println(paymentSts);
    }//GEN-LAST:event_unpaidActionPerformed

    private void completedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completedActionPerformed
        paymentSts = "completed";
        System.out.println(paymentSts);
    }//GEN-LAST:event_completedActionPerformed

    private void pendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingActionPerformed
        paymentSts = "pending";
        System.out.println(paymentSts);
    }//GEN-LAST:event_pendingActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
       String key = email.getText();
       if(key.contains("@")) {
           email.setBackground(Color.white);
           email.setForeground(Color.black);
       } else {
           email.setBackground(Color.red);
           email.setForeground(Color.white);
       }
    }//GEN-LAST:event_emailKeyTyped

    private void auctionstoreboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auctionstoreboxActionPerformed
        
    }//GEN-LAST:event_auctionstoreboxActionPerformed

    private void customeridKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customeridKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_customeridKeyTyped

    private void phonenumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonenumberKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_phonenumberKeyTyped

    private void objectnumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_objectnumberKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_objectnumberKeyTyped

    private void ordernumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ordernumberKeyTyped
       char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_ordernumberKeyTyped

    private void retailPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retailPKeyTyped
       char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_retailPKeyTyped

    private void rpsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rpsKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_rpsKeyTyped

    private void spKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_spKeyTyped

    private void spsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spsKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_spsKeyTyped

    private void erKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_erKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_erKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        calculateEarning();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void spsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spsFocusLost
       calculateEarning();
    }//GEN-LAST:event_spsFocusLost

    private void getProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getProductActionPerformed
        String searchName = productid.getText();
        if(searchName == null || searchName.equals("")) return;
        try {
            fillTable(searchName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
       
        
    }//GEN-LAST:event_getProductActionPerformed

    private void mopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mopActionPerformed
       
    }//GEN-LAST:event_mopActionPerformed

    private void postalcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postalcodeKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACKSPACE) || c==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_postalcodeKeyTyped

    private void addexpbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addexpbtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) addexp.getModel();
        String name = "";
        int cost =0;
        dd = dateoforder.getDate();
       extra = new java.sql.Date(dd.getTime());
        if(dateoforder.getDate()==null) {
            JOptionPane.showMessageDialog(null,"Please select date of order first");
        } else {
            int te = Integer.parseInt(total.getText());
                    name = JOptionPane.showInputDialog("Enter Name");
                 cost  = Integer.parseInt(JOptionPane.showInputDialog("Enter Cost"));
            try {
                Connection con  = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/customerinfo", "root", "");
                Statement st = con.createStatement();
                
                Date v = dateoforder.getDate();
                String mysql = "INSERT INTO `customerinfo`.`other_expenses` (`dateoforder`,`expenses name`,`cost`,`total expense`) VALUES ('"+ extra +"','"+ name +"','"+ cost +"','"+ te +"')";
                st.executeUpdate(mysql);
                
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
        

        model.addRow(new Object[] {name,cost});
        int rows = addexp.getRowCount();
        int sum = 0;
        for(int i=0;i<rows;i++) {
            sum = sum + Integer.parseInt(addexp.getValueAt(i, 1).toString());
        }
        total.setText(Integer.toString(sum));
        }
    }//GEN-LAST:event_addexpbtnActionPerformed

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
            java.util.logging.Logger.getLogger(Place_an_order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Place_an_order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Place_an_order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Place_an_order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Place_an_order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable addexp;
    private javax.swing.JButton addexpbtn;
    private javax.swing.JTextField address;
    private javax.swing.JComboBox<String> auctionstorebox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton completed;
    private javax.swing.JTextField country;
    private javax.swing.JTextField customerid;
    private javax.swing.JTextField customername;
    private com.toedter.calendar.JDateChooser dateoforder;
    private com.toedter.calendar.JDateChooser dos_c;
    private com.toedter.calendar.JDateChooser dos_m;
    private javax.swing.JTextField email;
    private com.toedter.calendar.JDateChooser enddate;
    private javax.swing.JTextField er;
    private javax.swing.JButton getProduct;
    private javax.swing.JLabel imagePreview;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JComboBox<String> jcombo1;
    private javax.swing.JScrollPane jtable;
    private javax.swing.JComboBox<String> mop;
    private javax.swing.JLabel msg;
    private javax.swing.JTextField objectnumber;
    private javax.swing.JTextField ordernumber;
    private javax.swing.JRadioButton paid;
    private com.toedter.calendar.JDateChooser pd;
    private javax.swing.JRadioButton pending;
    private javax.swing.JComboBox<String> pfwa;
    private javax.swing.JTextField phonenumber;
    private javax.swing.JTextField postalcode;
    private javax.swing.JTextField productid;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel rec;
    private javax.swing.JTextField retailP;
    private javax.swing.JTextField rps;
    private javax.swing.JTextField sp;
    private javax.swing.JTextField sps;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JLabel sts;
    private javax.swing.JLabel tdisplay;
    private javax.swing.JLabel total;
    private javax.swing.JRadioButton unpaid;
    // End of variables declaration//GEN-END:variables
}
