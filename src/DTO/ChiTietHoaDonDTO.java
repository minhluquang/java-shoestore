package DTO;

public class ChiTietHoaDonDTO {
    private int productSerialId;
    private int billId;
    private int quantity;
    private int priceSingle;

    public ChiTietHoaDonDTO() {
    }
    public ChiTietHoaDonDTO(int productSerialId, int billId, int quantity, int priceSingle) {
        this.productSerialId = productSerialId;
        this.billId = billId;
        this.quantity = quantity;
        this.priceSingle = priceSingle;
    }    
    public int getProductSerialId() {
        return productSerialId;
    }
    public void setProductSerialId(int productSerialId) {
        this.productSerialId = productSerialId;
    }
    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getPriceSingle() {
        return priceSingle;
    }
    public void setPriceSingle(int priceSingle) {
        this.priceSingle = priceSingle;
    }
}
