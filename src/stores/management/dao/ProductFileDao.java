/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management.dao;

import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import stores.management.dbcon.DataBaseConnection;
import stores.management.entity.ProductFile;

/**
 *
 * @author anis
 */
public class ProductFileDao {
    DataBaseConnection db = new DataBaseConnection();
    
    public long add(ProductFile p) {
        PreparedStatement stmt = null;
        Connection con = db.getConnection();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("INSERT INTO files (file) "
            + "VALUE (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setBlob(1, p.getPhoto());
            if(stmt.executeUpdate() > 0) {
                rs = stmt.getGeneratedKeys();
                if(rs.next())
                    return rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return -1;
    }
    
    public ProductFile get(long id) {
        PreparedStatement stmt = null;
        Connection con = db.getConnection();
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM files WHERE id=?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProductFile(rs.getLong(1), rs.getBinaryStream(2));
            }
        } catch (Exception e) {
        } finally {
            db.returnResources(stmt, null);
            db.returnConnection(con);
        }
        return null;
    }
    
    public String delete(long id) {
        PreparedStatement stmt = null;
        Connection con = db.getConnection();
        
        try {
            stmt = con.prepareStatement("DELETE FROM files WHERE id=?");
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
}
