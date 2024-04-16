package DTO;

public class NhaCungCap {
	private int supplier_id;
	private String supplier_name;
	private String supplier_addresss;

	public NhaCungCap(int supplier_id, String supplier_name, String supplier_addresss) {
		this.supplier_id = supplier_id;
		this.supplier_name = supplier_name;
		this.supplier_addresss = supplier_addresss;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_addresss() {
		return supplier_addresss;
	}

	public void setSupplier_addresss(String supplier_addresss) {
		this.supplier_addresss = supplier_addresss;
	}

	public NhaCungCap() {
		// TODO Auto-generated constructor stub
	}

}
