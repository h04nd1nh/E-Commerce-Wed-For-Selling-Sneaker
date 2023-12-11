/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class CartItem {
    private int ProductID;
    private String ProductName;
    private int ColorID;
    private String Color;
    private int SizeID;
    private int Size;
    private int Quantity;
    private String Image;
    private float Sale;

    public CartItem() {
    }

    public CartItem(int ProductID,String ProductName, int ColorID, String Color, int SizeID, int Size, int Quantity,String Image,float Sale) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ColorID = ColorID;
        this.Color = Color;
        this.SizeID = SizeID;
        this.Size = Size;
        this.Quantity = Quantity;
        this.Image = Image;
        this.Sale = Sale;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getColorID() {
        return ColorID;
    }

    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public float getSale() {
        return Sale;
    }

    public void setSale(float Sale) {
        this.Sale = Sale;
    }

    
    
}
