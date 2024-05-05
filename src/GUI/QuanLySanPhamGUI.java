package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.HangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.TheLoaiBUS;
import DTO.HangDTO;
import DTO.NhanVien;
import DTO.SanPhamDTO;
import DTO.TheLoaiDTO;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLySanPhamGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public String absolutePath = new File("").getAbsolutePath();
	private JTextField txtTmKiem;
	private JTable tblSanPham;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// private JButton btnChiTiet;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnNhapExcel;
	private JButton btnXuatExcel;
	private DefaultTableModel dtmTableModel;
	private JComboBox<String> cbbTrangthai;
	private JComboBox<String> cbbHang;
	private JComboBox<String> cbbLoai;
	private Map<String, Integer> mapTrangThai;
	private Map<String, Integer> mapHang;
	private Map<String, Integer> mapLoai;
	private JButton btnLamMoi;
	private JTabbedPane tabbedPane;
	private JPanel pnlSanPham;
	private JPanel pnlHang;
	private JPanel pnlLoai;

	private SanPhamDTO sp = new SanPhamDTO();
	private ArrayList<SanPhamDTO> dsSanPham;

	public QuanLySanPhamGUI quanLySanPhamGUI;

	// private static ChiTienSanPhamGUI chiTienSanPhamGUI;

	/**
	 * Create the panel.
	 */
	public QuanLySanPhamGUI() {
		this.quanLySanPhamGUI = this;

		tabbedPane = new JTabbedPane();
		pnlSanPham = new JPanel();
		pnlHang = new QLHang();
		pnlLoai = new QLLoai();
		tabbedPane.addTab("Hãng", pnlSanPham);
		tabbedPane.addTab("Hãng", pnlHang);
        tabbedPane.addTab("Loại", pnlLoai);
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tabbedPane.setForegroundAt(0, new Color(36, 136, 203));
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                // Tùy chỉnh giao diện của tab được chọn
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    tabbedPane.setForegroundAt(i, Color.BLACK); // Đặt màu chữ của tất cả các tab về màu đen
                }
                tabbedPane.setForegroundAt(selectedIndex, new Color(36, 136, 203)); // Đặt màu chữ của tab được chọn
                                                                                    // thành màu đỏ
            }
        });

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

		pnlSanPham.setBackground(new Color(230, 230, 230));
		pnlSanPham.setLayout(new BorderLayout(10, 10));

		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(255, 255, 255));
		pnlSanPham.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(20, 5));

		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(new Color(255, 255, 255));
		pnlTop.add(pnlSearch, BorderLayout.CENTER);
		pnlSearch.setLayout(new BorderLayout(5, 10));

		JPanel pnlLocNangCao = new JPanel();
		pnlLocNangCao.setBackground(new Color(255, 255, 255));
		pnlSearch.add(pnlLocNangCao, BorderLayout.WEST);
		pnlLocNangCao.setLayout(new GridLayout(1, 0, 2, 0));

		JPanel pnlHang = new JPanel();
		pnlLocNangCao.add(pnlHang);
		pnlHang.setLayout(new GridLayout(0, 1, 0, 0));

		cbbHang = new JComboBox<>();
		cbbHang.setFocusable(false);
		cbbHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlHang.add(cbbHang);

		JPanel pnlLoai = new JPanel();
		pnlLocNangCao.add(pnlLoai);
		pnlLoai.setLayout(new GridLayout(0, 1, 0, 0));

		cbbLoai = new JComboBox<>();
		cbbLoai.setFocusable(false);
		cbbLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbbLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlLoai.add(cbbLoai);

		JPanel pnlTrangThai = new JPanel();
		pnlLocNangCao.add(pnlTrangThai);
		pnlTrangThai.setLayout(new GridLayout(0, 1, 0, 0));

		cbbTrangthai = new JComboBox<>();
		cbbTrangthai.setFocusable(false);
		cbbTrangthai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbbTrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlTrangThai.add(cbbTrangthai);
		mapTrangThai = new HashMap<>();
		mapTrangThai.put("Trạng thái", -1);
		mapTrangThai.put("Đang kinh doanh", 1);
		mapTrangThai.put("Ngừng kinh doanh", 0);
		for (String key  : mapTrangThai.keySet()) {
			cbbTrangthai.addItem(key);
		}
		cbbTrangthai.setSelectedItem("Trạng thái");

		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		txtTmKiem = new JTextField();
		txtTmKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemSanPham();
			}
		});
		txtTmKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKiem.setColumns(10);
		panel_1.add(txtTmKiem);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLamMoi.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBackground(new Color(255, 255, 255));
		panel_2.add(btnLamMoi);

		JPanel pnlSearchPrice = new JPanel();

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
		pnlSanPham.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		dtmTableModel = new DefaultTableModel(new Object[] {"Mã SP", "Tên sản phẩm", "Hãng", "Loại", "Giá bán", "Số lượng", "Trạng thái" }, 0);
		tblSanPham = new JTable(dtmTableModel);
		tblSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xuLyClickTable();
			}
		});
		tblSanPham.setBorder(null);
		tblSanPham.setSelectionBackground(new Color(232, 57, 95));
		tblSanPham.setRowHeight(25);
		tblSanPham.setIntercellSpacing(new Dimension(0, 0));
		tblSanPham.setFocusable(false);
		tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblSanPham.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tblSanPham);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);

		tblSanPham.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblSanPham.getTableHeader().setOpaque(false);
		tblSanPham.getTableHeader().setBackground(new Color(36, 136, 203));
		tblSanPham.getTableHeader().setForeground(new Color(255, 255, 255));
		tblSanPham.setRowHeight(25);

		// load sữ liệu
		dsSanPham = SanPhamBUS.getDanhSachSanPham();
		loadcbbHang();
		loadcbbLoai();
		loadDanhSachSanPham();
		// Sự kiện lắng nghe click
		// btnChiTiet.addActionListener(this);
		cbbHang.addActionListener(this);
		cbbLoai.addActionListener(this);
		cbbTrangthai.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);

	}

	public void reLoadData(){
		dsSanPham = SanPhamBUS.getDanhSachSanPham();
		loadDanhSachSanPham();
	}

	public void loadDanhSachSanPham() {
		// String[] s = {"Mã SP", "Tên sản phẩm", "Hãng", "Loại", "Giá bán", "Số lượng",
		// "Trạng thái"};

		dtmTableModel.setRowCount(0);
		dsSanPham = SanPhamBUS.getDanhSachSanPham(); 
		for (SanPhamDTO sanPhamDTO : dsSanPham) {
			HangDTO hang = HangBUS.getHangByID(sanPhamDTO.getBrand_id());
			TheLoaiDTO theLoai = TheLoaiBUS.getTheLoaiByID(sanPhamDTO.getCategory_id());
			String status = sanPhamDTO.isStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh";
			Object[] obj = { sanPhamDTO.getProduct_id(), sanPhamDTO.getProduct_name(), hang.getBrand_name(),
					theLoai.getCategory_name(), sanPhamDTO.getOutput_price(), sanPhamDTO.getQuantity(), status };
			dtmTableModel.addRow(obj);
		}
	}

	public void loadcbbLoai() {
		mapLoai = new HashMap<>();
		mapLoai.put("Loại", 0);
		ArrayList<TheLoaiDTO> theLoaiDTOs = TheLoaiBUS.getDanhSachTheLoai();
		for (TheLoaiDTO theLoaiDTO : theLoaiDTOs) {
			if (theLoaiDTO.isStatus()) {
			mapLoai.put(theLoaiDTO.getCategory_name(), theLoaiDTO.getCategory_id());
				
			}
		}
		for (String key : mapLoai.keySet()) {
			cbbLoai.addItem(key);
		}
		cbbLoai.setSelectedItem("Loại");
	}

	public void loadcbbHang() {
		mapHang = new HashMap<>();
		mapHang.put("Hãng", 0);
		ArrayList<HangDTO> hangDTOs = HangBUS.getDanhSachHang();
		for (HangDTO hangDTO : hangDTOs) {
			if (hangDTO.isStatus()) {
			mapHang.put(hangDTO.getBrand_name(), hangDTO.getBrand_id());
				
			}
		}
		for (String key : mapHang.keySet()) {
			cbbHang.addItem(key);
		}
		cbbHang.setSelectedItem("Hãng");
	}

	public void timKiemSanPham() {
		int hangId = mapHang.get(cbbHang.getSelectedItem()).intValue();
		int loaiId = mapLoai.get(cbbLoai.getSelectedItem()).intValue();
		int trangThai = mapTrangThai.get(cbbTrangthai.getSelectedItem()).intValue();
		String ten = txtTmKiem.getText().toLowerCase().strip();
		dsSanPham = SanPhamBUS.searchDanhSachSanPham(hangId, loaiId, ten, trangThai);
		
		dtmTableModel.setRowCount(0);
		for (SanPhamDTO sp: dsSanPham) {
			HangDTO hang = HangBUS.getHangByID(sp.getBrand_id());
			TheLoaiDTO theLoai = TheLoaiBUS.getTheLoaiByID(sp.getCategory_id());
			String status = sp.isStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh";
			Object[] obj = { sp.getProduct_id(), sp.getProduct_name(), hang.getBrand_name(),
					theLoai.getCategory_name(), sp.getOutput_price(), sp.getQuantity(), status };
			dtmTableModel.addRow(obj);
		}
	}

	public void suaSanPham() {
		int selectedIndex = tblSanPham.getSelectedRow();
		int productID = (int) tblSanPham.getValueAt(selectedIndex, 0);
		SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(productID);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI(sanPhamDTO, "change", quanLySanPhamGUI);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void themSanPham() {
		SanPhamDTO sanPhamDTO = new SanPhamDTO();
		int productID = SanPhamBUS.getSoluongSanPham()+1;
		sanPhamDTO.setProduct_id(productID);
		sanPhamDTO.setQuantity(0);
		sanPhamDTO.setStatus(true);
		sanPhamDTO.setCountry("");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI(sanPhamDTO, "add", quanLySanPhamGUI);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void changeStatusSanPham(int productID){
		SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(productID);
		sanPhamDTO.setStatus(!sanPhamDTO.isStatus());
		boolean doiThanhCong = SanPhamBUS.doiTrangThaiSanPham(sanPhamDTO);
		if (doiThanhCong) {
			JOptionPane.showMessageDialog(null, "Đổi trạng thái sản phẩm "+productID+" thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Đổi trạng thái sản phẩm "+productID+" thất bại");
		}
		dsSanPham = SanPhamBUS.getDanhSachSanPham();
		loadDanhSachSanPham();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSua) {
			if (sp.getProduct_id() > 0) {
				suaSanPham();				
			} else {
            	JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa", "Thông báo lỗi sửa thông tin sản phẩm", JOptionPane.INFORMATION_MESSAGE);
            }
		}
		if (e.getSource() == btnLamMoi) {			
			xuLyLamMoi();
		}
		
		if (e.getSource() == cbbHang || e.getSource() == cbbLoai || e.getSource() == cbbTrangthai) {
			timKiemSanPham();
		}
		if (e.getSource() == btnThem) {
			themSanPham();	
		}
		if (e.getSource()==btnXoa) {
			int selectedIndex = tblSanPham.getSelectedRow();
			int productID = (int) tblSanPham.getValueAt(selectedIndex, 0);
			int op = JOptionPane.showConfirmDialog(null, "Bạn muốn thay đổi trạng thái sản phẩm "+productID+"?","Xác nhận xoá sản phẩm", JOptionPane.YES_NO_OPTION);
			if (op == JOptionPane.YES_OPTION) {
				changeStatusSanPham(productID);
			}
		}
		if (e.getSource() == btnNhapExcel) {
			importExcel();
		}
		if (e.getSource() == btnXuatExcel) {
			try {
				exportExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	// Xử lý click vào row table
		public void xuLyClickTable() {
			int row = tblSanPham.getSelectedRow();
			if (row > -1) {
				sp.setProduct_id((int) tblSanPham.getValueAt(row, 0));
			}
		}
	
	public void xuLyLamMoi() {
		txtTmKiem.setText("");
		cbbHang.setSelectedItem("Hãng");
		cbbLoai.setSelectedItem("Loại");
		cbbTrangthai.setSelectedItem("Trạng thái");
		loadDanhSachSanPham();
	}
	
	public void importExcel() {
		try {
			ArrayList<SanPhamDTO> dssp = new ArrayList<>();
			
    		JFileChooser fileChooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
    		fileChooser.setFileFilter(filter);

    		int result = fileChooser.showOpenDialog(null);
    		if (result == JFileChooser.APPROVE_OPTION) {
    		    File selectedFile = fileChooser.getSelectedFile();
    		    
    		    FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsoluteFile());
    		    XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
    		    XSSFSheet sheet = wb.getSheetAt(0); // Lất sheet 0 của excel
    		    FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator(); // Lấy giá trị các cột
    		    
    		    // Duyệt qua từng hàng trong sheet
                for (Row row : sheet) {
                	if (row.getRowNum()==0) {
                		if (!checkHeaderImportExcel(row)) {
                			JOptionPane.showMessageDialog(null, "Lỗi hàng đầu tiên không đúng định dạng!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                			return;
                		}
                		continue;
                	}
                	
                	// Duyệt qua từng ô trong 1 hàng
                	SanPhamDTO sp = new SanPhamDTO();
                    for (Cell cell : row) { 
                    	int columnIndex = cell.getColumnIndex();
                        try {
                        	switch (columnIndex) {
	                         case 0:
	                        	 sp.setProduct_name(cell.getStringCellValue());
	                            break;
	                         case 1:
	                        	 sp.setBrand_id((int) cell.getNumericCellValue());
	                             break;
	                         case 2:
	                        	 sp.setCategory_id((int) cell.getNumericCellValue());
	                             break;
	                         case 3:
	                        	 sp.setOutput_price((int) cell.getNumericCellValue());
	                        	 break;
	                         case 4:
	                        	 sp.setCountry(cell.getStringCellValue());
	                        	 break;
	                         case 5:
	                        	 sp.setYear_of_product((int) cell.getNumericCellValue());
	                        	 break;
                           }
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi định dạng dữ liệu, vui lòng kiểm tra lại file excel!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
						}
                    }
                    dssp.add(sp);
                }
                
                // Ghi dữ liệu vào db
					if (SanPhamBUS.themDanhSachSanPham(dssp)) {
						loadDanhSachSanPham();
						JOptionPane.showMessageDialog(null, "Đã import dữ liệu từ file excel vào hệ thống thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
							JOptionPane.showMessageDialog(null, "Có lỗi khi import dữ liệu từ file excel vào hệ thống!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
							return;
					}
				
    		}
    	} catch (Exception e2) {
    	    // Xử lý ngoại lệ ở đây nếu cần
    	}
	}
	
	public boolean checkHeaderImportExcel (Row row) {
        String[] expectedHeaders = {"product_name", "brand_id", "category_id", "output_price", "country", "year_of_product"};
        boolean headerMatched = true;
        
        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = row.getCell(i);
            if (cell == null || !cell.getStringCellValue().trim().equals(expectedHeaders[i])) {
                headerMatched = false;
                break;
            }
        }
        
        return headerMatched;
	}
	
	public void exportExcel() throws IOException {
		ArrayList<SanPhamDTO> dssp= SanPhamBUS.getDanhSachSanPham();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dssp.xlsx");
		    XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet("Danh sách sản phẩm");
		    
		    // Ghi header
		    XSSFRow headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("product_id");
		    headerRow.createCell(1).setCellValue("product_name");
		    headerRow.createCell(2).setCellValue("brand_id");
		    headerRow.createCell(3).setCellValue("category_id");
		    headerRow.createCell(4).setCellValue("output_price");
		    headerRow.createCell(5).setCellValue("country");
		    headerRow.createCell(6).setCellValue("year_of_product");
		    
		    // Ghi thông tin nhân viên
		    int rowNum = 1;
		    for (SanPhamDTO sp: dssp) {
		    	XSSFRow row = sheet.createRow(rowNum++);
		    	row.createCell(0).setCellValue(sp.getProduct_id());
		    	row.createCell(1).setCellValue(sp.getProduct_name());
		    	row.createCell(2).setCellValue(sp.getBrand_id());
		    	row.createCell(3).setCellValue(sp.getCategory_id());
		    	row.createCell(4).setCellValue(sp.getOutput_price());
		    	row.createCell(5).setCellValue(sp.getCountry());
		    	row.createCell(6).setCellValue(sp.getYear_of_product());
		    }
		    
		    wb.write(fileOutputStream);
		    wb.close();
		    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
