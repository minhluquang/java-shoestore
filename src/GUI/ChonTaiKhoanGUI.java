package GUI;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Message;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JSplitPane;
import javax.swing.DefaultComboBoxModel;

public class ChonTaiKhoanGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTimKiem;
	
	private DefaultTableModel dtm;
	private JTextField txtMaTaiKhoan;
	private JTextField txtUsername;
	private JTable table;
	private JTextField txtMaNhanVien;
	private JComboBox cmbChucVu;
	private JComboBox cmbTrangThai;
	
	private static TaiKhoanGUI tKhoanGUI;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChonTaiKhoanGUI frame = new ChonTaiKhoanGUI(tKhoanGUI);
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
	public ChonTaiKhoanGUI(TaiKhoanGUI tKhoanGUI) {
		this.tKhoanGUI = tKhoanGUI;
		
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ thêm tài khoản không?", "Xác nhận huỷ thêm tài khoản", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}

    	});
		
		int width = 850;
		int height = 480;
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);
		setLocationRelativeTo(null);
		setTitle("Chọn tài khoản");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlHeader = new JPanel();
		contentPane.add(pnlHeader, BorderLayout.NORTH);
		pnlHeader.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		pnlHeader.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(20, 5));
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		txtTimKiem = new JTextField();
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				xuLyTimKiem(txtTimKiem.getText());
			}
		});
		txtTimKiem.setPreferredSize(new Dimension(7, 30));
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		panel.add(txtTimKiem, BorderLayout.CENTER);
		
		JPanel panel_1_2 = new JPanel();
		panel.add(panel_1_2, BorderLayout.NORTH);
		
		JPanel panel_1_3 = new JPanel();
		panel.add(panel_1_3, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		pnlHeader.add(panel_1, BorderLayout.EAST);
		
		JPanel panel_1_1 = new JPanel();
		pnlHeader.add(panel_1_1, BorderLayout.WEST);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		splitPane.setRightComponent(panel_5);
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
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Mã nhân viên");
		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2_1);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNhanVien.setEnabled(false);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setColumns(10);
		panel_5.add(txtMaNhanVien);
		
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
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân Viên", "Admin"}));
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
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_5.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinTaiKhoan();
			}
		});
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setPreferredSize(new Dimension(100, 30));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLuu.setFocusable(false);
		btnLuu.setBorder(null);
		btnLuu.setBackground(new Color(21, 155, 71));
		panel_2_1.add(btnLuu);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ thêm tài khoản không?", "Xác nhận huỷ thêm tài khoản", JOptionPane.YES_NO_OPTION);
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
		panel_2_1.add(btnNewButton_1);
		
		dtm = new DefaultTableModel(new Object[] {"Mã NV", "Họ và tên", "Số điện thoại", "Email"}, 0);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row > -1) {
					txtMaNhanVien.setText(table.getValueAt(row, 0).toString());
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		splitPane.setLeftComponent(scrollPane);
		splitPane.setResizeWeight(0.8);
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36,136,203));
		table.getTableHeader().setForeground(new Color(255,255,255));
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// Load dsnv
		loadDanhSachNhanVien();
		xuLyTuDongGanGiaTri();
	}

	// Load danh sách nhân viên
		public void loadDanhSachNhanVien() {
			dtm.setRowCount(0);
			ArrayList<NhanVien> dsnv = NhanVienBUS.getDanhSachNhanVien(true, 1);
			for (NhanVien nv : dsnv) {
				Object[] row = {nv.getStaffId(), nv.getFull_name(), nv.getPhone_number(), nv.getEmail()};
				dtm.addRow(row);
			}
			
		}
		
		// Xử lý tìm kiếm
		public void xuLyTimKiem(String keyword) {
			dtm.setRowCount(0);
			ArrayList<NhanVien> dsnv = NhanVienBUS.searchNhanVien(keyword, 1, true);
			
			for (NhanVien nv : dsnv) {
				Object[] row = {nv.getStaffId(), nv.getFull_name(), nv.getPhone_number(), nv.getEmail()};
				dtm.addRow(row);
			}
		}
		
		// Xử lí tự động điền giá trị
		public void xuLyTuDongGanGiaTri() {
			txtMaTaiKhoan.setText(Integer.toString(TaiKhoanBUS.generateIdTaiKhoan()));
		}
		
		// Xử lý lưu tài khoản
		public void xuLyLuuThongTinTaiKhoan() {
			int accountId = Integer.parseInt(txtMaTaiKhoan.getText().trim());
			String username = txtUsername.getText().trim();
			
			int accountStatus = 0;
			if (cmbTrangThai.getSelectedItem().equals("Hoạt động")) {
				accountStatus = 1;
			} else if (cmbTrangThai.getSelectedItem().equals("Ngưng hoạt động")) {
				accountStatus = 0;
			}
			
			String position = "";
			if (cmbChucVu.getSelectedIndex() == 0) {
				position = "staff";
			} else if (cmbChucVu.getSelectedIndex() == 1) {
				position = "admin";
			}
			
			String message = "Vui lòng nhập đầy đủ các trường:";
			boolean isErrorMessage = false;
			if (username.isEmpty()) {
				message += "\n - Username";
				isErrorMessage = true;
			}
			
			if (txtMaNhanVien.getText().isEmpty()) {
				message += "\n - Chọn nhân viên";
				isErrorMessage = true;
			}
			
			if (isErrorMessage) {
				JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// Kiểm tra lỗi ràng buộc
				String messageError = "Vui lòng nhập đúng định dạng: ";
				boolean isError = false;
				
				String regexUsername = "^[a-zA-Z][a-zA-Z0-9]{7,}$";
				
				if (!username.matches(regexUsername)) {
					messageError += "\n - Username phải có ít nhất 8 ký tự";
					messageError += "\n - Username bắt đầu phải là ký tự chữ";
					isError = true;	
				}
				
				if (TaiKhoanBUS.isUsedUsername(username)) {
					JOptionPane.showMessageDialog(null, "Username đã có người sử dụng, vui lòng nhập cái khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				if (isError) {
					JOptionPane.showMessageDialog(null, messageError, "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}
			
			// Nếu không có lỗi gì hết thì lưu
			int staffId = Integer.parseInt(txtMaNhanVien.getText().trim());
			
			boolean isSuccessCreateNewAccount = TaiKhoanBUS.insertTaiKhoan(username, "shopgiay88", accountStatus, position);
			boolean isSuccessUpdateStaff = NhanVienBUS.updateAccountIdForStaff(staffId, accountId, true);
			
			if (isSuccessCreateNewAccount && isSuccessUpdateStaff) {
				tKhoanGUI.loadDanhSachTaiKhoan();
				JOptionPane.showMessageDialog(null, "Hệ thống đã tạo tài khoản thành công!", "Thông báo tạo thành công tài khoản", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Hệ thống đã tạo tài khoản thất bại!", "Thông báo tạo thất bại tài khoản", JOptionPane.INFORMATION_MESSAGE);
			}
		}
}
