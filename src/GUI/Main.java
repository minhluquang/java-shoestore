package GUI;

import java.io.FileNotFoundException;

import javax.swing.UIManager;

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


public class Main {	
    public static void main(String[] args) throws FileNotFoundException  {
    	try {
    		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    		DangNhapDangKyGUI dangNhapDangKyGUI = new DangNhapDangKyGUI();
        	dangNhapDangKyGUI.setVisible(true);  
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
