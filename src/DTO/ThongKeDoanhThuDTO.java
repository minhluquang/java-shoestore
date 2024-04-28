package DTO;

public class ThongKeDoanhThuDTO {

		String thoigian; // nam, thang, ngay
		int slHoaDon;
		int slDonNhap;
	    long von;
	    long doanhthu;
	    long loinhuan;

	    public ThongKeDoanhThuDTO() {
	    }
	    
	    public ThongKeDoanhThuDTO(String thoigian, long von, long doanhthu, long loinhuan, int slHoaDon, int slDonNhap) {
	        this.thoigian = thoigian;
	        this.von = von;
	        this.doanhthu = doanhthu;
	        this.loinhuan = loinhuan;
	        this.slDonNhap = slDonNhap;
	        this.slHoaDon = slHoaDon;
	    }
	    
	    public int getSlHoaDon() {
			return slHoaDon;
		}

		public void setSlHoaDon(int slHoaDon) {
			this.slHoaDon = slHoaDon;
		}

		public int getSlDonNhap() {
			return slDonNhap;
		}

		public void setSlDonNhap(int slDonNhap) {
			this.slDonNhap = slDonNhap;
		}

		public String getThoigian() {
	        return thoigian;
	    }

	    public void setThoigian(String thoigian) {
	        this.thoigian = thoigian;
	    }

	    public long getVon() {
	        return von;
	    }

	    public void setVon(long von) {
	        this.von = von;
	    }

	    public long getDoanhthu() {
	        return doanhthu;
	    }

	    public void setDoanhthu(long doanhthu) {
	        this.doanhthu = doanhthu;
	    }

	    public long getLoinhuan() {
	        return loinhuan;
	    }

	    public void setLoinhuan(long loinhuan) {
	        this.loinhuan = loinhuan;
	    }
}
