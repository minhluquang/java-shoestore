package DTO;

public class NhanVien {
	private int account_id;
	private String full_name;
	private String email;
	private String phone_number;
	private String password;
	private int accountStatus;
	private String position;
	
	public NhanVien() {}

	public NhanVien(int account_id, String full_name, String email, String phone_number, String password,
			int accountStatus, String position) {
		this.account_id = account_id;
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.accountStatus = accountStatus;
		this.position = position;
	}
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
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
	public int getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
