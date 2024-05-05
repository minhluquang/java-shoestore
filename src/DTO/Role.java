package DTO;

public class Role {
	private int role_id;
	private String role_name;
	private String role_tab_name;
	public Role() {
		
	}
	public Role(int role_id, String role_name, String role_tab_name) {
		this.role_id = role_id;
		this.role_name = role_name;
		this.role_tab_name = role_tab_name;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_tab_name() {
		return role_tab_name;
	}
	public void setRole_tab_name(String role_tab_name) {
		this.role_tab_name = role_tab_name;
	}
		
}
