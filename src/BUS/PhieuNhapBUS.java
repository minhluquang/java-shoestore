package BUS;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;

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

import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhap;
import DTO.PhieuNhap;
import DTO.SanPhamDTO;
import GUI.MyApp;

public class PhieuNhapBUS {
	private static ArrayList<PhieuNhap> dspn;

	public static ArrayList<PhieuNhap> getDanhSachPhieuNhap() {
		dspn = PhieuNhapDAO.getDanhSachPhieuNhap();
		return dspn;
	}

	public static String getTenNhanVienById(int id) {
		return PhieuNhapDAO.getTenNhanVienById(id);
	}

	public static PhieuNhap getPhieuNhapById(int id) {
		PhieuNhap p = PhieuNhapDAO.getPhieuNhapById(id);
		return p;
	}

	public static int generate_Id() {
		return PhieuNhapDAO.generrate_Id();
	}

	public static double getThanhTien(int soLuong, int gia) {

		return soLuong * 1.0 * gia;
	}

	public static String renderTongSoTien(JTable table) {
		double sum = 0;
		int rowCount = table.getRowCount();
		int lastColumnIndex = table.getColumnCount() - 1;

		for (int i = 0; i < rowCount; i++) {
			Object lastColumnValue = table.getValueAt(i, lastColumnIndex);
			sum += Double.parseDouble((String) lastColumnValue);
		}
		String formattedNumber = String.format("%.0f", sum);
		return formattedNumber;
	}

	public static boolean create_GoodsReceipts(int receipt_id, String date, int supplier_id, int staff_id) {
		return PhieuNhapDAO.create_GoodsReceipts(receipt_id, date, supplier_id, staff_id);
	}

	public static int tinhTongTien(int receipt_id) {
		return PhieuNhapDAO.tinhTongTien(receipt_id);
	}

	public static boolean update_Total_Price(int receipt_id) {
		return PhieuNhapDAO.update_Total_Price(receipt_id);
	}

	public static String getInPutPriceByProductId(String product_id) {
		return PhieuNhapDAO.getInPutPriceByProductId(product_id);
	}

	public static String tinhGiaNhap(int giaBan) {
		int res = (int) (giaBan * (1 - 0.2));
		System.out.println(res);
		return "" + res;
	}

	public static ArrayList<SanPhamDTO> searchObjectById(String enteredText) {
		return PhieuNhapDAO.searchObjectById(enteredText);
	}

	public static ArrayList<PhieuNhap> searchByDays(String bd, String kt, String query) {
		return PhieuNhapDAO.searchByDays(bd, kt, query);
	}

	public static void xuatPdf(int receipt_id) throws FileNotFoundException {
		String path = "pdf/goodsreceipt_" + receipt_id + ".pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);
		PhieuNhap pn = PhieuNhapBUS.getPhieuNhapById(receipt_id);
		String dateString = pn.getDate();

		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

		SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = "";
		try {
			Date date = originalFormat.parse(dateString);
			formattedDate = newFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Paragraph storeTitle = new Paragraph("Cua hang ban giay Shopgiay88").setBold().setFontSize(16);
		storeTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph storeAddress = new Paragraph("DC: 273 An Duong Vuong, P.3, Q.5, Tp. HCM");
		storeAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph phoneNumberAddress = new Paragraph("SDT: 028.392.44.690");
		phoneNumberAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

		Paragraph invoiceTitle = new Paragraph("HOA DON NHAP HANG").setBold().setFontSize(16).setMarginTop(20);
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
		table.addCell(new Cell().add(new Paragraph("Ngay tao: " + formattedDate)).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(new Paragraph("Ma so: " + receipt_id).setBorder(Border.NO_BORDER)));
		table.addCell(new Cell()
				.add(new Paragraph(
						"Nhan vien: " + NhanVienBUS.getNhanVienByAccountID(MyApp.user.getAccountId()).getFull_name()))
				.setBorder(Border.NO_BORDER));

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
		ArrayList<ChiTietPhieuNhap> arr = ChiTietPhieuNhapBUS.getDanhSachChiTietPhieuNhap(receipt_id);
		for (ChiTietPhieuNhap p : arr) {
			Cell cellBody1 = new Cell();
			Cell cellBody2 = new Cell();
			Cell cellBody3 = new Cell();
			cellBody1.add(new Paragraph(SanPhamBUS.getTenSanPhamById(p.getProduct_id()))).setBorder(new SolidBorder(1));
			cellBody2.add(new Paragraph(p.getQuantity() + "")).setBorder(new SolidBorder(1));
			cellBody3.add(new Paragraph(p.getInput_price() + "")).setBorder(new SolidBorder(1));

			cellBody2.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
			cellBody3.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);

			tableBillDetail.addCell(cellBody1);
			tableBillDetail.addCell(cellBody2);
			tableBillDetail.addCell(cellBody3);
		}

		document.add(tableBillDetail);

		// Phan bottom
		Table tableBottom = new Table(twocolumnWidth);
		tableBottom.setMarginTop(20);

		tableBottom.addCell(new Cell(1, 2).add(new LineSeparator(new DottedLine())));

		tableBottom.addCell(new Cell().add(new Paragraph("TONG TIEN")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph(pn.getTotal_price() + "")).setBorder(Border.NO_BORDER));

		document.add(tableBottom);

		document.close();
	}
}
