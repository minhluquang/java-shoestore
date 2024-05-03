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

import DTO.KhachHang;
import DTO.NhanVien;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import GUI.NhanVienGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiTietKhachHangGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtSoDienThoai;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtMaKhachHang;

	private KhachHang khachhang;
	private KhachHangGUI parentGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietKhachHangGUI frame = new ChiTietKhachHangGUI(new KhachHang(), new KhachHangGUI());
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
	public ChiTietKhachHangGUI(KhachHang kh, KhachHangGUI parentGUI) {
		this.khachhang = kh;
		this.parentGUI = parentGUI;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết khách hàng không?",
						"Xác nhận đóng chi tiết khách hàng", JOptionPane.YES_NO_OPTION);
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

		JLabel lblNewLabel_4 = new JLabel("Thông tin khách hàng");
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

		JLabel lblNewLabel_6_2 = new JLabel("Mã khách hàng");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEnabled(false);
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaKhachHang.setColumns(10);
		panel_5.add(txtMaKhachHang);

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

		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);

		JLabel lblNewLabel_7_1_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));

		// ========= Xử lý lưu thông tin khách hàng =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinNhanVien();
			}
		});
		// ========= Xử lý lưu thông tin khách hàng =========

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
				int choice = JOptionPane.showConfirmDialog(null,
						"Bạn có muốn huỷ bỏ chỉnh sửa chi tiết khách hàng không?",
						"Xác nhận huỷ bỏ chỉnh sửa chi tiết khách hàng", JOptionPane.YES_NO_OPTION);
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
		if (kh != null) {
			xuLyTuDongGanGiaTri();
		}
	}

	public ChiTietKhachHangGUI(KhachHang kh, ChiTietHoaDonGUI chiTietHoaDonGUI) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết khách hàng không?",
						"Xác nhận đóng chi tiết khách hàng", JOptionPane.YES_NO_OPTION);
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

		JLabel lblNewLabel_4 = new JLabel("Thông tin khách hàng");
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

		JLabel lblNewLabel_6_2 = new JLabel("Mã khách hàng");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEnabled(false);
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaKhachHang.setColumns(10);
		panel_5.add(txtMaKhachHang);

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

		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);

		JLabel lblNewLabel_7_1_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));

		// ========= Xử lý lưu thông tin khách hàng =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinNhanVien(kh, chiTietHoaDonGUI);
			}
		});
		// ========= Xử lý lưu thông tin khách hàng =========

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
				int choice = JOptionPane.showConfirmDialog(null,
						"Bạn có muốn huỷ bỏ chỉnh sửa chi tiết khách hàng không?",
						"Xác nhận huỷ bỏ chỉnh sửa chi tiết khách hàng", JOptionPane.YES_NO_OPTION);
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
		xuLyTuDongGanGiaTri(kh);
	}

	public void xuLyTuDongGanGiaTri() {
		if (khachhang.getCustomerId() > 0) {
			txtMaKhachHang.setText(Integer.toString(khachhang.getCustomerId()));
		} else {
			txtMaKhachHang.setText(Integer.toString(KhachHangBUS.generateIdKhachHang()));
		}
		txtHoTen.setText(khachhang.getCustomerName());
		txtSoDienThoai.setText(khachhang.getPhoneNumber());
	}

	public void xuLyLuuThongTinNhanVien() {
		int customerId = Integer.parseInt(txtMaKhachHang.getText());
		String customerName = txtHoTen.getText().trim();
		String phoneNumber = txtSoDienThoai.getText().trim();

		if (customerName.isEmpty() || phoneNumber.isEmpty()) {
			String message = "Vui lòng nhập đầy đủ các trường:";
			message += "\n - Họ và tên";
			message += "\n - Số điện thoại";
			JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra lỗi ràng buộc
			String messageError = "Vui lòng nhập đúng định dạng: ";
			Boolean isError = false;

			String regexPhoneNumber = "^0\\d{9}$";
			String regexFullname = "^[a-zA-Z]+(\\s[a-zA-Z]+)+$";

			if (!customerName.matches(regexFullname)) {
				messageError += "\n - Họ và tên không dấu (ví dụ: Lu Quang Minh)";
				isError = true;
			}
			if (!phoneNumber.matches(regexPhoneNumber)) {
				messageError += "\n - Số điện thoại (ví dụ: 0931814480)";
				isError = true;
			}

			if (isError) {
				JOptionPane.showMessageDialog(null, messageError, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (KhachHangBUS.isExistPhoneNumber(phoneNumber, customerId)) {
				JOptionPane.showMessageDialog(null, "Hệ thống đã có khách hàng sử dụng số điện thoại này",
						"Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (KhachHangBUS.isExistKhachHang(customerId)) {
					if (KhachHangBUS.updateKhachHang(customerId, customerName, phoneNumber)) {
						JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin khách hàng",
								"Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
						parentGUI.loadDanhSachKhachHang();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin khách hàng",
								"Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					int status = 1;
					if (KhachHangBUS.insertKhachHang(customerId, customerName, phoneNumber, status)) {
						JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin khách hàng",
								"Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
						parentGUI.loadDanhSachKhachHang();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin khách hàng",
								"Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	public void xuLyTuDongGanGiaTri(KhachHang kh) {
		txtMaKhachHang.setText(Integer.toString(KhachHangBUS.generateIdKhachHang()));
		txtHoTen.setText(kh.getCustomerName());
		txtSoDienThoai.setText(kh.getPhoneNumber());
	}

	public void xuLyLuuThongTinNhanVien(KhachHang kh,ChiTietHoaDonGUI chiTietHoaDonGUI) {
		int customerId = KhachHangBUS.generateIdKhachHang();
		txtMaKhachHang.setText(customerId + "");
		String customerName = txtHoTen.getText().trim();
		String phoneNumber = txtSoDienThoai.getText().trim();

		if (customerName.isEmpty() || phoneNumber.isEmpty()) {
			String message = "Vui lòng nhập đầy đủ các trường:";
			message += "\n - Họ và tên";
			message += "\n - Số điện thoại";
			JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra lỗi ràng buộc
			String messageError = "Vui lòng nhập đúng định dạng: ";
			Boolean isError = false;

			String regexPhoneNumber = "^0\\d{9}$";
			String regexFullname = "^[a-zA-Z]+(\\s[a-zA-Z]+)+$";

			if (!customerName.matches(regexFullname)) {
				messageError += "\n - Họ và tên không dấu (ví dụ: Lu Quang Minh)";
				isError = true;
			}
			if (!phoneNumber.matches(regexPhoneNumber)) {
				messageError += "\n - Số điện thoại (ví dụ: 0931814480)";
				isError = true;
			}

			if (isError) {
				JOptionPane.showMessageDialog(null, messageError, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (KhachHangBUS.isExistPhoneNumber(phoneNumber, customerId)) {
				kh = KhachHangBUS.getKhachHangByPhoneNumber(phoneNumber);
				chiTietHoaDonGUI.loadCustumerId(kh.getCustomerId());
				dispose();
			} else {
				if (KhachHangBUS.insertKhachHang(customerId, customerName, phoneNumber, 1)) {
					kh = KhachHangBUS.getKhachHangByID(customerId);
					chiTietHoaDonGUI.loadCustumerId(customerId);
					dispose();
				}
			}
		}
	}
}
