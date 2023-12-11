/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class Order {
    private String OrderID;
    private int UserID;
    private String DateCreate;
    private float Total;
    private String Address;
    private String Status;
    private String Note;
    private String FullName;
    private String Email;
    private String Phone;
    private int VoucherID;
    private String VoucherCode;
    private float PaymentAmount;

    public Order() {
    }

    public Order(String OrderID, int UserID, String DateCreate, float Total, String Address, String Status, String Note, String FullName, String Email, String Phone, int VoucherID, String VoucherCode, float PaymentAmount) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.DateCreate = DateCreate;
        this.Total = Total;
        this.Address = Address;
        this.Status = Status;
        this.Note = Note;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.VoucherID = VoucherID;
        this.VoucherCode = VoucherCode;
        this.PaymentAmount = PaymentAmount;
    }

    

    

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String DateCreate) {
        this.DateCreate = DateCreate;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
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

    public float getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(float PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", UserID=" + UserID + ", DateCreate=" + DateCreate + ", Total=" + Total + ", Address=" + Address + ", Status=" + Status + ", Note=" + Note + ", FullName=" + FullName + ", Email=" + Email + ", Phone=" + Phone + ", VoucherID=" + VoucherID + ", VoucherCode=" + VoucherCode + ", PaymentAmount=" + PaymentAmount + '}';
    }
    
    
}
