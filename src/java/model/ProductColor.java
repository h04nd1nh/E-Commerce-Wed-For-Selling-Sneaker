/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class ProductColor {
    private int ProductID;
    private int ColoriD;
    
    public ProductColor() {
        
    }
    
    public ProductColor(int ProductID, int ColorID) {
        this.ProductID = ProductID;
        this.ColoriD = ColorID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getColoriD() {
        return ColoriD;
    }

    public void setColoriD(int ColoriD) {
        this.ColoriD = ColoriD;
    }
    
    
}
