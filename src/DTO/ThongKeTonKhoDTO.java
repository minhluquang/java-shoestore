package DTO;

public class ThongKeTonKhoDTO {
	private int stt;
	private int maSP;
	private String tenSP;
	private int slNhap;
	private int slXuat;
	private int tonKho;
	
	
	public ThongKeTonKhoDTO () {}
	
	public ThongKeTonKhoDTO(int stt, int maSP, String tenSP, int slNhap, int slXuat, int tonKho) {
		
		this.stt = stt;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.slNhap = slNhap;
		this.slXuat = slXuat;
		this.tonKho = tonKho;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getMaSP() {
		return maSP;
	}
	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getSlNhap() {
		return slNhap;
	}
	public void setSlNhap(int slNhap) {
		this.slNhap = slNhap;
	}
	public int getSlXuat() {
		return slXuat;
	}
	public void setSlXuat(int slXuat) {
		this.slXuat = slXuat;
	}
	public int getTonKho() {
		return tonKho;
	}
	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
}
