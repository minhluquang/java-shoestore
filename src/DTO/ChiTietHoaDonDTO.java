package DTO;

public class ChiTietHoaDonDTO {
    private int productSerialId;
    private int billId;
    private int priceSingle;

    public ChiTietHoaDonDTO() {
    }
    public ChiTietHoaDonDTO(int productSerialId, int billId, int priceSingle) {
        this.productSerialId = productSerialId;
        this.billId = billId;
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
    public int getPriceSingle() {
        return priceSingle;
    }
    public void setPriceSingle(int priceSingle) {
        this.priceSingle = priceSingle;
    }
}
