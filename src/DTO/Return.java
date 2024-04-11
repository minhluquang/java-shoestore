package DTO;

public class Return {
    private int return_id;
    private int product_id;
    private String date_return;
    private String reason;

    // Constructors
    public Return() {
    }

    public Return(int return_id, int product_id, String date_return, String reason) {
        this.return_id = return_id;
        this.product_id = product_id;
        this.date_return = date_return;
        this.reason = reason;
    }

    // Getters and setters
    public int getReturn_id() {
        return return_id;
    }

    public void setReturn_id(int return_id) {
        this.return_id = return_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
