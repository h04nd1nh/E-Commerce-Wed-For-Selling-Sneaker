/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.*;
/**
 *
 * @author hoand
 */
public class Product {
    private int ProductID;
    private String ProductName;
    private float Price;
    private float Sale;
    private String Image1;
    private String Image2;
    private String Image3;
    private int BrandID;
    private String BrandName;
    private Boolean Status;
    private String Description;
    
    
    public Product() {
        
    }

    public Product(int ProductID, String ProductName, float Price, float Sale, String Image1, String Image2, String Image3, int BrandID, String BrandName, Boolean Status, String Description) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Sale = Sale;
        this.Image1 = Image1;
        this.Image2 = Image2;
        this.Image3 = Image3;
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.Status = Status;
        this.Description = Description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public float getSale() {
        return Sale;
    }

    public void setSale(float Sale) {
        this.Sale = Sale;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String Image1) {
        this.Image1 = Image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String Image2) {
        this.Image2 = Image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String Image3) {
        this.Image3 = Image3;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public int GetReviewCount() {
        ProductDAO ProductDAO = new ProductDAO();
        int ReviewCount = ProductDAO.getProductReviewCount(ProductID);
        return ReviewCount;
    }
    
    public int getProductRateAverage() {
        ProductDAO ProductDAO = new ProductDAO();
        return ProductDAO.getProductRateAverageByProductID(ProductID);
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", Price=" + Price + ", Sale=" + Sale + ", Image1=" + Image1 + ", Image2=" + Image2 + ", Image3=" + Image3 + ", BrandID=" + BrandID + ", BrandName=" + BrandName + ", Status=" + Status + ", Description=" + Description + '}';
    }
    
    
    
    
}
