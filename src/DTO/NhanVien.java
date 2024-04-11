package DTO;

public class NhanVien {
<<<<<<< HEAD
	private String username;
=======
	private int staff_id;
>>>>>>> f16468cb86e654859291bcaa54ff23841dc0722b
	private String full_name;
	private String email;
	private String phone_number;
	private String password;
	private int staff_status;
	private String account_id;
	
	public NhanVien() {}

<<<<<<< HEAD
	public NhanVien(String username, String full_name, String email, String phone_number, String password,
			int accountStatus, String position) {
		this.username = username;
=======
	public NhanVien(int staff_id, String full_name, String email, String phone_number, String password,
			int staff_status, String account_id) {
		this.staff_id = staff_id;
>>>>>>> f16468cb86e654859291bcaa54ff23841dc0722b
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.staff_status = staff_status;
		this.account_id = account_id;
	}
	
<<<<<<< HEAD
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
=======
	public int getStaffId() {
		return staff_id;
	}
	public void setStaffId(int staff_id) {
		this.staff_id = staff_id;
>>>>>>> f16468cb86e654859291bcaa54ff23841dc0722b
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

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
}
