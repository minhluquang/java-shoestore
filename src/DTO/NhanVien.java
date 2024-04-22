package DTO;

public class NhanVien {
	private int staff_id;
	private String full_name;
	private String email;
	private String phone_number;
	private String password;
	private int staff_status;
	private String username;
	private TaiKhoan taiKhoan;
	private int account_id;
	
	public NhanVien() {}


	public NhanVien(int staff_id, String full_name, String email, String phone_number, String password,
			int staff_status, String username) {
		this.staff_id = staff_id;
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.staff_status = staff_status;
		this.username = username;
	}
	

    public NhanVien(int staff_id, String full_name, String email, String phone_number, String password,
                    int staff_status, String username, TaiKhoan taiKhoan) {
        this.staff_id = staff_id;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.staff_status = staff_status;
        this.username = username;
        this.taiKhoan = taiKhoan; // Khởi tạo trường TaiKhoan
        this.account_id = taiKhoan.getAccountId(); // Lấy accountId từ đối tượng TaiKhoan
    }
	public int getAccount_id() {
		return account_id;
	}


	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}


	public int getStaffId() {
		return staff_id;
	}
	public void setStaffId(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStaffStatus() {
		return staff_status;
	}
	public void setStaffStatus(int staff_status) {
		this.staff_status = staff_status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
