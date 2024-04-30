package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.NhanVienBUS;
import BUS.ThongKeNhaCungCapBUS;
import BUS.ThongKeTonKhoBUS;
import DTO.NhanVien;
import DTO.ThongKeNhaCungCapDTO;
import DTO.ThongKeTonKhoDTO;

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
import java.util.ArrayList;
import java.util.List;

public class ThongKeNhaCungCapGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tblThongKe;
	private DefaultTableModel dtmThongKe;
	private JTextField textField;
	public String absolutePath = new File("").getAbsolutePath();

	/**
	 * Create the panel.
	 */
	public ThongKeNhaCungCapGUI() {
		setLayout(new BorderLayout(0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(50,0, 0, 0));
		
		JPanel pnl_left = new JPanel();
		add(pnl_left, BorderLayout.WEST);
		pnl_left.setLayout(new GridLayout(4, 1, 30, 40));
		pnl_left.setBorder(BorderFactory.createEmptyBorder(10,10, 0, 0));
		JPanel panelSearch = new JPanel();
		pnl_left.add(panelSearch);
		panelSearch.setLayout(new GridLayout(2, 1, 20, 20));
		
		JPanel pnl_search = new JPanel();
		panelSearch.add(pnl_search);
		pnl_search.setLayout(new GridLayout(0, 1, 10, 5));
		
		JLabel lblNewLabel = new JLabel("Nhập tên nhà cung cấp	: ");
		pnl_search.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		pnl_search.add(textField);
		
		JPanel pnl_button = new JPanel();
		panelSearch.add(pnl_button);
		pnl_button.setLayout(new GridLayout(0, 3, 20, 0));
		pnl_button.setBorder(BorderFactory.createEmptyBorder(25,0, 0, 0));
		
		JButton btn_search = new JButton("Tìm kiếm");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmThongKe.setRowCount(0);
				String tenNCC = textField.getText();
				List<ThongKeNhaCungCapDTO> dataList = ThongKeNhaCungCapBUS.getNCCSearch(tenNCC);
		        for (ThongKeNhaCungCapDTO dto : dataList) {
		            Object[] row = { dto.getStt(),dto.getMaNCC(), dto.getTenNCC(),dto.getSL_Nhap(), dto.getTongTien() };
		            dtmThongKe.addRow(row);
		        }
			}
		});
		pnl_button.add(btn_search);
		
		JButton btn_reset = new JButton("Reset");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmThongKe.setRowCount(0);
				displayData();
			}
		});
		pnl_button.add(btn_reset);
		
		JButton btn_xuatExcel = new JButton("Xuất Excel");
		btn_xuatExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					exportExcel();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		pnl_button.add(btn_xuatExcel);
		
		JPanel pnl_right = new JPanel();
		
		add(pnl_right);
		pnl_right.setLayout(new BorderLayout());
		pnl_right.setBorder(BorderFactory.createEmptyBorder(0,100, 0, 0));
		
		
		
		
		
		tblThongKe = new JTable();
		
		tblThongKe.setBorder(null);
		tblThongKe.setSelectionBackground(new Color(232, 57, 95));
		tblThongKe.setRowHeight(25);
		tblThongKe.setIntercellSpacing(new Dimension(0, 0));
		tblThongKe.setFocusable(false);
		
		dtmThongKe = new DefaultTableModel(new Object[] {"STT","Mã nhà cung cấp", "Tên nhà cung cấp","SL đơn nhập", "Tổng tiền"}, 0);
		tblThongKe.setModel(dtmThongKe);
		tblThongKe.setDefaultEditor(Object.class, null);
		pnl_right.setLayout(new BorderLayout(0, 0));
		tblThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		

		
		JScrollPane scrollPane = new JScrollPane(tblThongKe);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnl_right.add(scrollPane, BorderLayout.CENTER);
	
		((DefaultTableCellRenderer)tblThongKe.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tblThongKe.setDefaultRenderer(Object.class, centerRenderer);
		
		tblThongKe.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblThongKe.getTableHeader().setOpaque(false);
		tblThongKe.getTableHeader().setBackground(new Color(36,136,203));
		tblThongKe.getTableHeader().setForeground(new Color(255,255,255));
		tblThongKe.setRowHeight(25);
		displayData();
	}
	
	public void displayData() {
		List<ThongKeNhaCungCapDTO> dataList = ThongKeNhaCungCapBUS.getAllNCC();
        for (ThongKeNhaCungCapDTO dto : dataList) {
            Object[] row = { dto.getStt(),dto.getMaNCC(), dto.getTenNCC(),dto.getSL_Nhap(), dto.getTongTien() };
            dtmThongKe.addRow(row);
        }
    }
	
	public void exportExcel() throws IOException {
		List<ThongKeNhaCungCapDTO> dsncc = ThongKeNhaCungCapBUS.getAllNCC();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(absolutePath + "/excel/sdncc.xlsx");
		    XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet("Danh sách nhà cung cấp");
		    XSSFRow row = null;
		    Cell cell = null;
		    
		    // Ghi header
		    XSSFRow headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("Mã nhà cung cấp");
		    headerRow.createCell(1).setCellValue("Tên nhà cung cấp");
		    headerRow.createCell(2).setCellValue("Số lượng đơn nhập");
		    headerRow.createCell(3).setCellValue("Tổng tiền");
		    
		    // Ghi thông tin nhân viên
		    int rowNum = 1;
		    for (ThongKeNhaCungCapDTO ncc : dsncc) {
		    	 row = sheet.createRow(rowNum++);
		    	row.createCell(0).setCellValue(ncc.getMaNCC());
		    	row.createCell(1).setCellValue(ncc.getTenNCC());
		    	row.createCell(2).setCellValue(ncc.getSL_Nhap());
		    	row.createCell(3).setCellValue(ncc.getTongTien());
		    }
		    
		    wb.write(fileOutputStream);
		    wb.close();
		    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
		}
	}

}
