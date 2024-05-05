package DTO;

import java.sql.Date;

public class HoaDonDTO {
    private int billId;
    private int staffId;
    private Date date;
    private int totalPrice;
    private int customerId;
    private String discountCode;

    public HoaDonDTO(int billId, int staffId, Date date, int totalPrice, int customerId, String discountCode) {
        this.billId = billId;
        this.staffId = staffId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.discountCode = discountCode;
    }
    public HoaDonDTO() {
    }
    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public int getStaffId() {
        return staffId;
    }
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getDiscountCode() {
        return discountCode;
    }
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
