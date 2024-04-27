/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MSI
 */
public class SanPhamDTO {
    private int product_id;
    private int category_id;
    private int brand_id;
    private String product_name;
    private int output_price;
    private String country;
    private int year_of_product;
    private int discount_percent;
    private String image_path;
    private int quantity;
    private boolean status;

    // Constructor
    public SanPhamDTO(int product_id, int category_id, int brand_id, String product_name, int output_price, String country, int year_of_product, int discount_percent, String image_path, int quantity, boolean status) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.product_name = product_name;
        this.output_price = output_price;
        this.country = country;
        this.year_of_product = year_of_product;
        this.discount_percent = discount_percent;
        this.image_path = image_path;
        this.quantity = quantity;
        this.status = status;
    }

    public SanPhamDTO() {
    }

    // Getters and Setters
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getOutput_price() {
        return output_price;
    }

    public void setOutput_price(int output_price) {
        this.output_price = output_price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear_of_product() {
        return year_of_product;
    }

    public void setYear_of_product(int year_of_product) {
        this.year_of_product = year_of_product;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
