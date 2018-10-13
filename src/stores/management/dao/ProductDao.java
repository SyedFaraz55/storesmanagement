/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import stores.management.dbcon.DataBaseConnection;
import stores.management.entity.Product;

/**
 *
 * @author anis
 */
public class ProductDao {

    DataBaseConnection db = new DataBaseConnection();
    ProductFileDao pfDao = new ProductFileDao();

    public String add(Product p) {
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO product (name, discription, link, photo1, photo2, photo3) "
                    + "VALUE (?,?,?,?,?,?)");
            stmt.setString(1, p.getName());

            stmt.setString(2, p.getDiscription());

            stmt.setString(3, p.getLink());

            stmt.setLong(4, pfDao.add(p.getPhoto1()));
            stmt.setLong(5, pfDao.add(p.getPhoto2()));
            stmt.setLong(6, pfDao.add(p.getPhoto3()));
            if (stmt.executeUpdate() > 0) {
                return "Saved";
            }

        } catch (Exception e) {
            return e.getLocalizedMessage();

        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return "Already Exists";
    }

    public List<Product> all() {
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM product");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), pfDao.get(rs.getLong(5)), pfDao.get(rs.getLong(6)),
                        pfDao.get(rs.getLong(7)));
                list.add(p);
            }
        } catch (Exception e) {
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return list;
    }

    public Product get(long id) {
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Product p = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM product WHERE id=?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), pfDao.get(rs.getLong(5)), pfDao.get(rs.getLong(6)),
                        pfDao.get(rs.getLong(7)));

            }
        } catch (Exception e) {
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return p;
    }
    
    public List<Product> all(String name) {
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM product WHERE name LIKE ?");
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), pfDao.get(rs.getLong(5)), pfDao.get(rs.getLong(6)),
                        pfDao.get(rs.getLong(7)));
                list.add(p);
            }
        } catch (Exception e) {
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return list;
    }
    
    public String delete(long id) {
        //Delete files
        Product p = get(id);
        deleteFiles(p);
        //delete product
        PreparedStatement stmt = null;
        Connection con = db.getConnection();
        
        try {
            stmt = con.prepareStatement("DELETE FROM product WHERE id=?");
            stmt.setLong(1, id);
            if(stmt.executeUpdate() > 0) {
                return "Successfully Deleted";
            }
            
        } catch (Exception e) {
            return e.getLocalizedMessage();
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return null;
    }
    
    private void deleteFiles(Product p) {
        pfDao.delete(p.getPhoto1().getId());
        pfDao.delete(p.getPhoto2().getId());
        pfDao.delete(p.getPhoto3().getId());
    }
}
