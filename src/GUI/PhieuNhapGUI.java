package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.NhaCungCapBUS;
import BUS.PhieuNhapBUS;
import DTO.PhieuNhap;

public class PhieuNhapGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public String absolutePath = new File("").getAbsolutePath();
	private JTextField txtTmKim;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnChiTietPhieuNhap;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnNhapExcel;
	private JButton btnXuatExcel;
	private DefaultTableModel defaultTableModel;

	private static ChiTietPhieuNhapGUI chiTietPhieuNhap;

	/**
	 * Create the panel.
	 */
	public PhieuNhapGUI() {
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

		JComboBox comboBox = new JComboBox();
		comboBox.setFocusable(false);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tất cả" }));
		pnlTrangThai.add(comboBox);

		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		txtTmKim = new JTextField();
		txtTmKim.setMinimumSize(new Dimension(250, 19));
		txtTmKim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKim.setColumns(10);
		panel_1.add(txtTmKim);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnTim = new JButton("");
		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setIcon(new ImageIcon(absolutePath + "/src/images/icons/search.png"));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTim.setFocusable(false);
		btnTim.setBackground(new Color(255, 255, 255));
		panel_2.add(btnTim);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDanhSachPhieuNhap();
			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamMoi.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBackground(Color.WHITE);
		panel_2.add(btnLamMoi);

		JPanel pnlTopBottom = new JPanel();
		pnlTopBottom.setBackground(Color.WHITE);
		pnlSearch.add(pnlTopBottom, BorderLayout.SOUTH);
		pnlTopBottom.setLayout(new GridLayout(0, 7, 5, 0));

		btnChiTietPhieuNhap = new JButton("Chi Tiết");
		btnChiTietPhieuNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChiTietPhieuNhap.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
		btnChiTietPhieuNhap.setPreferredSize(new Dimension(150, 40));
		btnChiTietPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTietPhieuNhap.setFocusable(false);
		btnChiTietPhieuNhap.setBackground(Color.WHITE);
		pnlTopBottom.add(btnChiTietPhieuNhap);

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

		btnXoa = new JButton("Xoá");
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setIcon(new ImageIcon(absolutePath + "/src/images/icons/delete.png"));
		btnXoa.setPreferredSize(new Dimension(0, 40));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setFocusable(false);
		btnXoa.setBackground(Color.WHITE);
		pnlTopBottom.add(btnXoa);

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

//		=================================TABLE================================
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionForeground(Color.WHITE);
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);

		defaultTableModel = new DefaultTableModel(
				new Object[] { "STT", "Mã Phiếu", "Nhà cung cấp", "Nhân Viên", "Thời gian", "Tổng số tiền" }, 0);
		table.setModel(defaultTableModel);
		table.setDefaultEditor(Object.class, null);

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		pnlCenter.add(scrollPane, BorderLayout.NORTH);

		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		loadDanhSachPhieuNhap();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int selectedRow = target.getSelectedRow();
					if (selectedRow != -1) {
						int maPhieu = (int) defaultTableModel.getValueAt(selectedRow, 1);
						hienThiThongTinPhieuNhap(maPhieu);
					}
				}
			}
		});

		btnChiTietPhieuNhap.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChiTietPhieuNhap) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int maPhieu = (int) defaultTableModel.getValueAt(selectedRow, 1);
				hienThiThongTinPhieuNhap(maPhieu);
			}
//		} else if (e.getSource() == btnThem) {
//			if (chiTietNhanVienGUI == null || !chiTietNhanVienGUI.isVisible()) {
//				chiTietNhanVienGUI = new ChiTietNhaCungCapGUI();
//			} else {
//				chiTietNhanVienGUI.toFront();
//			}
//			chiTietNhanVienGUI.setVisible(true);
//			chiTietNhanVienGUI.requestFocus();
//		} else if (e.getSource() == btnSua) {
//			if (chiTietNhanVienGUI == null || !chiTietNhanVienGUI.isVisible()) {
//				chiTietNhanVienGUI = new ChiTietNhaCungCapGUI();
//			} else {
//				chiTietNhanVienGUI.toFront();
//			}
//			chiTietNhanVienGUI.setVisible(true);
//			chiTietNhanVienGUI.requestFocus();
//		} else if (e.getSource() == btnXoa) {
//			if (chiTietNhanVienGUI == null || !chiTietNhanVienGUI.isVisible()) {
//				chiTietNhanVienGUI = new ChiTietNhaCungCapGUI();
//			} else {
//				chiTietNhanVienGUI.toFront();
//			}
//			chiTietNhanVienGUI.setVisible(true);
//			chiTietNhanVienGUI.requestFocus();
		} else if (e.getSource() == btnNhapExcel) {
			// Xử lý khi button "Nhập excel" được nhấn
		} else if (e.getSource() == btnXuatExcel) {
			// Xử lý khi button "Xuất excel" được nhấn
		}
	}

	public static void main(String[] args) {
		PhieuNhapGUI a = new PhieuNhapGUI();
		JFrame f = new JFrame();
		f.getContentPane().add(a);
		f.setVisible(true);
	}

	public void loadDanhSachPhieuNhap() {
		defaultTableModel.setRowCount(0);
		ArrayList<PhieuNhap> dspn = PhieuNhapBUS.getDanhSachPhieuNhap();

		int rowNum = defaultTableModel.getRowCount();
		for (PhieuNhap p : dspn) {
			int stt = ++rowNum;
			String TenNhanVien = PhieuNhapBUS.getTenNhanVienById(p.getStaff_id());
			String TenNhaCungCap = NhaCungCapBUS.getTenNhaCungCapById(p.getSupplier_id());
			Object[] row = { stt, p.getReceipt_id(), TenNhaCungCap, TenNhanVien, p.getDate(), p.getTotal_price() };
			defaultTableModel.addRow(row);
		}

	}

	public void hienThiThongTinPhieuNhap(int maPhieu) {
		if (chiTietPhieuNhap == null || !chiTietPhieuNhap.isVisible()) {
			chiTietPhieuNhap = new ChiTietPhieuNhapGUI(maPhieu);
		} else {
			chiTietPhieuNhap.toFront();
		}
		chiTietPhieuNhap.setVisible(true);
		chiTietPhieuNhap.requestFocus();
	}
}
