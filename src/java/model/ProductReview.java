/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class ProductReview {
    private int ProductReviewID;
    private int UserID;
    private int ProductID;
    private String FullName;
    private String Avatar;
    private String ReviewDate;
    private String ReviewContent;

    public ProductReview() {
    }

    public ProductReview(int ProductReviewID, int UserID, int ProductID, String FullName, String Avatar, String ReviewDate, String ReviewContent) {
        this.ProductReviewID = ProductReviewID;
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.FullName = FullName;
        this.Avatar = Avatar;
        this.ReviewDate = ReviewDate;
        this.ReviewContent = ReviewContent;
    }

    public int getProductReviewID() {
        return ProductReviewID;
    }

    public void setProductReviewID(int ProductReviewID) {
        this.ProductReviewID = ProductReviewID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(String ReviewDate) {
        this.ReviewDate = ReviewDate;
    }

    public String getReviewContent() {
        return ReviewContent;
    }

    public void setReviewContent(String ReviewContent) {
        this.ReviewContent = ReviewContent;
    }
    
    
}
