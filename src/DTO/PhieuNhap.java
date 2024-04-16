package DTO;

public class PhieuNhap {
	private int receipt_id;
	private String goodsreceipt_name;
	private String date;
	private int total_price;
	private int supplier_id;
	private int staff_id;

	public PhieuNhap() {
		// TODO Auto-generated constructor stub
	}

	public PhieuNhap(int receipt_id, String goodsreceipt_name, String date, int total_price, int supplier_id,
			int staff_id) {
		super();
		this.receipt_id = receipt_id;
		this.goodsreceipt_name = goodsreceipt_name;
		this.date = date;
		this.total_price = total_price;
		this.supplier_id = supplier_id;
		this.staff_id = staff_id;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public int getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}

	public String getGoodsreceipt_name() {
		return goodsreceipt_name;
	}

	public PhieuNhap(int receipt_id, String goodsreceipt_name, String date, int total_price, int staff_id) {
		super();
		this.receipt_id = receipt_id;
		this.goodsreceipt_name = goodsreceipt_name;
		this.date = date;
		this.total_price = total_price;
		this.staff_id = staff_id;
	}

	public void setGoodsreceipt_name(String goodsreceipt_name) {
		this.goodsreceipt_name = goodsreceipt_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

}
