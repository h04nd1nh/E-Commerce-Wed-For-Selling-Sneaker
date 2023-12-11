/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class ProductSize {
    private int ProductID;
    private int SizeID;
    
    public ProductSize() {
        
    }
    
    public ProductSize(int ProductID, int SizeID) {
        this.ProductID = ProductID;
        this.SizeID = SizeID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }
    
    
}
