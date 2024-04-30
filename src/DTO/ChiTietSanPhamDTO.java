package DTO;

public class ChiTietSanPhamDTO {
    private int productSerialId;
    private int productId;
    public ChiTietSanPhamDTO() {
    }
    public ChiTietSanPhamDTO(int productSerialId, int productId) {
        this.productSerialId = productSerialId;
        this.productId = productId;
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
