	package GUI;
	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
	
	import javax.swing.BorderFactory;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JSpinner;
	import javax.swing.JTable;
	import javax.swing.SpinnerDateModel;
	import javax.swing.SpinnerNumberModel;
	import javax.swing.SwingConstants;
	import javax.swing.table.DefaultTableCellRenderer;
	import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.ThongKeDoanhThuBUS;
	import DTO.ThongKeDoanhThuDTO;
	
	public class ThongKeTheoKhoangNgayGUI extends JPanel {
	
		private static final long serialVersionUID = 1L;
		private JTable table;
		private JTable tblDoanhThu;
		private DefaultTableModel dtmDoanhThu;
		public String absolutePath = new File("").getAbsolutePath();
		/**
		 * Create the panel.
		 */
		public ThongKeTheoKhoangNgayGUI() {
			this.setBorder(BorderFactory.createEmptyBorder(20, 20, 50, 50));
			setLayout(new BorderLayout(50, 50));
			JPanel pnl_top = new JPanel();
			add(pnl_top, BorderLayout.NORTH);
			pnl_top.setLayout(new GridLayout(0, 4, 40, 0));
	//		pnl_top.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			
			JPanel pnl_dateStart = new JPanel();
			pnl_top.add(pnl_dateStart);
			pnl_top.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 140));
			pnl_dateStart.setLayout(new BorderLayout(0, 0));
			JLabel lblNewLabel = new JLabel("Từ ngày: ");
			pnl_dateStart.add(lblNewLabel, BorderLayout.WEST);
	
			 Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.DAY_OF_MONTH, 1); // Set the day to 1
		        
		     SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MONTH);
			 JSpinner spn_dateStart = new JSpinner(model);
			 JSpinner.DateEditor de_spn_dateStart = new JSpinner.DateEditor(spn_dateStart, "dd/MM/yyyy");
		     spn_dateStart.setEditor(de_spn_dateStart);
			 pnl_dateStart.add(spn_dateStart);
			 

				JPanel pnl_dateFinish = new JPanel();
				pnl_top.add(pnl_dateFinish);
				pnl_top.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 140));
				pnl_dateFinish.setLayout(new BorderLayout(0, 0));
				JLabel lblNewLabel1 = new JLabel("Tới ngày: ");
				pnl_dateFinish.add(lblNewLabel1, BorderLayout.WEST);
		
				 Calendar calendar1 = Calendar.getInstance();
			     calendar1.set(Calendar.DAY_OF_MONTH, 1); // Set the day to 1
			        
			     SpinnerDateModel model1 = new SpinnerDateModel(calendar1.getTime(), null, null, Calendar.MONTH);
				 JSpinner spn_dateFinish = new JSpinner(model1);
				 JSpinner.DateEditor de_spn_dateFinish = new JSpinner.DateEditor(spn_dateFinish, "dd/MM/yyyy");
			     spn_dateFinish.setEditor(de_spn_dateFinish);
				 pnl_dateFinish.add(spn_dateFinish);
		        
		       
			
			
			
			
		        
		        JPanel pnl_tk = new JPanel();
		        pnl_top.add(pnl_tk);
		        pnl_tk.setLayout(new BorderLayout(0, 0));
		        
		        JButton btn_tk = new JButton("Thống kê");
		        
		        pnl_tk.add(btn_tk);
		        
		        JPanel pnl_xuat = new JPanel();
		        pnl_top.add(pnl_xuat);
		        pnl_xuat.setLayout(new BorderLayout(0, 0));
		        pnl_top.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));
		        
		        JButton btn_xuat = new JButton("Xuất Excel");
		        btn_xuat.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		Date startDate = (Date) spn_dateStart.getValue();
		        		 Date finishDate = (Date) spn_dateFinish.getValue();		        		 		        		 		        		    
		        		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        		 String dateStart = dateFormat.format(startDate);		    	        		
		        		 String dateFinish = dateFormat.format(finishDate);
		        		 
		        		 try {
							exportExcel(dateStart, dateFinish);
						} catch (Exception e2) {
							// TODO: handle exception
						}
		        	}
		        });
		       
		        	
		       
		        pnl_xuat.add(btn_xuat);
			
			JPanel pnl_center = new JPanel();
			add(pnl_center, BorderLayout.CENTER);
			
			tblDoanhThu = new JTable();
			
			tblDoanhThu.setBorder(null);
			tblDoanhThu.setSelectionBackground(new Color(232, 57, 95));
			tblDoanhThu.setRowHeight(25);
			tblDoanhThu.setIntercellSpacing(new Dimension(0, 0));
			tblDoanhThu.setFocusable(false);
			
			dtmDoanhThu = new DefaultTableModel(new Object[] {"Ngày","Tổng đơn nhập", "Vốn","Tổng hóa đơn", "Doanh thu", "Lợi nhuận"}, 0);
			tblDoanhThu.setModel(dtmDoanhThu);
			tblDoanhThu.setDefaultEditor(Object.class, null);
			pnl_center.setLayout(new BorderLayout(0, 0));
			tblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
	
			
			JScrollPane scrollPane = new JScrollPane(tblDoanhThu);
			scrollPane.setBorder(null);
			scrollPane.setBackground(new Color(255, 255, 255));
			pnl_center.add(scrollPane);
		
			((DefaultTableCellRenderer)tblDoanhThu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			tblDoanhThu.setDefaultRenderer(Object.class, centerRenderer);
			
			tblDoanhThu.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tblDoanhThu.getTableHeader().setOpaque(false);
			tblDoanhThu.getTableHeader().setBackground(new Color(36,136,203));
			tblDoanhThu.getTableHeader().setForeground(new Color(255,255,255));
			tblDoanhThu.setRowHeight(25);
			
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
			
			JLabel lbl_ngay = new JLabel("0");
			
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
			
			JLabel lbl_phieuNhap = new JLabel("0");
			
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
			
			JLabel lbl_von = new JLabel("0");
			
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
			
			JLabel lbl_hoaDon = new JLabel("0");
			
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
			
			JLabel lbl_doanhThu = new JLabel("0");
			
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
			
			JLabel lbl_loiNhuan = new JLabel("0");
			lbl_loiNhuan.setForeground(new Color(102, 153, 204));
			lbl_loiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnlLoiNhuan.add(lbl_loiNhuan);
			
			btn_tk.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
	        		 dtmDoanhThu.setRowCount(0);
	        		 Date startDate = (Date) spn_dateStart.getValue();
	        		 Date finishDate = (Date) spn_dateFinish.getValue();
	        		 
	        		 if (startDate.compareTo(finishDate) > 0) {
	        		        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
	        		        return;
	        		    } else {
	        		    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	        		 String dateStart = dateFormat.format(startDate);
//	    	        		 System.out.println(dateStart);
	    	        		 String dateFinish = dateFormat.format(finishDate);	 
	    	        		 System.out.println(dateFinish);
	    	        		 List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.thongKeTheoKhoangNgay(dateStart, dateFinish);
//	    	        		 System.out.println(dataList);
	    	        	        for (ThongKeDoanhThuDTO dto : dataList) {
	    	        	            Object[] row = { dto.getThoigian(),dto.getSlDonNhap(), dto.getVon(),dto.getSlHoaDon(), dto.getDoanhthu(), dto.getLoinhuan() };
	    	        	            dtmDoanhThu.addRow(row);
	    	        	        }
	    	        	        lbl_phieuNhap.setText(calculateColumnTotal()[1]+"");
	    	        	        lbl_von.setText(calculateColumnTotal()[2]+"");
	    	        	        lbl_hoaDon.setText(calculateColumnTotal()[3]+"");
	    	        	        lbl_doanhThu.setText(calculateColumnTotal()[4]+"");
	    	        	        lbl_ngay.setText(calculateColumnTotal()[0]+"");
	    	        	        lbl_loiNhuan.setText(calculateColumnTotal()[5]+"");  
	        		    }
	        		 
	        		
	        		 
	        		
	        		    
	        	}
	        });
	
		}
		public long[] calculateColumnTotal() {
		    int numRows = tblDoanhThu.getRowCount();
		    int numCols = tblDoanhThu.getColumnCount();
	
		    // Mảng để lưu trữ tổng của từng cột
		    long[] columnTotals = new long[numCols];
	
		    // Duyệt qua từng dòng trong bảng
		    for (int row = 0; row < numRows; row++) {
		        // Duyệt qua từng cột trong dòng và cộng dồn vào tổng của cột tương ứng
		        for (int col = 0; col < numCols; col++) {
		            // Lấy giá trị của ô hiện tại trong bảng
		            Object cellValue = tblDoanhThu.getValueAt(row, col);
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
		
		public void exportExcel(String dateStart, String dateFinish) throws IOException {
	    	List<ThongKeDoanhThuDTO> doanhThuNgay = ThongKeDoanhThuBUS.thongKeTheoKhoangNgay(dateStart, dateFinish);
	    	try {
	    		FileOutputStream fileOutputStream = new FileOutputStream(absolutePath + "/excel/dt_tu_"+dateStart+"_den_"+dateFinish+".xlsx");
	    	    XSSFWorkbook wb = new XSSFWorkbook();
	    	    XSSFSheet sheet = wb.createSheet("Doanh thu từ ngày " +dateStart+ " Tới ngày " + dateFinish);
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
	    	    for (ThongKeDoanhThuDTO dtNgay : doanhThuNgay) {
	    	    	 row = sheet.createRow(rowNum++);
	    	    	row.createCell(0).setCellValue(dtNgay.getThoigian());
	    	    	row.createCell(1).setCellValue(dtNgay.getSlDonNhap());
	    	    	row.createCell(2).setCellValue(dtNgay.getVon());
	    	    	row.createCell(3).setCellValue(dtNgay.getSlHoaDon());
	    	    	row.createCell(4).setCellValue(dtNgay.getDoanhthu());
	    	    	row.createCell(5).setCellValue(dtNgay.getLoinhuan());
	    	    }
	    	    
	    	    wb.write(fileOutputStream);
	    	    wb.close();
	    	    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
	    	} catch (Exception e) {
	    	    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	
}
