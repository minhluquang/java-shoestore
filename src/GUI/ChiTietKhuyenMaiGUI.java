package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import BUS.KhuyenMaiBUS;
import DTO.KhuyenMai;

public class ChiTietKhuyenMaiGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtDieuKien;
	private JTextField txtPhanTramGiamGia;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cmbTrangThai;
	private KhuyenMai km;
	private KhuyenMaiGUI parentGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietKhuyenMaiGUI frame = new ChiTietKhuyenMaiGUI(new KhuyenMai(), new KhuyenMaiGUI());
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
	public ChiTietKhuyenMaiGUI(KhuyenMai km, KhuyenMaiGUI parentGUI) {
		this.km = km;
		this.parentGUI = parentGUI;
		
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết khuyến mãi không?", "Xác nhận đóng chi tiết khuyến mãi", JOptionPane.YES_NO_OPTION);
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
        setTitle("Thông tin khuyến mãi");
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
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin khuyến mãi");
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
		
		JLabel lblNewLabel_6_2 = new JLabel("Tên KM");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setColumns(10);
		panel_5.add(txtTen);
		
		JLabel lblNewLabel_6 = new JLabel("Giá trị KM");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);
		
		txtDieuKien = new JTextField();
		txtDieuKien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDieuKien.setColumns(10);
		panel_5.add(txtDieuKien);
		
		
		JLabel lblNewLabel_6_3 = new JLabel("Dạng KM");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		txtPhanTramGiamGia = new JTextField();
		txtPhanTramGiamGia.setPreferredSize(new Dimension(100, 19));
		txtPhanTramGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhanTramGiamGia.setColumns(10);
		panel_5.add(txtPhanTramGiamGia);
		
		JLabel lblNewLabel_6_3_1 = new JLabel("Ngày BD");
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1);
		
		txtStartDate = new JTextField();
		txtStartDate.setPreferredSize(new Dimension(100, 19));
		txtStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStartDate.setColumns(10);
		panel_5.add(txtStartDate);
		
		JLabel lblNewLabel_6_3_1_2 = new JLabel("Ngày KT");
		lblNewLabel_6_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_2);
		
		txtEndDate = new JTextField();
		txtEndDate.setPreferredSize(new Dimension(100, 19));
		txtEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEndDate.setColumns(10);
		panel_5.add(txtEndDate);
		
		JLabel lblNewLabel_6_3_1_1 = new JLabel("Status");
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_1);
		
		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"1", "0"}));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setFocusable(false);
		panel_5.add(cmbTrangThai);
		
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		
		hienThiThongTinKhuyenMai();
		// ========= Xử lý lưu thông tin  =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinKhuyenMai();
			}
		});
		// ========= Xử lý lưu thông tin  =========
		
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
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết khuyến mãi không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
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
		
	}
	
	public void xuLyLuuThongTinKhuyenMai() {
	    String discountCode = txtTen.getText();
	    int discount = Integer.parseInt(txtDieuKien.getText());
	    String type = txtPhanTramGiamGia.getText();
	    String startDate = txtStartDate.getText();
	    String endDate = txtEndDate.getText();
	   
	    int status = Integer.parseInt(cmbTrangThai.getSelectedItem().toString());
	    
	    // Kiểm tra form có txt trống không, nếu có thì không cho đi tiếp
	    if (discountCode.trim().isEmpty() || startDate.trim().isEmpty() || endDate.trim().isEmpty()) {
	        String message = "Vui lòng nhập đầy đủ các trường:";
	        message += "\n - Mã khuyến mãi";
	        message += "\n - Phần trăm giảm giá";
	        message += "\n - Ngày bắt đầu";
	        message += "\n - Ngày kết thúc";
	        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	    } else {
	        // Kiểm tra điều kiện phần trăm giảm giá (type)
	        if (type.equalsIgnoreCase("PR")) {
	            if (discount <= 0 || discount >= 101) {
	                JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải nằm trong khoảng từ 1 đến 100", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return; // Dừng lại nếu có lỗi
	            }
	        } else if (type.equalsIgnoreCase("AR")) {
	            if (discount <= 0) {
	                JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return; // Dừng lại nếu có lỗi
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Loại khuyến mãi không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return; // Dừng lại nếu có lỗi
	        }
	        
	        // Kiểm tra ngày kết thúc không nhỏ hơn ngày bắt đầu
	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            Date start = dateFormat.parse(startDate);
	            Date end = dateFormat.parse(endDate);
	            
	            if (end.before(start)) {
	                JOptionPane.showMessageDialog(null, "Ngày kết thúc không được bé hơn ngày bắt đầu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return; // Dừng lại nếu có lỗi
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Tiếp tục thêm hoặc cập nhật thông tin khuyến mãi nếu không có lỗi
	        boolean isExistDiscount = KhuyenMaiBUS.isExistKM(discountCode);
	        if (!isExistDiscount) {
	            if (KhuyenMaiBUS.insertKhuyenMai(discountCode, discount, type, startDate, endDate, status)) {
	                JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin khuyến mãi", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
	                parentGUI.loadDanhSachKhuyenMai();
	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin khuyến mãi", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } else {
	            if (KhuyenMaiBUS.updateKhuyenMai(discountCode, discount, type, startDate, endDate, status)) {
	                JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin khuyến mãi", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
	                parentGUI.loadDanhSachKhuyenMai();
	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin khuyến mãi", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    }
	}

	//
	public void hienThiThongTinKhuyenMai() {
	    txtTen.setText(km.getDiscount_code());
	    txtDieuKien.setText(String.valueOf(km.getDiscount_value()));
	    txtPhanTramGiamGia.setText(km.getType());
	    txtStartDate.setText(km.getStart_date());
	    txtEndDate.setText(km.getEnd_date());	   
	}
}
