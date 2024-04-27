package DTO;

public class HangDTO {
    private int brand_id;
    private String brand_name;
    private boolean status;

    // Constructor
    public HangDTO(int brand_id, String brand_name, boolean status) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.status = status;
    }

    public HangDTO() {
    }

    // Getters and Setters
    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
