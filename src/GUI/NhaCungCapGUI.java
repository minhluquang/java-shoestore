package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCap;

public class NhaCungCapGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public String absolutePath = new File("").getAbsolutePath();
	private JTextField txtTmKim;
	private JTable table;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnNhapExcel;
	private JButton btnXuatExcel;
	private DefaultTableModel defaultTableModel;
	NhaCungCap ncc = new NhaCungCap();
	private static ChiTietNhaCungCapGUI ChiTietNhaCungCap;

	/**
	 * Create the panel.
	 */
	public NhaCungCapGUI() {
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

		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		txtTmKim = new JTextField();
		txtTmKim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String str = txtTmKim.getText();
				ArrayList<NhaCungCap> arr = NhaCungCapBUS.getListNCCByQuery(str);
				loadDanhSachNhaCungCap(arr);
			}
		});
		txtTmKim.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtTmKim.setMinimumSize(new Dimension(250, 19));
		txtTmKim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKim.setColumns(10);
		panel_1.add(txtTmKim);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnReload = new JButton("Làm mới ");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTmKim.setText("");
				loadDanhSachNhaCungCap();
			}
		});
		btnReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReload.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		btnReload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReload.setFocusable(false);
		btnReload.setBackground(new Color(255, 255, 255));
		panel_2.add(btnReload);

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

//		=================================TABLE================================
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);

		defaultTableModel = new DefaultTableModel(
				new Object[] { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ" }, 0);
		table.setModel(defaultTableModel);
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xuLyClickTable();
			}
		});
		;

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane, BorderLayout.NORTH);

		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		loadDanhSachNhaCungCap();
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnThem) {
			if (ChiTietNhaCungCap == null || !ChiTietNhaCungCap.isVisible()) {
				ChiTietNhaCungCap = new ChiTietNhaCungCapGUI(new NhaCungCap(), this);
			} else {
				ChiTietNhaCungCap.toFront();
			}
			ChiTietNhaCungCap.setVisible(true);
			ChiTietNhaCungCap.requestFocus();

		} else if (e.getSource() == btnSua) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int maNcc = (int) defaultTableModel.getValueAt(selectedRow, 1);
			if (ChiTietNhaCungCap == null || !ChiTietNhaCungCap.isVisible()) {
				ChiTietNhaCungCap = new ChiTietNhaCungCapGUI(ncc, this);
			} else {
				ChiTietNhaCungCap.toFront();
			}
			ChiTietNhaCungCap.setVisible(true);
			ChiTietNhaCungCap.requestFocus();
		} else if (e.getSource() == btnXoa) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xóa", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int selected = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhà cung cấp vừa chọn không!", "",
					JOptionPane.YES_NO_OPTION);
			if (selected != 0)
				return;
			boolean success = NhaCungCapDAO.deletePublisher(ncc.getSupplier_id());
			if (!success) {
				JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thất bại !", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công !", "Thông báo",
						JOptionPane.CLOSED_OPTION);
				loadDanhSachNhaCungCap();
			}

		} else if (e.getSource() == btnXuatExcel) {
			try {
				exportExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == btnNhapExcel) {
			importExcel();
		}
	}

	public static void main(String[] args) {
		NhaCungCapGUI a = new NhaCungCapGUI();
		JFrame f = new JFrame();
		f.getContentPane().add(a);
		f.setVisible(true);
	}

	public void loadDanhSachNhaCungCap() {
		defaultTableModel.setRowCount(0);
		ArrayList<NhaCungCap> dsncc = NhaCungCapBUS.getDanhSachNhaCungCap();

		int rowNum = defaultTableModel.getRowCount();
		for (NhaCungCap n : dsncc) {
			if (n.getStatus() == 0) {
				continue;
			}
			int stt = ++rowNum;
			Object[] row = { stt, n.getSupplier_id(), n.getSupplier_name(), n.getSupplier_addresss() };
			defaultTableModel.addRow(row);
		}

	}

	public void loadDanhSachNhaCungCap(ArrayList<NhaCungCap> dsncc) {
		defaultTableModel.setRowCount(0);
		int rowNum = defaultTableModel.getRowCount();
		for (NhaCungCap n : dsncc) {
			if (n.getStatus() == 0) {
				continue;
			}
			int stt = ++rowNum;
			Object[] row = { stt, n.getSupplier_id(), n.getSupplier_name(), n.getSupplier_addresss() };
			defaultTableModel.addRow(row);
		}

	}

	public void xuLyClickTable() {
		int row = table.getSelectedRow();
		if (row > -1) {
			ncc.setSupplier_id(((int) table.getValueAt(row, 1)));
			ncc.setSupplier_name((String) table.getValueAt(row, 2));
			ncc.setSupplier_addresss((String) table.getValueAt(row, 3));
		}
	}

	public void exportExcel() throws IOException {
		ArrayList<NhaCungCap> dsnv = NhaCungCapBUS.getDanhSachNhaCungCap();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dsncc.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Danh sách nhân viên");

			// Ghi header
			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("supplier_id");
			headerRow.createCell(1).setCellValue("supplier_name");
			headerRow.createCell(2).setCellValue("supplier_address");

			// Ghi thông tin nhân viên
			int rowNum = 1;
			for (NhaCungCap nv : dsnv) {
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(nv.getSupplier_id());
				row.createCell(1).setCellValue(nv.getSupplier_name());
				row.createCell(2).setCellValue(nv.getSupplier_addresss());

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
			ArrayList<NhaCungCap> dsnv = new ArrayList<>();

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
					NhaCungCap km = new NhaCungCap();
					for (Cell cell : row) {
						int columnIndex = cell.getColumnIndex();
						try {
							switch (columnIndex) {
							case 0:
								System.out.println(0);
								km.setSupplier_id((int) cell.getNumericCellValue());
								break;
							case 1:
								System.out.println(1);
								km.setSupplier_name(cell.getStringCellValue());
								break;
							case 2:
								System.out.println(2);
								km.setSupplier_addresss(cell.getStringCellValue());
								;
								break;
							}

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
				if (NhaCungCapBUS.insertPublisher(dsnv)) {
					loadDanhSachNhaCungCap();
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
		String[] expectedHeaders = { "supplier_id", "supplier_name", "supplier_email" };
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
