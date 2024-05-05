	package GUI;
	
	import javax.swing.JPanel;
	import java.awt.BorderLayout;
	import javax.swing.JTextField;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
	import javax.swing.ImageIcon;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Dimension;
	
	import javax.swing.UIManager;
	import javax.swing.SwingConstants;
	import java.awt.FlowLayout;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.ThongKeDoanhThuBUS;
import BUS.ThongKeTonKhoBUS;
import DTO.ThongKeDoanhThuDTO;
import DTO.ThongKeTonKhoDTO;

import javax.swing.ListSelectionModel;
	import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
	
	public class ThongKeTongQuanGUI extends JPanel {
		public String absolutePath = new File("").getAbsolutePath();
	
		private static final long serialVersionUID = 1L;
		private JTable tblTongQuan;
	
		private DefaultTableModel dtmTongQuan;
	
	
		/**
		 * Create the panel.
		 */
		public ThongKeTongQuanGUI() {
			setLayout(new BorderLayout(0, 0));
			
			JPanel pnlTop = new JPanel();
			add(pnlTop, BorderLayout.NORTH);
			pnlTop.setLayout(new GridLayout(0, 3, 30, 0));
			pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
	
			
			JPanel pnlSP = new JPanel();
			pnlSP.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlSP);
			pnlSP.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconSP = new JLabel("");
			lblIconSP.setIcon(new ImageIcon(absolutePath + "/src/images/icons/sp.png"));
			pnlSP.add(lblIconSP, BorderLayout.WEST);
			lblIconSP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlSPContent = new JPanel();
			pnlSPContent.setBackground(new Color(255, 255, 255));
			pnlSP.add(pnlSPContent);
			pnlSPContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlSPContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblSp = new JLabel("100");
			lblSp.setText(ThongKeDoanhThuBUS.getTotalProduct()+"");
			lblSp.setHorizontalAlignment(SwingConstants.LEFT);
			pnlSPContent.add(lblSp);
			lblSp.setForeground(new Color(70, 130, 180));
			lblSp.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			JLabel lblNewLabel_1 = new JLabel("Sản phẩm hiện có trong kho");
			pnlSPContent.add(lblNewLabel_1);
			lblNewLabel_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlKH = new JPanel();
			pnlKH.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlKH);
			pnlKH.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconKH = new JLabel("");
			lblIconKH.setIcon(new ImageIcon(absolutePath + "/src/images/icons/kh.png"));
			pnlKH.add(lblIconKH, BorderLayout.WEST);
			lblIconKH.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlKHContent = new JPanel();
			pnlKH.add(pnlKHContent);
			pnlKHContent.setBackground(new Color(255, 255, 255));
			pnlKHContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlKHContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblKH = new JLabel("100");
			lblKH.setText(ThongKeDoanhThuBUS.getTotalCustomer()+"");
			pnlKHContent.add(lblKH);
			lblKH.setForeground(new Color(70, 130, 180));
			lblKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			
			JLabel lblNewLabel_1_1 = new JLabel("Khách hàng từ trước tới nay");
			pnlKHContent.add(lblNewLabel_1_1);
			lblNewLabel_1_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlNV = new JPanel();
			pnlNV.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlNV);
			pnlNV.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconNV = new JLabel("");
			lblIconNV.setIcon(new ImageIcon(absolutePath + "/src/images/icons/nv.png"));
			pnlNV.add(lblIconNV, BorderLayout.WEST);
			lblIconNV.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlNVContent = new JPanel();
			pnlNV.add(pnlNVContent);
			pnlNVContent.setBackground(new Color(255, 255, 255));
			pnlNVContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlNVContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblNV = new JLabel("");
			lblNV.setText(ThongKeDoanhThuBUS.getTotalStaff()+"");
			pnlNVContent.add(lblNV);
			lblNV.setForeground(new Color(70, 130, 180));
			lblNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Nhân viên đang hoạt động");
			pnlNVContent.add(lblNewLabel_1_1_1);
			lblNewLabel_1_1_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlCenter = new JPanel();
			add(pnlCenter, BorderLayout.CENTER);
		
			pnlCenter.setBorder(BorderFactory.createEmptyBorder(30, 15, 10, 15));
			
			tblTongQuan = new JTable();
			
			tblTongQuan.setBorder(null);
			tblTongQuan.setSelectionBackground(new Color(232, 57, 95));
			tblTongQuan.setRowHeight(25);
			tblTongQuan.setIntercellSpacing(new Dimension(0, 0));
			tblTongQuan.setFocusable(false);
			
			dtmTongQuan = new DefaultTableModel(new Object[] {"Ngày","Tổng đơn nhập", "Vốn","Tổng hóa đơn", "Doanh thu", "Lợi nhuận"}, 0);
			tblTongQuan.setModel(dtmTongQuan);
			tblTongQuan.setDefaultEditor(Object.class, null);
			tblTongQuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			
			displayData();
			pnlCenter.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(tblTongQuan);
			scrollPane.setBorder(null);
			scrollPane.setBackground(new Color(255, 255, 255));
			pnlCenter.add(scrollPane, BorderLayout.CENTER);
		
			((DefaultTableCellRenderer)tblTongQuan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			tblTongQuan.setDefaultRenderer(Object.class, centerRenderer);
			
			tblTongQuan.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tblTongQuan.getTableHeader().setOpaque(false);
			tblTongQuan.getTableHeader().setBackground(new Color(36,136,203));
			tblTongQuan.getTableHeader().setForeground(new Color(255,255,255));
			tblTongQuan.setRowHeight(25);
			
			JLabel lblNewLabel =new JLabel("Thống kê doanh thu 7 ngày gần nhất");
			lblNewLabel.setForeground(new Color(102, 153, 204));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pnlCenter.add(lblNewLabel, BorderLayout.SOUTH);
			lblNewLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 30));
			
			JPanel pnl_button = new JPanel();
			pnlCenter.add(pnl_button, BorderLayout.NORTH);
			pnl_button.setLayout(new GridLayout(0, 6, 50, 0));
			pnl_button.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 100));
			
			JButton btn_reset = new JButton("Reset");
			
			pnl_button.add(btn_reset);
			
			JButton btn_excel = new JButton("Xuất Excel");
			btn_excel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						exportExcel();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			});
			pnl_button.add(btn_excel);
			
			JPanel pnl_bottom = new JPanel();
			add(pnl_bottom, BorderLayout.SOUTH);
			pnl_bottom.setLayout(new GridLayout(2, 3, 20, 20));
			pnl_bottom.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
			JPanel pnlNgay = new JPanel();
			pnl_bottom.add(pnlNgay);
			pnlNgay.setLayout(new BorderLayout(10, 10));
			
			JLabel lblNewLabel_2 = new JLabel("Tổng số ngày:");
			pnlNgay.add(lblNewLabel_2, BorderLayout.WEST);
			lblNewLabel_2.setForeground(new Color(102, 153, 204));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			JLabel lbl_ngay = new JLabel("7");
			lbl_ngay.setText(calculateColumnTotal()[0]+"");
			lbl_ngay.setForeground(new Color(102, 153, 204));
			lbl_ngay.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlNgay.add(lbl_ngay);
			
			JPanel pnlPhieuNhap = new JPanel();
			pnl_bottom.add(pnlPhieuNhap);
			pnlPhieuNhap.setLayout(new BorderLayout(10, 0));
			
			JLabel lblNewLabel_2_1 = new JLabel("Tổng phiếu nhập: ");
			lblNewLabel_2_1.setForeground(new Color(102, 153, 204));
			lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlPhieuNhap.add(lblNewLabel_2_1, BorderLayout.WEST);
			
			JLabel lbl_phieuNhap = new JLabel("7");
			lbl_phieuNhap.setText(calculateColumnTotal()[1]+"");
			lbl_phieuNhap.setForeground(new Color(102, 153, 204));
			lbl_phieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlPhieuNhap.add(lbl_phieuNhap);
			
			JPanel pnlVon = new JPanel();
			pnl_bottom.add(pnlVon);
			pnlVon.setLayout(new BorderLayout(10, 0));
			
			JLabel lblVonText = new JLabel("Tổng vốn: ");
			lblVonText.setForeground(new Color(102, 153, 204));
			lblVonText.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlVon.add(lblVonText, BorderLayout.WEST);
			
			JLabel lbl_von = new JLabel("7");
			lbl_von.setText(calculateColumnTotal()[2]+"");
			lbl_von.setForeground(new Color(102, 153, 204));
			lbl_von.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlVon.add(lbl_von);
			
			JPanel pnlHoaDon = new JPanel();
			pnl_bottom.add(pnlHoaDon);
			pnlHoaDon.setLayout(new BorderLayout(10, 0));
			
			JLabel lblNewLabel_2_3 = new JLabel("Tổng hóa đơn: ");
			lblNewLabel_2_3.setForeground(new Color(102, 153, 204));
			lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlHoaDon.add(lblNewLabel_2_3, BorderLayout.WEST);
			
			JLabel lbl_hoaDon = new JLabel("7");
			lbl_hoaDon.setText(calculateColumnTotal()[3]+"");
			lbl_hoaDon.setForeground(new Color(102, 153, 204));
			lbl_hoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlHoaDon.add(lbl_hoaDon);
			
			JPanel pnlDoanhThu = new JPanel();
			pnl_bottom.add(pnlDoanhThu);
			pnlDoanhThu.setLayout(new BorderLayout(10, 0));
			
			JLabel lblNewLabel_2_4 = new JLabel("Tổng doanh thu: ");
			lblNewLabel_2_4.setForeground(new Color(102, 153, 204));
			lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlDoanhThu.add(lblNewLabel_2_4, BorderLayout.WEST);
			
			JLabel lbl_doanhThu = new JLabel("7");
			lbl_doanhThu.setText(calculateColumnTotal()[4]+"");
			lbl_doanhThu.setForeground(new Color(102, 153, 204));
			lbl_doanhThu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlDoanhThu.add(lbl_doanhThu);
			
			JPanel pnlLoiNhuan = new JPanel();
			pnl_bottom.add(pnlLoiNhuan);
			pnlLoiNhuan.setLayout(new BorderLayout(10, 0));
			
			JLabel lbltext = new JLabel("Tổng lợi nhuận: ");
			lbltext.setForeground(new Color(102, 153, 204));
			lbltext.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlLoiNhuan.add(lbltext, BorderLayout.WEST);
			
			JLabel lbl_loiNhuan = new JLabel("7");
			lbl_loiNhuan.setForeground(new Color(102, 153, 204));
			lbl_loiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbl_loiNhuan.setText(calculateColumnTotal()[5]+"");
			pnlLoiNhuan.add(lbl_loiNhuan);
			
			
			btn_reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dtmTongQuan.setRowCount(0);
					displayData();
					lbl_ngay.setText(calculateColumnTotal()[0]+"");
					lbl_phieuNhap.setText(calculateColumnTotal()[1]+"");
					lbl_von.setText(calculateColumnTotal()[2]+"");
					lbl_hoaDon.setText(calculateColumnTotal()[3]+"");
					lbl_doanhThu.setText(calculateColumnTotal()[4]+"");
					lbl_loiNhuan.setText(calculateColumnTotal()[5]+"");
					lblSp.setText(ThongKeDoanhThuBUS.getTotalProduct()+"");
					lblKH.setText(ThongKeDoanhThuBUS.getTotalCustomer()+"");
					lblNV.setText(ThongKeDoanhThuBUS.getTotalStaff()+"");
				}
			});		
			
			
			
	
		}
		
		public void displayData() {
	        List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.getThongKeDoanhThu();
	        for (ThongKeDoanhThuDTO dto : dataList) {
	            Object[] row = { dto.getThoigian(),dto.getSlDonNhap(), dto.getVon(),dto.getSlHoaDon(), dto.getDoanhthu(), dto.getLoinhuan() };
	            dtmTongQuan.addRow(row);
	        }
	    }
		
		public long[] calculateColumnTotal() {
		    int numRows = tblTongQuan.getRowCount();
		    int numCols = tblTongQuan.getColumnCount();

		    // Mảng để lưu trữ tổng của từng cột
		    long[] columnTotals = new long[numCols];

		    // Duyệt qua từng dòng trong bảng
		    for (int row = 0; row < numRows; row++) {
		        // Duyệt qua từng cột trong dòng và cộng dồn vào tổng của cột tương ứng
		        for (int col = 0; col < numCols; col++) {
		            // Lấy giá trị của ô hiện tại trong bảng
		            Object cellValue = tblTongQuan.getValueAt(row, col);
		            // Kiểm tra nếu giá trị là số (hoặc có thể chuyển đổi thành số)
		            if (cellValue instanceof Number) {
		                double value = ((Number) cellValue).doubleValue();
		                // Cộng dồn vào tổng của cột
		                columnTotals[col] += value;
		            } else {
		            	columnTotals[col] +=1;
					}
		        }
		    }
		    return columnTotals;
	}
		public void exportExcel() throws IOException {
			List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.getThongKeDoanhThu();
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(absolutePath + "/excel/thong_ke_7_ngay.xlsx");
			    XSSFWorkbook wb = new XSSFWorkbook();
			    XSSFSheet sheet = wb.createSheet("Doanh thu 7 ngày gần nhất");
			    XSSFRow row = null;
			    Cell cell = null;
			    
			    // Ghi header
			    XSSFRow headerRow = sheet.createRow(0);
			    headerRow.createCell(0).setCellValue("Ngày");
			    headerRow.createCell(1).setCellValue("Tổng đơn nhập");
			    headerRow.createCell(2).setCellValue("Vốn");
			    headerRow.createCell(3).setCellValue("Tổng hóa đơn");
			    headerRow.createCell(4).setCellValue("Doanh thu");
			    headerRow.createCell(5).setCellValue("Lợi nhuận");
			    
			   
			    int rowNum = 1;
			    for (ThongKeDoanhThuDTO list : dataList) {
			    	 row = sheet.createRow(rowNum++);
			    	row.createCell(0).setCellValue(list.getThoigian());
			    	row.createCell(1).setCellValue(list.getSlDonNhap());
			    	row.createCell(2).setCellValue(list.getVon());
			    	row.createCell(3).setCellValue(list.getSlHoaDon());
			    	row.createCell(4).setCellValue(list.getDoanhthu());
			    	row.createCell(4).setCellValue(list.getLoinhuan());
			    }
			    
			    wb.write(fileOutputStream);
			    wb.close();
			    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
			    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
			}
		}
}
