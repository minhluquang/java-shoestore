package DTO;

public class Warranty {
	private int warrantyid;
	private int product_serial_id;
	private String warrantyDate;
	private String reason;
	private String active;
	private int status;
	
	public Warranty() {
		
	}
	
	
	public Warranty(int warrantyid, int product_serial_id, String warrantyDate,
	String reason,String active, int status) {
		this.warrantyid = warrantyid;
		this.product_serial_id = product_serial_id;
		this.warrantyDate = warrantyDate;
		this.reason = reason;
		this.status = status;
		this.active = active;
	}


	public int getWarrantyid() {
		return warrantyid;
	}

	public void setWarrantyid(int warrantyid) {
		this.warrantyid = warrantyid;
	}

	public int getProduct_serial_id() {
		return product_serial_id;
	}

	public void setProduct_serial_id(int product_serial_id) {
		this.product_serial_id = product_serial_id;
	}

	public String getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(String warrantyDate) {
		this.warrantyDate = warrantyDate;
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


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}
	
	
	
}
