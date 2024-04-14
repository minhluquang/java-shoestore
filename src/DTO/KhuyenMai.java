package DTO;

public class KhuyenMai {
	private String discount_code;
	private int condition_value;
	private String discount;
	private String start_date;
	private String end_date;
	private String active;
	
	// Constructors
    public KhuyenMai() {
    }

	public KhuyenMai(String discount_code, int condition_value, String discount, String start_date, String end_date,
			String active) {
		this.discount_code = discount_code;
		this.condition_value = condition_value;
		this.discount = discount;
		this.start_date = start_date;
		this.end_date = end_date;
		this.active = active;
	}
	// Getters and setters

	public String getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(String discount_code) {
		this.discount_code = discount_code;
	}

	public int getConditionValue() {
		return condition_value;
	}

	public void setConditionValue(int condition_value) {
		this.condition_value = condition_value;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}
