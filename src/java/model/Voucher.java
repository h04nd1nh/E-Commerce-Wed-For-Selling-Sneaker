/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class Voucher {
    private int VoucherID;
    private String VoucherCode;
    private int Discount;
    private String StartDate;
    private String Expiry;

    public Voucher() {
    }

    public Voucher(int VoucherID, String VoucherCode, int Discount, String StartDate, String Expiry) {
        this.VoucherID = VoucherID;
        this.VoucherCode = VoucherCode;
        this.Discount = Discount;
        this.StartDate = StartDate;
        this.Expiry = Expiry;
    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int VoucherID) {
        this.VoucherID = VoucherID;
    }

    public String getVoucherCode() {
        return VoucherCode;
    }

    public void setVoucherCode(String VoucherCode) {
        this.VoucherCode = VoucherCode;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String Expiry) {
        this.Expiry = Expiry;
    }
    
    
}
