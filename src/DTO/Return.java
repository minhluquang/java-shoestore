package DTO;

import java.util.Date;

public class Return {
    private int return_id;
    private int product_serial_id;
    private String date_return;
    private String reason;
    private int status;
    // Constructors
    public Return() {
    }

    public Return(int return_id, int product_serial_id, String date_return, String reason, int status) {
        this.return_id = return_id;
        this.product_serial_id = product_serial_id;
        this.date_return = date_return;
        this.reason = reason;
        this.status = status;
    }

	public int getReturn_id() {
		return return_id;
	}

	public void setReturn_id(int return_id) {
		this.return_id = return_id;
	}

	public int getProduct_serial_id() {
		return product_serial_id;
	}

	public void setProduct_serial_id(int product_serial_id) {
		this.product_serial_id = product_serial_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
