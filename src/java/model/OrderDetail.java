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
public class OrderDetail {

    private int DetailID;
    private String OrderID;
    private int ProductID;
    private int Quantity;
    private float Price;
    private int SizeID;
    private int ColorID;
    private int Rate;

    public OrderDetail() {
    }

    public OrderDetail(int DetailID, String OrderID, int ProductID, int Quantity, float Price, int SizeID, int ColorID, int Rate) {
        this.DetailID = DetailID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.SizeID = SizeID;
        this.ColorID = ColorID;
        this.Rate = Rate;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int DetailID) {
        this.DetailID = DetailID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getColorID() {
        return ColorID;
    }

    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public int getSize() {
        ProductDAO dao = new ProductDAO();
        return dao.getSizeBySizeID(SizeID);
    }

    public String getColor() {
        ProductDAO dao = new ProductDAO();
        return dao.getColorByColorID(ColorID);
    }

    public Product getProduct() {
        ProductDAO dao = new ProductDAO();
        return dao.getProductByID(ProductID);
    }
}
