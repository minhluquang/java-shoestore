package DTO;

public class KhuyenMai {
	private String discount_code;
	private int status;
	private int discount_value;
	private String type;
	private String start_date;
	private String end_date;
	
	// Constructors
    public KhuyenMai() {
    }

	public KhuyenMai(String discount_code, int status, int discount_value, String start_date, String end_date,String type) {
		this.discount_code = discount_code;
		this.discount_value = discount_value;
		this.type = type;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
	}

	public String getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(String discount_code) {
		this.discount_code = discount_code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(int discount_value) {
		this.discount_value = discount_value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
	
}
