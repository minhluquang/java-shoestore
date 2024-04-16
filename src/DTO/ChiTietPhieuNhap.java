package DTO;

public class ChiTietPhieuNhap {
	private int receiptdetail_id;
	private int product_id;
	private int quantity;
	private int receipt_id;
	private int input_price;

	public ChiTietPhieuNhap() {
		// TODO Auto-generated constructor stub
	}

	public int getReceiptdetail_id() {
		return receiptdetail_id;
	}

	public void setReceiptdetail_id(int receiptdetail_id) {
		this.receiptdetail_id = receiptdetail_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}

	public int getInput_price() {
		return input_price;
	}

	public void setInput_price(int input_price) {
		this.input_price = input_price;
	}

	public ChiTietPhieuNhap(int receiptdetail_id, int product_id, int quantity, int receipt_id, int input_price) {
		super();
		this.receiptdetail_id = receiptdetail_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.receipt_id = receipt_id;
		this.input_price = input_price;
	}

}
