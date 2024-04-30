package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import BUS.ChiTietQuyenBUS;
import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.Quyen;
import DTO.TaiKhoan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;

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

import DTO.TaiKhoan;
import GUI.TaiKhoanGUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;

public class ChiTietTaiKhoanGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private TaiKhoan tk;
	private TaiKhoanGUI parentGUI;
	private JTextField txtMaTaiKhoan;
	private JTextField txtUsername;
	private JPasswordField txtMatKhauHienTai;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtNhapLaiMatKhauMoi;
	private JComboBox cmbTrangThai;
	private JComboBox cmbChucVu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietTaiKhoanGUI frame = new ChiTietTaiKhoanGUI(new TaiKhoan(), new TaiKhoanGUI());
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
	public ChiTietTaiKhoanGUI(TaiKhoan tk, TaiKhoanGUI parentGUI) {
		this.tk = tk;
		this.parentGUI = parentGUI;
		
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng sửa tài khoản không?", "Xác nhận đóng sửa tài khoản", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}
    	});
		
		int width = 380;
        int height = 500;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin tài khoản");
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
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin tài khoản");
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBorder(null);
		panel_4.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Thông tin", null, panel_5, null);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6_2 = new JLabel("Mã tài khoản");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		txtMaTaiKhoan = new JTextField();
		txtMaTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaTaiKhoan.setEnabled(false);
		txtMaTaiKhoan.setEditable(false);
		txtMaTaiKhoan.setColumns(10);
		panel_5.add(txtMaTaiKhoan);
		
		JLabel lblNewLabel_6_3 = new JLabel("Username");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(100, 19));
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		panel_5.add(txtUsername);
		
		JLabel lblNewLabel_6_3_1 = new JLabel("Chức vụ");
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1);
		
		cmbChucVu = new JComboBox();
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Admin"}));
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbChucVu.setFocusable(false);
		panel_5.add(cmbChucVu);
		
		JLabel lblNewLabel_6_3_1_1 = new JLabel("Trạng thái");
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_1);
		
		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setFocusable(false);
		panel_5.add(cmbTrangThai);
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinTaiKhoan();
			}
		});
		btnLuu.setPreferredSize(new Dimension(100, 30));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLuu.setFocusable(false);
		btnLuu.setBorder(null);
		btnLuu.setBackground(new Color(21, 155, 71));
		panel_2.add(btnLuu);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết khách hàng không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết khách hàng", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
			}
		});
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(220, 53, 69));
		panel_2.add(btnNewButton_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Đổi mật khẩu", null, panel_7, null);
		panel_7.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Mật khẩu hiện tại");
		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(lblNewLabel_6_2_1);
		
		txtMatKhauHienTai = new JPasswordField();
		panel_7.add(txtMatKhauHienTai);
		
		JLabel lblNewLabel_6_3_2 = new JLabel("Nhập mật khẩu mới");
		lblNewLabel_6_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(lblNewLabel_6_3_2);
		
		txtMatKhauMoi = new JPasswordField();
		panel_7.add(txtMatKhauMoi);
		
		JLabel lblNewLabel_6_3_2_1 = new JLabel("Nhập lại mật khẩu mới");
		lblNewLabel_6_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(lblNewLabel_6_3_2_1);
		
		txtNhapLaiMatKhauMoi = new JPasswordField();
		panel_7.add(txtNhapLaiMatKhauMoi);
		
		JLabel lblNewLabel_7_1_1_1_1 = new JLabel("");
		panel_7.add(lblNewLabel_7_1_1_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_7.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyDoiMatKhauTaiKhoan();
			}
		});
		btnDoiMatKhau.setPreferredSize(new Dimension(100, 30));
		btnDoiMatKhau.setForeground(Color.WHITE);
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDoiMatKhau.setFocusable(false);
		btnDoiMatKhau.setBorder(null);
		btnDoiMatKhau.setBackground(new Color(21, 155, 71));
		panel_2_1.add(btnDoiMatKhau);
		
		JButton btnNewButton_1_1 = new JButton("Huỷ bỏ");
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa tài khoản không?", "Xác nhận huỷ bỏ chỉnh sửa tài khoản", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
			}
		});
		btnNewButton_1_1.setPreferredSize(new Dimension(100, 30));
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setFocusable(false);
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(220, 53, 69));
		panel_2_1.add(btnNewButton_1_1);
		
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
		if (tk != null) {
			xuLyTuDongGanGiaTri();	
		}
	}

	public void xuLyTuDongGanGiaTri() {
		if (tk.getAccountId() == 0) {
			txtMaTaiKhoan.setText(Integer.toString(TaiKhoanBUS.generateIdTaiKhoan()));
		} else {
			txtMaTaiKhoan.setText(Integer.toString(tk.getAccountId()));
		}
		
		txtUsername.setText(tk.getUsername());
		
		if (tk.getPosition() != null) {
		    if (tk.getPosition().equals("admin")) {
		        cmbChucVu.setSelectedIndex(1);
		    } else if (tk.getPosition().equals("staff")) {
		        cmbChucVu.setSelectedIndex(0);
		    }
		}
		
		if (tk.getAccountStatus() == 1) {
			cmbTrangThai.setSelectedIndex(0);
		} else if (tk.getAccountStatus() == 0) {
			cmbTrangThai.setSelectedIndex(1);
		}
	}
	
	public void xuLyLuuThongTinTaiKhoan() {
		int accountId = Integer.parseInt(txtMaTaiKhoan.getText());
		String username = txtUsername.getText().trim();
		
		int status = 0;
		if (cmbTrangThai.getSelectedItem().equals("Hoạt động")) {
			status = 1;
		} else if (cmbTrangThai.getSelectedItem().equals("Ngưng hoạt động")) {
			status = 0;
		}
		
		String position = "";
		if (cmbChucVu.getSelectedItem().equals("Nhân viên")) {
			position = "staff";
		} else if (cmbChucVu.getSelectedItem().equals("Admin")) {
			position = "admin";
		}
		
		
		if (username.isEmpty()) {
			String message = "Vui lòng nhập đầy đủ các trường:";
			message += "\n - Username";
			JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra lỗi ràng buộc
			String messageError = "Vui lòng nhập đúng định dạng: ";
			Boolean isError = false;
			
			String regexUsername = "^[a-zA-Z][a-zA-Z0-9]{7,}$";
			
			if (!username.matches(regexUsername)) {
				messageError += "\n - Username phải có ít nhất 8 ký tự";
				messageError += "\n - Username bắt đầu phải là ký tự chữ";
				isError = true;	
			}
			
			if (isError) {
				JOptionPane.showMessageDialog(null, messageError, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (TaiKhoanBUS.isExistUsername(username, accountId)) {
				JOptionPane.showMessageDialog(null, "Hệ thống đã tồn tại username: " + username, "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (TaiKhoanBUS.isExistIdTaiKhoan(accountId)) {
					if (TaiKhoanBUS.updateTaiKhoan(accountId, username, status, position)) {
						if (position.equalsIgnoreCase("admin")) {
							 ChiTietQuyenBUS.deleteTatCaQuyenCuaTaiKhoanBangId(accountId);
							 autoSetFullQuyen(accountId);
						}
						JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin tài khoản", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
						parentGUI.loadDanhSachTaiKhoan();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin tài khoản", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
	
	// Xử lý đổi mật khẩu
	public void xuLyDoiMatKhauTaiKhoan() {
		int accountId = Integer.parseInt(txtMaTaiKhoan.getText());
		char[] currentPassword = txtMatKhauHienTai.getPassword();
		char[] newPassowrd = txtMatKhauMoi.getPassword();
		char[] newConfirmPassword = txtNhapLaiMatKhauMoi.getPassword();
		
		String matKhauHienTai = new String(currentPassword); 
		String matKhauMoi = new String(newPassowrd);
		String nhapLaiMatKhauMoi = new String(newConfirmPassword);
	
		
		if (TaiKhoanBUS.isTruePassword(accountId, matKhauHienTai)) {
			if (matKhauMoi.length() < 6) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới từ 6 kí tự trở lên!", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
			} else if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới và nhập lại mật khẩu phải trùng nhau!", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// Cập nhật mật khẩu
				if (TaiKhoanBUS.updatePasswordByAccountId(accountId, matKhauMoi)) {
					JOptionPane.showMessageDialog(null, "Hệ thống đổi mật khẩu thành công", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Hệ thống đổi mật khẩu thất bại, vui lòng thử lại sau!", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Mật khẩu hiện tại mà bạn nhập không chính xác!", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Tự động gán hết quyền cho tài khoản
	public boolean autoSetFullQuyen(int accountId) {
		ArrayList<Quyen> dsq = QuyenBUS.getDanhSachQuyen();
		boolean success = true;
		for (Quyen quyen : dsq) {
			if (!ChiTietQuyenBUS.insertQuyenVaoTaiKhoan(quyen.getRoleId(), accountId)) {
				success = false;
			}
		}
		return success;
	}
}
