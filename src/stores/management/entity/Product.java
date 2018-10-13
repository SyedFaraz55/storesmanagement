/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management.entity;

/**
 *
 * @author anis
 */
public class Product {
    private long id;
    private String name;
    private String discription;
    private String link;
    private ProductFile photo1;
    private ProductFile photo2;
    private ProductFile photo3;

    public Product(long id, String name, String discription, String link, ProductFile photo1, ProductFile photo2, ProductFile photo3) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.link = link;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public Product(String name, String discription, String link, ProductFile photo1, ProductFile photo2, ProductFile photo3) {
        this.name = name;
        this.discription = discription;
        this.link = link;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ProductFile getPhoto1() {
        return photo1;
    }

    public void setPhoto1(ProductFile photo1) {
        this.photo1 = photo1;
    }

    public ProductFile getPhoto2() {
        return photo2;
    }

    public void setPhoto2(ProductFile photo2) {
        this.photo2 = photo2;
    }

    public ProductFile getPhoto3() {
        return photo3;
    }

    public void setPhoto3(ProductFile photo3) {
        this.photo3 = photo3;
    }
    
    
}
