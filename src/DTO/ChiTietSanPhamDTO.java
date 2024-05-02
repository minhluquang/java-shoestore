package DTO;

public class ChiTietSanPhamDTO {
    private int productSerialId;
    private int productId;
    private boolean sold;
    public ChiTietSanPhamDTO() {
    }
    
    public ChiTietSanPhamDTO(int productSerialId, int productId, boolean sold) {
        this.productSerialId = productSerialId;
        this.productId = productId;
        this.sold = sold;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getProductSerialId() {
        return productSerialId;
    }
    public void setProductSerialId(int productSerialId) {
        this.productSerialId = productSerialId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

}
