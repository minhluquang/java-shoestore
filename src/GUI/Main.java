package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.print.Doc;
import javax.swing.UIManager;

import org.apache.poi.xddf.usermodel.BlackWhiteMode;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Line;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;


public class Main {	
    public static void main(String[] args) throws FileNotFoundException  {
    	try {
    		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    		DangNhapDangKyGUI dangNhapDangKyGUI = new DangNhapDangKyGUI();
        	dangNhapDangKyGUI.setVisible(true);  
        	xuatPdf();
		} catch (Exception e) {
			e.printStackTrace();
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
        float twocolumnWidth[] = {twocol150, twocol};
        
        Table table = new Table(twocolumnWidth);
        table.setMarginTop(20);
        table.addCell(new Cell().add(new Paragraph("Ngay tao: 2/5/2024")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Ma so: 111")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Thu ngan: Lu Quang Minh")).setBorder(Border.NO_BORDER));
        
        document.add(table);
        
        // bill detail
        float threecol = 190f;
        float threeColumnWidth[] = {threecol, threecol, threecol};
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
