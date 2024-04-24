package DTO;

public class Warranty {
	private int warrantyid;
	private int productid;
	private String startDate;
	private String endDate;
	private String warrantyDate;
	private String reason;
	private String warrantyStatus;
	
	public Warranty() {
		
	}

	public int getWarrantyid() {
		return warrantyid;
	}

	public void setWarrantyid(int warrantyid) {
		this.warrantyid = warrantyid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getWarrantyStatus() {
		return warrantyStatus;
	}

	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}
	
}
