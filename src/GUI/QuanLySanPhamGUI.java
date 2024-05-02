package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

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
import javax.swing.UIManager;

import java.awt.Cursor;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.HangBUS;
import BUS.SanPhamBUS;
import BUS.TheLoaiBUS;
import DTO.HangDTO;
import DTO.SanPhamDTO;
import DTO.TheLoaiDTO;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuanLySanPhamGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTmKiem;
    private JTable table;
    private final ButtonGroup buttonGroup = new ButtonGroup();
//    private JButton btnChiTiet;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
	private DefaultTableModel dtmTableModel;
	private JComboBox comboBoxHang;
	private JComboBox comboBoxLoai;
	private DefaultComboBoxModel dcmHang;
	private DefaultComboBoxModel dcmLoai;
	private JButton btnTim;


	private ArrayList<SanPhamDTO> dsSanPham;
    
//    private static ChiTienSanPhamGUI chiTienSanPhamGUI;
    
	/**
	 * Create the panel.
	 */
	public QuanLySanPhamGUI() {
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
		
		JPanel panelloc = new JPanel(new FlowLayout());

		JPanel pnlHang = new JPanel();
		panelloc.add(pnlHang);
		pnlHang.setLayout(new GridLayout(0, 1, 0, 0));
		
		comboBoxHang = new JComboBox();
		comboBoxHang.setFocusable(false);
		comboBoxHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dcmHang = new DefaultComboBoxModel();
		comboBoxHang.setModel(dcmHang);
		pnlHang.add(comboBoxHang);

		JPanel pnlLoai = new JPanel();
		panelloc.add(pnlLoai);
		pnlLoai.setLayout(new GridLayout(0, 1, 0, 0));
		
		comboBoxLoai = new JComboBox();
		comboBoxLoai.setFocusable(false);
		comboBoxLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dcmLoai = new DefaultComboBoxModel();
		comboBoxLoai.setModel(dcmLoai);
		pnlLoai.add(comboBoxLoai);

		pnlLocNangCao.add(panelloc, BorderLayout.WEST);
		
		// JPanel pnlChucVu = new JPanel();
		// pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		// pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setFocusable(false);
//		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Chức vụ", "Admin", "Nhân viên"}));
//		pnlChucVu.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtTmKiem = new JTextField();
		txtTmKiem.setMinimumSize(new Dimension(250, 19));
		txtTmKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKiem.setColumns(10);
		panel_1.add(txtTmKiem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnTim = new JButton("Làm mới");
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
		
		// btnChiTiet = new JButton("Chi tiết");
		// btnChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnChiTiet.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
		// btnChiTiet.setPreferredSize(new Dimension(150, 40));
		// btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		// btnChiTiet.setFocusable(false);
		// btnChiTiet.setBackground(Color.WHITE);
		// pnlTopBottom.add(btnChiTiet);
		
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
		
		table = new JTable();
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		String[] s = {"Mã SP", "Tên sản phẩm", "Hãng", "Loại", "Giá bán", "Số lượng", "Trạng thái"};
		dtmTableModel = new DefaultTableModel(s,0);
		table.setModel(dtmTableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36,136,203));
		table.getTableHeader().setForeground(new Color(255,255,255));
		table.setRowHeight(25);


		//load sữ liệu
		dsSanPham = SanPhamBUS.getDanhSachSanPham();
		loadComboboxHang();		
		loadComboboxLoai();
		loadDanhSachSanPham();
		// Sự kiện lắng nghe click
		// btnChiTiet.addActionListener(this);
		comboBoxHang.addActionListener(this);
		comboBoxLoai.addActionListener(this);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);



	}

	private void loadDanhSachSanPham(){
		// String[] s = {"Mã SP", "Tên sản phẩm", "Hãng", "Loại", "Giá bán", "Số lượng", "Trạng thái"};
		
		for (SanPhamDTO sanPhamDTO : dsSanPham) {
			HangDTO hang = HangBUS.getHangByID(sanPhamDTO.getBrand_id());
			TheLoaiDTO theLoai = TheLoaiBUS.getTheLoaiByID(sanPhamDTO.getCategory_id());
			String status = sanPhamDTO.isStatus()?"Đang kinh doanh":"Ngừng kinh doanh";
			Object[] obj = {sanPhamDTO.getProduct_id(), sanPhamDTO.getProduct_name(), hang.getBrand_name(), theLoai.getCategory_name(), sanPhamDTO.getOutput_price(), sanPhamDTO.getQuantity(), status};
			dtmTableModel.addRow(obj);
		}
	}


    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSua) {
			EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI(new SanPhamDTO());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			});
		}
		if (e.getSource()==btnTim || e.getSource()==comboBoxHang || e.getSource()==comboBoxLoai) {
			timKiemSanPham();
		}
    }

	public void timKiemSanPham(){
		String tenhang = comboBoxHang.getSelectedItem().toString();
		int hangId=-1;
		if (tenhang!="Tất cả") {
			HangDTO hang = HangBUS.getHangByName(tenhang);
			hangId = hang.getBrand_id();
		}
		String tenloai = comboBoxLoai.getSelectedItem().toString();
		int loaiId=-1;
		if (tenloai!="Tất cả") {
			TheLoaiDTO loai = TheLoaiBUS.getTheLoaiByName(tenloai);
			loaiId = loai.getCategory_id();
		}
		String ten = txtTmKiem.getText();
		dsSanPham = SanPhamBUS.searchDanhSachSanPham(hangId, loaiId, ten);
		loadDanhSachSanPham();
	}

	public void loadComboboxLoai(){
		dcmLoai.addElement("Tất cả");
		dcmLoai.setSelectedItem(dcmLoai.getElementAt(0));
		ArrayList<TheLoaiDTO> theLoaiDTOs = TheLoaiBUS.getDanhSachTheLoai();
		for (TheLoaiDTO theLoaiDTO : theLoaiDTOs) {
			dcmLoai.addElement(theLoaiDTO.getCategory_name());
		}
	}
	public void loadComboboxHang(){
		dcmHang.addElement("Tất cả");
		dcmHang.setSelectedItem(dcmHang.getElementAt(0));
		ArrayList<HangDTO> hangDTOs = HangBUS.getDanhSachHang();
		for (HangDTO hangDTO : hangDTOs) {
			dcmHang.addElement(hangDTO.getBrand_name());
		}
	}
}
