/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class ProductRate {
    private int ProductRateID;
    private int ProductID;
    private int OneStar;
    private int TwoStar;
    private int ThreeStar;
    private int FourStar;
    private int FiveStar;

    public ProductRate() {
    }

    public ProductRate(int ProductRateID, int ProductID, int OneStar, int TwoStar, int ThreeStar, int FourStar, int FiveStar) {
        this.ProductRateID = ProductRateID;
        this.ProductID = ProductID;
        this.OneStar = OneStar;
        this.TwoStar = TwoStar;
        this.ThreeStar = ThreeStar;
        this.FourStar = FourStar;
        this.FiveStar = FiveStar;
    }

    public int getProductRateID() {
        return ProductRateID;
    }

    public void setProductRateID(int ProductRateID) {
        this.ProductRateID = ProductRateID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getOneStar() {
        
        return OneStar;
    }

    public void setOneStar(int OneStar) {
        this.OneStar = OneStar;
    }

    public int getTwoStar() {
        return TwoStar;
    }

    public void setTwoStar(int TwoStar) {
        this.TwoStar = TwoStar;
    }

    public int getThreeStar() {
        return ThreeStar;
    }

    public void setThreeStar(int ThreeStar) {
        this.ThreeStar = ThreeStar;
    }

    public int getFourStar() {
        return FourStar;
    }

    public void setFourStar(int FourStar) {
        this.FourStar = FourStar;
    }

    public int getFiveStar() {
        return FiveStar;
    }

    public void setFiveStar(int FiveStar) {
        this.FiveStar = FiveStar;
    }
    
    public int getRateCount() {
        return OneStar + TwoStar + ThreeStar + FourStar + FiveStar;
    }

    @Override
    public String toString() {
        return "ProductRate{" + "ProductRateID=" + ProductRateID + ", ProductID=" + ProductID + ", OneStar=" + OneStar + ", TwoStar=" + TwoStar + ", ThreeStar=" + ThreeStar + ", FourStar=" + FourStar + ", FiveStar=" + FiveStar + '}';
    }
    
    
    
    
}
