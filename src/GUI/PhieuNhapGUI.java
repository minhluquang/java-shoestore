package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

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
	private JButton btnNhapExcel;
	private JButton btnXuatExcel;
	private DefaultTableModel defaultTableModel;

	private static ChiTietPhieuNhapGUI chiTietPhieuNhap;
	private static ChiTietNhaCungCapGUI chiTietNhanVienGUI;

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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date defaultDate = calendar.getTime();

		// Tạo một SpinnerDateModel với giá trị mặc định là ngày hiện tại
		SpinnerDateModel model = new SpinnerDateModel(defaultDate, null, null, Calendar.DAY_OF_MONTH);
		SpinnerDateModel model_end = new SpinnerDateModel(defaultDate, null, null, Calendar.DAY_OF_MONTH);
		JSpinner spinner = new JSpinner(model);
		// Định dạng ngày tháng năm cho JSpinner
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
		spinner.setEditor(editor);
		// Đặt font và border cho spinner
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setBorder(new TitledBorder(null, "Ngày bắt đầu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// Đặt kích thước cho spinner
		spinner.setPreferredSize(new Dimension(100, 40));
		// Thêm spinner vào panel
		panel_1.add(spinner);

		// Tạo một SpinnerDateModel với giá trị mặc định là ngày hiện tại

		JSpinner spinner_end = new JSpinner(model_end);

		// Định dạng ngày tháng năm cho JSpinner
		JSpinner.DateEditor editor_end = new JSpinner.DateEditor(spinner_end, "dd/MM/yyyy");
		spinner_end.setEditor(editor_end);

		// Đặt font và border cho spinner
		spinner_end.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner_end
				.setBorder(new TitledBorder(null, "Ngày kết thúc", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// Đặt kích thước cho spinner
		spinner_end.setPreferredSize(new Dimension(100, 40));

		// Thêm spinner vào panel
		panel_1.add(spinner_end);

		txtTmKim = new JTextField();
		txtTmKim.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtTmKim.setMinimumSize(new Dimension(250, 19));
		txtTmKim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTmKim.setColumns(20);
		panel_1.add(txtTmKim);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnFind = new JButton("Tìm Kiếm");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date start = (Date) spinner.getValue();
				Date end = (Date) spinner_end.getValue();
				System.out.println(start);
				System.out.println(end);
				int result = start.compareTo(end);
				System.out.println(result);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được lớn hơn ngày kết thúc !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate_start = sdf.format(start);
				String formattedDate_end = sdf.format(end);

				String query = txtTmKim.getText();
				ArrayList<PhieuNhap> arr = PhieuNhapBUS.searchByDays(formattedDate_start, formattedDate_end, query);
				loadDanhSachPhieuNhap(arr);
			}
		});
		btnFind.setPreferredSize(new Dimension(150, 21));
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setIcon(new ImageIcon(absolutePath + "/src/images/icons/search.png"));
		btnFind.setFocusable(false);
		btnFind.setBackground(Color.WHITE);
		panel_2.add(btnFind);

		JButton btnReset_1 = new JButton("Làm mới");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDanhSachPhieuNhap();
				txtTmKim.setText("");
			}
		});
		btnReset_1.setPreferredSize(new Dimension(150, 21));
		btnReset_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset_1.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		btnReset_1.setFocusable(false);
		btnReset_1.setBackground(Color.WHITE);
		panel_2.add(btnReset_1);

		JPanel pnlTopBottom = new JPanel();
		pnlTopBottom.setBackground(Color.WHITE);
		pnlSearch.add(pnlTopBottom, BorderLayout.NORTH);
		pnlTopBottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		btnChiTietPhieuNhap = new JButton("Chi Tiết");
		btnChiTietPhieuNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChiTietPhieuNhap.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
		btnChiTietPhieuNhap.setPreferredSize(new Dimension(150, 40));
		btnChiTietPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTietPhieuNhap.setFocusable(false);
		btnChiTietPhieuNhap.setBackground(Color.WHITE);
		pnlTopBottom.add(btnChiTietPhieuNhap);

		btnNhapExcel = new JButton("Nhập excel");
		btnNhapExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhapExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnNhapExcel.setPreferredSize(new Dimension(150, 40));
		btnNhapExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhapExcel.setFocusable(false);
		btnNhapExcel.setBackground(Color.WHITE);
		pnlTopBottom.add(btnNhapExcel);
		btnNhapExcel.addActionListener(this);

		btnXuatExcel = new JButton("Xuất excel");
		btnXuatExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXuatExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnXuatExcel.setPreferredSize(new Dimension(150, 40));
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXuatExcel.setFocusable(false);
		btnXuatExcel.setBackground(Color.WHITE);
		pnlTopBottom.add(btnXuatExcel);
		btnXuatExcel.addActionListener(this);

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
			try {
				exportExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	public void loadDanhSachPhieuNhap(ArrayList<PhieuNhap> dspn) {
		defaultTableModel.setRowCount(0);
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

	public void exportExcel() throws IOException {
		ArrayList<PhieuNhap> dsnv = PhieuNhapBUS.getDanhSachPhieuNhap();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dspn.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Danh sách nhân viên");

			// Ghi header
			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("receipt_id");
			headerRow.createCell(1).setCellValue("supplier");
			headerRow.createCell(2).setCellValue("staff_id");
			headerRow.createCell(3).setCellValue("total_price");
			headerRow.createCell(4).setCellValue("date");

			// Ghi thông tin nhân viên
			int rowNum = 1;
			for (PhieuNhap nv : dsnv) {
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(nv.getReceipt_id());
				row.createCell(1).setCellValue(NhaCungCapBUS.getTenNhaCungCapById(nv.getSupplier_id()));
				row.createCell(2).setCellValue(PhieuNhapBUS.getTenNhanVienById(nv.getStaff_id()));
				row.createCell(3).setCellValue(nv.getTotal_price());
				row.createCell(4).setCellValue(nv.getDate());
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

	public static void xuatPdf() throws FileNotFoundException {
		String path = "invoice.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);

		Paragraph storeTitle = new Paragraph("Cua hang ban giay Shopgiay88").setBold().setFontSize(16);
		storeTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph storeAddress = new Paragraph("DC: 273 An Duong Vuong, P.3, Q.5, Tp. HCM");
		storeAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph phoneNumberAddress = new Paragraph("SDT: 028.392.44.690");
		phoneNumberAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

		Paragraph invoiceTitle = new Paragraph("HOA DON BAN HANG").setBold().setFontSize(16).setMarginTop(20);
		invoiceTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

		document.add(storeTitle);
		document.add(storeAddress);
		document.add(phoneNumberAddress);
		document.add(invoiceTitle);

		// bill info
		float twocol = 285f;
		float twocol150 = twocol + 150f;
		float twocolumnWidth[] = { twocol150, twocol };

		Table table = new Table(twocolumnWidth);
		table.setMarginTop(20);
		table.addCell(new Cell().add(new Paragraph("Ngay tao: 2/5/2024")).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(new Paragraph("Ma so: 111")).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(new Paragraph("Thu ngan: Lu Quang Minh")).setBorder(Border.NO_BORDER));

		document.add(table);

		// bill detail
		float threecol = 190f;
		float threeColumnWidth[] = { threecol, threecol, threecol };
		Table tableBillDetail = new Table(threeColumnWidth);
		tableBillDetail.setMarginTop(20);

		// header
		Cell cellHeader1 = new Cell().add(new Paragraph("Ten san pham")).setBorder(new SolidBorder(1));
		Cell cellHeader2 = new Cell().add(new Paragraph("So luong")).setBorder(new SolidBorder(1));
		Cell cellHeader3 = new Cell().add(new Paragraph("Don gia")).setBorder(new SolidBorder(1));

		tableBillDetail.addCell(cellHeader1);
		tableBillDetail.addCell(cellHeader2);
		tableBillDetail.addCell(cellHeader3);

		// body
		// ROW 1
		Cell cellBody1 = new Cell().add(new Paragraph("Nike Air Force 1")).setBorder(new SolidBorder(1));
		Cell cellBody2 = new Cell().add(new Paragraph("2")).setBorder(new SolidBorder(1));
		Cell cellBody3 = new Cell().add(new Paragraph("1685000")).setBorder(new SolidBorder(1));

		cellBody2.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
		cellBody3.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);

		tableBillDetail.addCell(cellBody1);
		tableBillDetail.addCell(cellBody2);
		tableBillDetail.addCell(cellBody3);

		// ROW 2
		Cell cellBody4 = new Cell().add(new Paragraph("Converse Chunk Taylor")).setBorder(new SolidBorder(1));
		Cell cellBody5 = new Cell().add(new Paragraph("1")).setBorder(new SolidBorder(1));
		Cell cellBody6 = new Cell().add(new Paragraph("800000")).setBorder(new SolidBorder(1));

		cellBody5.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
		cellBody6.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);

		tableBillDetail.addCell(cellBody4);
		tableBillDetail.addCell(cellBody5);
		tableBillDetail.addCell(cellBody6);

		document.add(tableBillDetail);

		// Phan bottom
		Table tableBottom = new Table(twocolumnWidth);
		tableBottom.setMarginTop(20);
		tableBottom.addCell(new Cell().add(new Paragraph("Tien hang:")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph("4170000")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph("Giam gia:")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph("50000")).setBorder(Border.NO_BORDER));

		tableBottom.addCell(new Cell(1, 2).add(new LineSeparator(new DottedLine())));

		tableBottom.addCell(new Cell().add(new Paragraph("TONG TIEN")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph("4120000")).setBorder(Border.NO_BORDER));

		document.add(tableBottom);

		document.close();
	}
}
