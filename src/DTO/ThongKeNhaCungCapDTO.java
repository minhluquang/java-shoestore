package DTO;

public class ThongKeNhaCungCapDTO {
	private int stt;
	private int maNCC;
	private String tenNCC;
	private int SL_Nhap;
	private long tongTien;
	
	
	
	public ThongKeNhaCungCapDTO() {
		
	}
	public ThongKeNhaCungCapDTO(int stt, int maNCC, String tenNCC, int sL_Nhap, long tongTien) {
		
		this.stt = stt;
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		SL_Nhap = sL_Nhap;
		this.tongTien = tongTien;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public int getSL_Nhap() {
		return SL_Nhap;
	}
	public void setSL_Nhap(int sL_Nhap) {
		SL_Nhap = sL_Nhap;
	}
	public long getTongTien() {
		return tongTien;
	}
	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}
}
