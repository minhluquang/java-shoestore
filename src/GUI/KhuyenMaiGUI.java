package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.KhuyenMaiBUS;
import DTO.KhuyenMai;

public class KhuyenMaiGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public String absolutePath = new File("").getAbsolutePath();
	private JTextField txtTmKim;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnChiTiet;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnNhapExcel;
	private JButton btnXuatExcel;
	private DefaultTableModel dtmKhuyenMai;
	private JComboBox cmbTrangThai;
	private static ChiTietKhuyenMaiGUI chiTietKhuyenMaiGUI;
	private KhuyenMai km = new KhuyenMai();

	public KhuyenMaiGUI() {
		setBackground(new Color(230, 230, 230));
		setLayout(new BorderLayout(10, 10));
		Font font = new Font(getFont().getName(), Font.PLAIN, 16);
		ArrayList<KhuyenMai> danhSachKhuyenMai = KhuyenMaiBUS.getDanhSachKhuyenMai();
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

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFocusable(false);
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Trạng thái", "1", "0" }));

		// ========== Start: Xử lý search trạng thái ==========
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int searchStatus = comboBox_1.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}

				xuLyTimKiem(txtTmKim.getText(), searchStatus);
			}
		});
		// ========== End: Xử lý search trạng thái ==========

		pnlChucVu.add(comboBox_1);

		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		txtTmKim = new JTextField();
		// ========== Start: Xử lý search ==========
		txtTmKim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Lấy giá trị của comboBox_1 để xác định trạng thái tìm kiếm
				int searchStatus = comboBox_1.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}
				xuLyTimKiem(txtTmKim.getText(), searchStatus);
			}
		});
		// ========== End: Xử lý search ==========

		txtTmKim.setMinimumSize(new Dimension(250, 19));
		txtTmKim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKim.setColumns(10);
		panel_1.add(txtTmKim);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnTim = new JButton("Làm mới");
		// ========== Start: Xử lý làm mới search ==========
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTmKim.setText("");
				comboBox_1.setSelectedIndex(0);
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

		// ========== TABLE DANH SÁCH ==========
		table = new JTable();
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);

		dtmKhuyenMai = new DefaultTableModel(
				new Object[] { "Name", "Discount_Value", "Type", "Start_Date", "End_Date", "Status" }, 0);
		table.setModel(dtmKhuyenMai);
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);

		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		// Căn giữa tiêu đề
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		// Căn giữa dữ liệu trong bảng
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		loadDanhSachKhuyenMai();

		// Sự kiện lắng nghe click
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}

	// search
	public void xuLyTimKiem(String keyword, int searchStatus) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<KhuyenMai> dskm = KhuyenMaiBUS.searchKhuyenMai(keyword, searchStatus);

		for (KhuyenMai km : dskm) {
			String statusINT = String.valueOf(km.getStatus());
			String status;
			if ("1".equals(statusINT)) {
				status = "1";
			} else {
				status = "0";
			}
			Object[] row = new Object[] { km.getDiscount_code(), km.getDiscount_value(), km.getStart_date(),
					km.getEnd_date(), km.getStatus(), status };
			model.addRow(row);
		}
	}

	// load
	public void loadDanhSachKhuyenMai() {
		dtmKhuyenMai.setRowCount(0);
		ArrayList<KhuyenMai> dskm = KhuyenMaiBUS.getDanhSachKhuyenMai();
		for (KhuyenMai km : dskm) {
			Object[] rowData = new Object[] { km.getDiscount_code(), km.getDiscount_value(), km.getType(),
					km.getStart_date(), km.getEnd_date(), km.getStatus() };
			dtmKhuyenMai.addRow(rowData);
		}
	}

	// btnSua
	public void hienThiGiaoDienSua() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String discount_code = (String) table.getValueAt(selectedRow, 0);
			// Lấy thông tin khuyến mãi từ cơ sở dữ liệu dựa trên discount_code
			KhuyenMai km = KhuyenMaiBUS.getKhuyenMaiByDiscountCode(discount_code);
			System.out.println("ten :" + discount_code);
			if (km != null) {
				// Hiển thị giao diện sửa thông tin khuyến mãi
				if (chiTietKhuyenMaiGUI == null || !chiTietKhuyenMaiGUI.isVisible()) {
					chiTietKhuyenMaiGUI = new ChiTietKhuyenMaiGUI(km, this);
				} else {
					chiTietKhuyenMaiGUI.toFront();
				}
				chiTietKhuyenMaiGUI.setVisible(true);
				chiTietKhuyenMaiGUI.requestFocus();
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin khuyến mãi", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một mã khuyến mãi để sửa.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnThem) {
			if (chiTietKhuyenMaiGUI == null || !chiTietKhuyenMaiGUI.isVisible()) {
				chiTietKhuyenMaiGUI = new ChiTietKhuyenMaiGUI(new KhuyenMai(), this);
			} else {
				chiTietKhuyenMaiGUI.toFront();
			}
			chiTietKhuyenMaiGUI.setVisible(true);
			chiTietKhuyenMaiGUI.requestFocus();
		} else if (e.getSource() == btnSua) {
			hienThiGiaoDienSua();
		} else if (e.getSource() == btnXoa) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
				String discount_code = (String) table.getValueAt(selectedRow, 0); // Lấy discount_code từ hàng được chọn
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa mã giảm giá này?",
						"Xác nhận xóa mã giảm giá", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					boolean success = KhuyenMaiBUS.deleteKhuyenMai(discount_code); // Gọi phương thức xóa từ
																					// KhuyenMaiBUS hoặc KhuyenmaiDAO
					if (success) {
						JOptionPane.showMessageDialog(null, "Xóa mã giảm giá thành công.");
						loadDanhSachKhuyenMai(); // Sau khi xóa thành công, cập nhật lại danh sách mã giảm giá
					} else {
						JOptionPane.showMessageDialog(null, "Xóa mã giảm giá thất bại.");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một mã giảm giá để xóa.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnNhapExcel) {
			importExcel();
			// Xử lý khi button "Nhập excel" được nhấn
		} else if (e.getSource() == btnXuatExcel) {
			try {
				exportExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Xử lý khi button "Xuất excel" được nhấn
		}
	}

	public void exportExcel() throws IOException {
		ArrayList<KhuyenMai> dsnv = KhuyenMaiBUS.getDanhSachKhuyenMai();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dskm.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Danh sách nhân viên");

			// Ghi header
			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("name");
			headerRow.createCell(1).setCellValue("discount_value");
			headerRow.createCell(2).setCellValue("type");
			headerRow.createCell(3).setCellValue("start_date");
			headerRow.createCell(4).setCellValue("end_date");

			// Ghi thông tin nhân viên
			int rowNum = 1;
			for (KhuyenMai nv : dsnv) {
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(nv.getDiscount_code());
				row.createCell(1).setCellValue(nv.getDiscount_value());
				row.createCell(2).setCellValue(nv.getType());
				row.createCell(3).setCellValue(nv.getStart_date());
				row.createCell(4).setCellValue(nv.getEnd_date());
			}

			wb.write(fileOutputStream);
			wb.close();
			JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void importExcel() {
		try {
			ArrayList<KhuyenMai> dsnv = new ArrayList<>();

			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
			fileChooser.setFileFilter(filter);

			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();

				FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsoluteFile());
				XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
				XSSFSheet sheet = wb.getSheetAt(0); // Lất sheet 0 của excel
				FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator(); // Lấy giá trị các
																										// cột

				// Duyệt qua từng hàng trong sheet
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						if (!checkHeaderImportExcel(row)) {
							JOptionPane.showMessageDialog(null, "Lỗi hàng đầu tiên không đúng định dạng!",
									"Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
							return;
						}
						continue;
					}

					// Duyệt qua từng ô trong 1 hàng
					KhuyenMai mk = new KhuyenMai();
					for (Cell cell : row) {
						int columnIndex = cell.getColumnIndex();
						try {
							switch (columnIndex) {
							case 0:
								System.out.println(0);
								km.setDiscount_code(cell.getStringCellValue());
								break;
							case 1:
								System.out.println(1);
								km.setDiscount_value((int) cell.getNumericCellValue());
								break;
							case 2:
								System.out.println(2);
								km.setType(cell.getStringCellValue());
								break;
							case 3:
								System.out.println(3);
								km.setStart_date(cell.getStringCellValue());
								break;
							case 4:
								System.out.println(4);
								km.setEnd_date(cell.getStringCellValue());
								break;
							}
							System.out.println(km.getDiscount_code());

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"Xảy ra lỗi định dạng dữ liệu, vui lòng kiểm tra lại file excel!", "Thông báo lỗi",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					dsnv.add(km);
				}

				// Ghi dữ liệu vào db
				if (KhuyenMaiBUS.insertKhuyenMai(dsnv)) {
					loadDanhSachKhuyenMai();
					JOptionPane.showMessageDialog(null, "Đã import dữ liệu từ file excel vào hệ thống thành công!",
							"Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Có lỗi khi import dữ liệu từ file excel vào hệ thống!",
							"Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		} catch (Exception e2) {
			// Xử lý ngoại lệ ở đây nếu cần
		}
	}

	public boolean checkHeaderImportExcel(Row row) {
		String[] expectedHeaders = { "name", "discount_value", "type", "start_date", "end_date" };
		boolean headerMatched = true;

		for (int i = 0; i < expectedHeaders.length; i++) {
			Cell cell = row.getCell(i);
			if (cell == null || !cell.getStringCellValue().trim().equals(expectedHeaders[i])) {
				System.out.println(cell);
				headerMatched = false;
				break;
			}
		}

		return headerMatched;
	}
}
