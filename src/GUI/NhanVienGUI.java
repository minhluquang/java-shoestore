package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.NhanVien;
import BUS.NhanVienBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NhanVienGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblNhanVien;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTiet;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private DefaultTableModel dtmNhanVien;
    private JComboBox cmbTrangThai;
    
    private static ChiTietNhanVienGUI chiTietNhanVienGUI;
    private static ChiTietQuyenGUI chiTietQuyenGUI;
    
    private NhanVien nv = new NhanVien();
    
	/**
	 * Create the panel.
	 */
	public NhanVienGUI() {
		setBackground(new Color(230, 230, 230));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(255, 255, 255));
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(20, 5));
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(new Color(255, 255, 255));
		pnlTop.add(pnlSearch, BorderLayout.CENTER);
		pnlSearch.setLayout(new BorderLayout(5, 10));
		
		JPanel pnlLocNangCao = new JPanel();
		pnlLocNangCao.setBackground(new Color(255, 255, 255));
		pnlSearch.add(pnlLocNangCao, BorderLayout.WEST);
		pnlLocNangCao.setLayout(new BorderLayout(2, 0));
		
		JPanel pnlTrangThai = new JPanel();
		pnlLocNangCao.add(pnlTrangThai, BorderLayout.WEST);
		pnlTrangThai.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlChucVu = new JPanel();
		pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
		cmbTrangThai = new JComboBox();
		
		// ========== Start: Xử lý search trạng thái ==========
		cmbTrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int searchStatus = cmbTrangThai.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}
				
				xuLyTimKiem(txtTimKiem.getText(), searchStatus);
			}
		});
		// ========== End: Xử lý search trạng thái ==========
		
		cmbTrangThai.setFocusable(false);
		cmbTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Trạng thái", "Hoạt động", "Ngưng hoạt động"}));
		pnlChucVu.add(cmbTrangThai);
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setFocusable(false);
//		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Giới tính", "Nam", "Nữ"}));
//		pnlTrangThai.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtTimKiem = new JTextField();
		
		// ========== Start: Xử lý search ==========
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Nếu có chọn trạng thái thì lọc luôn
				int searchStatus = cmbTrangThai.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}
				
				xuLyTimKiem(txtTimKiem.getText(), searchStatus);
			}
		});
		// ========== End: Xử lý search ==========
		
		txtTimKiem.setMinimumSize(new Dimension(250, 19));
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		panel_1.add(txtTimKiem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnTim = new JButton("Làm mới");
		
		// ========== Start: Xử lý làm mới search ==========
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText("");
				cmbTrangThai.setSelectedIndex(0);
				xuLyTimKiem("", -1);
			}
		});
		// ========== End: Xử lý làm mới search ==========
		
		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTim.setFocusable(false);
		btnTim.setBackground(new Color(255, 255, 255));
		panel_2.add(btnTim);
		
		JPanel pnlTopBottom = new JPanel();
		pnlTopBottom.setBackground(Color.WHITE);
		pnlSearch.add(pnlTopBottom, BorderLayout.SOUTH);
		pnlTopBottom.setLayout(new GridLayout(0, 7, 5, 0));
		
//		btnChiTiet = new JButton("Quyền");
//		btnChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnChiTiet.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
//		btnChiTiet.setPreferredSize(new Dimension(150, 40));
//		btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
//		btnChiTiet.setFocusable(false);
//		btnChiTiet.setBackground(Color.WHITE);
//		pnlTopBottom.add(btnChiTiet);
		
		btnThem = new JButton("Thêm");
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setIcon(new ImageIcon(absolutePath + "/src/images/icons/add.png"));
		btnThem.setPreferredSize(new Dimension(0, 40));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setFocusable(false);
		btnThem.setBackground(Color.WHITE);
		pnlTopBottom.add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setIcon(new ImageIcon(absolutePath + "/src/images/icons/edit.png"));
		btnSua.setPreferredSize(new Dimension(0, 40));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setFocusable(false);
		btnSua.setBackground(Color.WHITE);
		pnlTopBottom.add(btnSua);
		
//		btnXoa = new JButton("Xoá");
//		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnXoa.setIcon(new ImageIcon(absolutePath + "/src/images/icons/delete.png"));
//		btnXoa.setPreferredSize(new Dimension(0, 40));
//		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
//		btnXoa.setFocusable(false);
//		btnXoa.setBackground(Color.WHITE);
//		pnlTopBottom.add(btnXoa);
		
		btnNhapExcel = new JButton("Nhập excel");
		btnNhapExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhapExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnNhapExcel.setPreferredSize(new Dimension(0, 40));
		btnNhapExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhapExcel.setFocusable(false);
		btnNhapExcel.setBackground(Color.WHITE);
		pnlTopBottom.add(btnNhapExcel);
		
		btnXuatExcel = new JButton("Xuất excel");
		btnXuatExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXuatExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnXuatExcel.setPreferredSize(new Dimension(0, 40));
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXuatExcel.setFocusable(false);
		btnXuatExcel.setBackground(Color.WHITE);
		pnlTopBottom.add(btnXuatExcel);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		pnlTop.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		pnlTop.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("");
		pnlTop.add(lblNewLabel_2, BorderLayout.EAST);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		pnlTop.add(panel_8, BorderLayout.SOUTH);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 255));
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		tblNhanVien = new JTable();
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xuLyClickTable();
			}
		});
		tblNhanVien.setBorder(null);
		tblNhanVien.setSelectionBackground(new Color(232, 57, 95));
		tblNhanVien.setRowHeight(25);
		tblNhanVien.setIntercellSpacing(new Dimension(0, 0));
		tblNhanVien.setFocusable(false);
		
		dtmNhanVien = new DefaultTableModel(new Object[]{"Mã NV", "Họ và tên", "Số điện thoại", "Email", "Trạng thái", "Tài khoản"}, 0);
		tblNhanVien.setModel(dtmNhanVien);
		tblNhanVien.setDefaultEditor(Object.class, null);
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tblNhanVien);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		tblNhanVien.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblNhanVien.getTableHeader().setOpaque(false);
		tblNhanVien.getTableHeader().setBackground(new Color(36,136,203));
		tblNhanVien.getTableHeader().setForeground(new Color(255,255,255));
		tblNhanVien.setRowHeight(25);
		

		loadDanhSachNhanVien();
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		
		
		
		// Sự kiện lắng nghe click
//		btnChiTiet.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
//		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btnChiTiet) {
//			if (chiTietQuyenGUI == null || !chiTietQuyenGUI.isVisible()) {
//				chiTietQuyenGUI = new ChiTietQuyenGUI();
//            } else {
//            	chiTietQuyenGUI.toFront();
//            }
//			chiTietQuyenGUI.setVisible(true);
//			chiTietQuyenGUI.requestFocus();
//        } 
		if (e.getSource() == btnThem) {
        	if (chiTietNhanVienGUI == null || !chiTietNhanVienGUI.isVisible()) {
            	chiTietNhanVienGUI = new ChiTietNhanVienGUI(new NhanVien(), this);
            } else {
            	chiTietNhanVienGUI.toFront();
            }
            chiTietNhanVienGUI.setVisible(true);
            chiTietNhanVienGUI.requestFocus();
        } else if (e.getSource() == btnSua) {
        	if (nv.getStaffId() > 0) {
            	if (chiTietNhanVienGUI == null || !chiTietNhanVienGUI.isVisible()) {
                	chiTietNhanVienGUI = new ChiTietNhanVienGUI(nv, this);
                } else {
                	chiTietNhanVienGUI.toFront();
                }
                chiTietNhanVienGUI.setVisible(true);
                chiTietNhanVienGUI.requestFocus();
            } else {
            	JOptionPane.showConfirmDialog(null, "Vui lòng chọn nhân viên cần sửa", "Thông báo lỗi sửa thông tin nhân viên", JOptionPane.CLOSED_OPTION);
            }
        } 
//        else if (e.getSource() == btnXoa) {
//        	int choice = JOptionPane.showConfirmDialog(null, "Xoá thông tin nhân viên có mã nhân viên là NV001", "Xác nhận xoá thông tin nhân viên", JOptionPane.YES_NO_OPTION);
//        	if (choice == JOptionPane.YES_OPTION) {
//        		
//        	} else {
//        		
//        	}
//        }
        else if (e.getSource() == btnNhapExcel) {
            // Xử lý khi button "Nhập excel" được nhấn
        } else if (e.getSource() == btnXuatExcel) {
            // Xử lý khi button "Xuất excel" được nhấn
        }
	}

	// Load danh sách nhân viên
	public void loadDanhSachNhanVien() {
		dtmNhanVien.setRowCount(0);
		ArrayList<NhanVien> dsnv = NhanVienBUS.getDanhSachNhanVien();
		
		for (NhanVien nv : dsnv) {
			int staffStatus = nv.getStaffStatus();
			String status;
			if (staffStatus == 1) {
				status = "Hoạt động";
			} else {
				status = "Ngưng hoạt động";
			}
			
			String accountId = nv.getAccount_id();
			if (accountId == null) {
				accountId = "Chưa có";
			}
			
			Object[] row = {nv.getStaffId(), nv.getFull_name(), nv.getPhone_number(), nv.getEmail(), status, accountId};
			dtmNhanVien.addRow(row);
		}
		
	}
	
	// Xử lý click vào row table
	public void xuLyClickTable() {
		int row = tblNhanVien.getSelectedRow();
		if (row > -1) {
			nv.setStaffId((int) tblNhanVien.getValueAt(row, 0));
			nv.setFull_name((String) tblNhanVien.getValueAt(row, 1));
			nv.setPhone_number((String) tblNhanVien.getValueAt(row, 2));
			nv.setEmail((String) tblNhanVien.getValueAt(row, 3));
			
			String status = (String) tblNhanVien.getValueAt(row, 4);
			if (status.equals("Hoạt động")) {
				nv.setStaffStatus(1);
			} else if (status.equals("Ngưng hoạt động")) {
				nv.setStaffStatus(0);
			}
		}
	}
	
	// Xử lý tìm kiếm
	public void xuLyTimKiem(String keyword, int searchStatus) {
		dtmNhanVien.setRowCount(0);
		ArrayList<NhanVien> dsnv = NhanVienBUS.searchNhanVien(keyword, searchStatus);
		
		for (NhanVien nv : dsnv) {
			int staffStatus = nv.getStaffStatus();
			String status;
			if (staffStatus == 1) {
				status = "Hoạt động";
			} else {
				status = "Ngưng hoạt động";
			}
			
			String accountId = nv.getAccount_id();
			if (accountId == null) {
				accountId = "Chưa có";
			}
			
			Object[] row = {nv.getStaffId(), nv.getFull_name(), nv.getEmail(), nv.getPhone_number(), status, accountId};
			dtmNhanVien.addRow(row);
		}
	}
}
