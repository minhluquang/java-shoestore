package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.JButton;

import DTO.NhanVien;
import DTO.TaiKhoan;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import GUI.NhanVienGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiTietNhanVienGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtSoDienThoai;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtEmail;
	private JTextField txtMaNhanVien;
	private JComboBox cmbTrangThai;
	
	private NhanVien nv;
	private NhanVienGUI parentGUI;
	private JTextField txtTaiKhoan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietNhanVienGUI frame = new ChiTietNhanVienGUI(new NhanVien(), new NhanVienGUI());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChiTietNhanVienGUI(NhanVien nv, NhanVienGUI parentGUI) {
		this.nv = nv;
		this.parentGUI = parentGUI;
		
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết nhân viên không?", "Xác nhận đóng chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}
    	});
		
		int width = 380;
        int height = 600;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin nhân viên");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(200, 10));
		pnlRight.setBorder(null);
		pnlRight.setBackground(Color.WHITE);
		contentPane.add(pnlRight);
		pnlRight.setLayout(new BorderLayout(0, 10));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pnlRight.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin nhân viên");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.setBackground(new Color(36, 136, 203));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		panel.add(lblNewLabel_5_1);
		
		JPanel panel_3 = new JPanel();
		pnlRight.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6_2 = new JLabel("Mã nhân viên");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEnabled(false);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNhanVien.setColumns(10);
		panel_5.add(txtMaNhanVien);
		
		JLabel lblNewLabel_6 = new JLabel("Họ và tên");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHoTen.setColumns(10);
		panel_5.add(txtHoTen);
		
		JLabel lblNewLabel_6_3 = new JLabel("Số điện thoại");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setPreferredSize(new Dimension(100, 19));
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoDienThoai.setColumns(10);
		panel_5.add(txtSoDienThoai);
		
		JLabel lblNewLabel_6_3_1 = new JLabel("Email");
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1);
		
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(100, 19));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		panel_5.add(txtEmail);
		
		JLabel lblNewLabel_6_3_1_1 = new JLabel("Trạng thái");
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_1);
		
		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setFocusable(false);
		panel_5.add(cmbTrangThai);
		
		JLabel lblNewLabel_6_3_1_2 = new JLabel("Tài khoản");
		lblNewLabel_6_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_2);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setEnabled(false);
		txtTaiKhoan.setPreferredSize(new Dimension(100, 19));
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTaiKhoan.setColumns(10);
		panel_5.add(txtTaiKhoan);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		
		
		// ========= Xử lý lưu thông tin nhân viên =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinNhanVien();
			}
		});
		// ========= Xử lý lưu thông tin nhân viên =========
		
		
		
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setPreferredSize(new Dimension(100, 30));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(21, 155, 71));
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết nhân viên không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(220, 53, 69));
		panel_2.add(btnNewButton_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_3.add(panel_6, BorderLayout.EAST);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(Color.WHITE);
		panel_3.add(panel_6_1, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		pnlRight.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);
		
		// ========== Gắn giá trị tự động ==========
		if (nv != null) {
			xuLyTuDongGanGiaTri();
		}
	}
	
	public void xuLyTuDongGanGiaTri() {
		int staffId = nv.getStaffId();
		if (staffId == 0) {
			txtMaNhanVien.setText(Integer.toString(NhanVienBUS.generateIdNhanVien()));
			txtTaiKhoan.setEnabled(true);
			txtTaiKhoan.setEditable(true);
		} else {
			txtMaNhanVien.setText(Integer.toString(nv.getStaffId()));
		}
		
		txtHoTen.setText(nv.getFull_name());
		txtSoDienThoai.setText(nv.getPhone_number());
		txtEmail.setText(nv.getEmail());
		if (nv.getStaffStatus() == 1) {
			cmbTrangThai.setSelectedIndex(0);
		} else if (nv.getStaffStatus() == 0) {
			cmbTrangThai.setSelectedIndex(1);
		}
		
		txtTaiKhoan.setText(nv.getUsername());
	}

	public void xuLyLuuThongTinNhanVien() {
		int staffId = nv.getStaffId();
		String fullname = txtHoTen.getText();
		String phoneNumber = txtSoDienThoai.getText();
		String email = txtEmail.getText();
		String username = txtTaiKhoan.getText();
		
		int status = 0;
		if (cmbTrangThai.getSelectedIndex() == 0) {
			status = 1;
		} else if (cmbTrangThai.getSelectedIndex() == 1) {
			status = 0;
		}
		
		// Kiểm tra form có txt trống không, nếu có thì không cho đi tiếp
		if (fullname.trim().isEmpty() || phoneNumber.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty()) {
			String message = "Vui lòng nhập đầy đủ các trường:";
			message += "\n - Họ và tên";
			message += "\n - Email";
			message += "\n - Số điện thoại";
			message += "\n - Username";
			JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra lỗi ràng buộc
			String messageError = "Vui lòng nhập đúng định dạng: ";
			Boolean isError = false;
			
			String regexPhoneNumber = "^0\\d{9}$";
			String regexEmail = "^[a-zA-Z0-9.-_]+@[a-zA-Z0.9.-_]+\\.[a-zA-Z]{2,}$";
			String regexFullname = "^[a-zA-Z]+(\\s[a-zA-Z]+)+$";
			
			
			if (!fullname.matches(regexFullname)) {
				messageError += "\n - Họ và tên không dấu (ví dụ: Lu Quang Minh)";
				isError = true;
			}
			if (!phoneNumber.matches(regexPhoneNumber)) {
				messageError += "\n - Số điện thoại (ví dụ: 0931814480)";
				isError = true;
			} 
			if (!email.matches(regexEmail)) {
				messageError += "\n - Email (ví dụ: minhlq2911@sgu.edu.vn)";
				isError = true;
			}
			
			if (isError) {
				JOptionPane.showMessageDialog(null, messageError, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
				
			// Nếu tồn tại staff_id (tức: có nhân viên thì update)
			if (NhanVienBUS.isExistNhanVien(staffId)) {
				if (NhanVienBUS.updateNhanVien(staffId, fullname, email, phoneNumber, status, username)) {
					JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin nhân viên", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
					parentGUI.loadDanhSachNhanVien();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin nhân viên", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				TaiKhoan tk = TaiKhoanBUS.getDetailTaiKhoanByUsername(username);
				if (tk == null) {
					JOptionPane.showMessageDialog(null, "Hệ thống không tồn tại username: " + username, "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
				} else if (NhanVienBUS.isUsedAccountId(tk.getAccountId())) {
					JOptionPane.showMessageDialog(null, "Hệ thống có nhân viên sử dụng username: " + username, "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
				} else if (TaiKhoanBUS.isExistIdTaiKhoan(tk.getAccountId())) {
					// Nếu không có thì insert
					if (NhanVienBUS.insertNhanVien(fullname, email, phoneNumber, status, username)) {
							JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin nhân viên", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
							parentGUI.loadDanhSachNhanVien();
							dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin nhân viên", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}
