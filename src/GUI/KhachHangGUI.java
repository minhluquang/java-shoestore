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

import DTO.KhachHang;
import BUS.KhachHangBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class KhachHangGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblKhachHang;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTiet;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private DefaultTableModel dtmKhachHang;
    private JComboBox cmbTrangThai;
    
    private static ChiTietKhachHangGUI chiTietKhachHangGUI;
    
    private KhachHang kh = new KhachHang();
    
	/**
	 * Create the panel.
	 */
	public KhachHangGUI() {
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
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtTimKiem = new JTextField();
		
		// ========== Start: Xử lý search ==========
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				xuLyTimKiem(txtTimKiem.getText());
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
				xuLyTimKiem("");
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
		tblKhachHang = new JTable();
		tblKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xuLyClickTable();
			}
		});
		tblKhachHang.setBorder(null);
		tblKhachHang.setSelectionBackground(new Color(232, 57, 95));
		tblKhachHang.setRowHeight(25);
		tblKhachHang.setIntercellSpacing(new Dimension(0, 0));
		tblKhachHang.setFocusable(false);
		
		dtmKhachHang = new DefaultTableModel(new Object[]{"Mã khách hàng", "Họ và tên", "Số điện thoại"}, 0);
		tblKhachHang.setModel(dtmKhachHang);
		tblKhachHang.setDefaultEditor(Object.class, null);
		tblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tblKhachHang);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		tblKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblKhachHang.getTableHeader().setOpaque(false);
		tblKhachHang.getTableHeader().setBackground(new Color(36,136,203));
		tblKhachHang.getTableHeader().setForeground(new Color(255,255,255));
		tblKhachHang.setRowHeight(25);
		

		loadDanhSachKhachHang();
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
		if (e.getSource() == btnThem) {
        	if (chiTietKhachHangGUI == null || !chiTietKhachHangGUI.isVisible()) {
        		chiTietKhachHangGUI = new ChiTietKhachHangGUI(new KhachHang(), this);
            } else {
            	chiTietKhachHangGUI.toFront();
            }
        	chiTietKhachHangGUI.setVisible(true);
        	chiTietKhachHangGUI.requestFocus();
        } else if (e.getSource() == btnSua) {
        	if (kh.getCustomerId() > 0) {
            	if (chiTietKhachHangGUI == null || !chiTietKhachHangGUI.isVisible()) {
            		chiTietKhachHangGUI = new ChiTietKhachHangGUI(kh, this);
                } else {
                	chiTietKhachHangGUI.toFront();
                }
            	chiTietKhachHangGUI.setVisible(true);
            	chiTietKhachHangGUI.requestFocus();
            } else {
            	JOptionPane.showConfirmDialog(null, "Vui lòng chọn khách hàng cần sửa", "Thông báo lỗi sửa thông tin khách hàng", JOptionPane.CLOSED_OPTION);
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
	public void loadDanhSachKhachHang() {
		dtmKhachHang.setRowCount(0);
		ArrayList<KhachHang> dskh = KhachHangBUS.getDanhSachKhachHang();
		
		for (KhachHang kh : dskh) {
			Object[] row = {kh.getCustomerId(), kh.getCustomerName(), kh.getPhoneNumber()};
			dtmKhachHang.addRow(row);
		}
		
	}
	
	// Xử lý click vào row table
	public void xuLyClickTable() {
		int row = tblKhachHang.getSelectedRow();
		if (row > -1) {
			kh.setCustomerId((int) tblKhachHang.getValueAt(row, 0));
			kh.setCustomerName((String) tblKhachHang.getValueAt(row, 1));
			kh.setPhoneNumber((String) tblKhachHang.getValueAt(row, 2));
		}
	}
	
	// Xử lý tìm kiếm
	public void xuLyTimKiem(String keyword) {
		dtmKhachHang.setRowCount(0);
		ArrayList<KhachHang> dskh = KhachHangBUS.searchKhachHang(keyword);
		
		for (KhachHang kh : dskh) {
			Object[] row = {kh.getCustomerId(), kh.getCustomerName(), kh.getPhoneNumber()};
			dtmKhachHang.addRow(row);
		}
	}
}
