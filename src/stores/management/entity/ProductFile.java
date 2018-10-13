/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management.entity;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author anis
 */
public class ProductFile {
    private long id;
    private InputStream photo;

    public ProductFile(long id, InputStream photo) {
        this.id = id;
        this.photo = photo;
    }

    public ProductFile(InputStream photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }
    
    
}
