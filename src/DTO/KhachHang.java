package DTO;

public class KhachHang {
	private int customerId;
	private String customerName;
	private String phoneNumber;
	private int status;
	
	public KhachHang() {}
	
	public KhachHang(int customerId, String customerName, String phoneNumber, int status) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCustomerId_Name(){
		return this.getCustomerId()+" "+this.getCustomerName();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
