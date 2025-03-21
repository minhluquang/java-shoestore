package GUI;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import BUS.ReturnBUS;
import BUS.RoleBUS;
import BUS.TaiKhoanBUS;
import DTO.Return;
import DTO.Role;
import DTO.TaiKhoan;
public class ReturnGUI extends JPanel implements ActionListener{
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
    private DefaultTableModel dtmReturn;
    private JComboBox cmbTrangThai;
    private static ChiTietBaoHanhGUI chiTietBaoHanh;
    private Return rt = new Return();
    public ReturnGUI() {
    	setBackground(new Color(230, 230, 230));
		setLayout(new BorderLayout(10, 10));
		Font font = new Font(getFont().getName(), Font.PLAIN, 16);
     
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
		
		JPanel pnlChucVu = new JPanel();
		pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFocusable(false);
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Trạng thái","1" ,"0"}));
		pnlChucVu.add(comboBox_1);
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
		
		dtmReturn = new DefaultTableModel(new Object[]{"Mã Đổi Trả", "Mã Seri Sản Phẩm", "Ngày Bảo Hành", "Lý do","Trạng thái" ,"Status"}, 0);
		table.setModel(dtmReturn);
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36,136,203));
		table.getTableHeader().setForeground(new Color(255,255,255));
		table.setRowHeight(25);
		 // Căn giữa tiêu đề
		 ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		 // Căn giữa dữ liệu trong bảng
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 table.setDefaultRenderer(Object.class, centerRenderer);
		 
		 loadDanhSachBaoHanh();
		 
		 
		 
		 
		// Sự kiện lắng nghe click
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
    }
    

    // load
    public void loadDanhSachBaoHanh() {
    	dtmReturn.setRowCount(0);
    	ArrayList<Return> danhSachBaoHanh = ReturnBUS.getDanhSachReturn();
		for (Return returnItem : danhSachBaoHanh) {
		    Object[] rowData = new Object[]{returnItem.getReturn_id(), returnItem.getProduct_serial_id(), returnItem.getDate_return(), returnItem.getReason(), returnItem.getActive(), returnItem.getStatus()};
		    dtmReturn.addRow(rowData);
		}
    }
    
    // search
    public void xuLyTimKiem(String keyword, int searchStatus) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.setRowCount(0);
    	ArrayList<Return> dsrt = ReturnBUS.searchReturn(keyword,searchStatus);
    	for (Return rt : dsrt) {
    		String statusINT = String.valueOf(rt.getStatus());
    		String status;
    		if ("1".equals(statusINT)) {
                status = "1";
            } else {
                status = "0";
            }
    		Object[] row = {rt.getReturn_id(), rt.getProduct_serial_id(),rt.getDate_return(),rt.getReason(), rt.getActive(), rt.getStatus(), status};
    		model.addRow(row);
    	}
    }
    
    public void hienThiGiaoDienSua() {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow != -1) {
	        int return_id = (int) table.getValueAt(selectedRow, 0);
	        // Lấy thông tin nhóm quyền từ cơ sở dữ liệu dựa trên role_id
	        Return rt = ReturnBUS.getReturnById(return_id);
	        if (rt != null) {
	            // Hiển thị giao diện sửa thông tin nhóm quyền
	            if (chiTietBaoHanh == null || !chiTietBaoHanh.isVisible()) {
	            	chiTietBaoHanh = new ChiTietBaoHanhGUI(rt, this);
	            } else {
	            	chiTietBaoHanh.toFront();
	            }
	            chiTietBaoHanh.setVisible(true);
	            chiTietBaoHanh.requestFocus();
	        } else {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin nhóm quyền", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhóm quyền để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
    
    
    
    public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnThem) {
			 if (chiTietBaoHanh == null || !chiTietBaoHanh.isVisible()) {
				 chiTietBaoHanh = new ChiTietBaoHanhGUI(new Return(), this);
	            } else {
	            	chiTietBaoHanh.toFront();
	            }
			 chiTietBaoHanh.setVisible(true);
			 chiTietBaoHanh.requestFocus();
        } else if (e.getSource() == btnSua) {
        	hienThiGiaoDienSua();
        } else if (e.getSource() == btnXoa) {
        	int selectedRow = table.getSelectedRow();
        	if(selectedRow != -1) {
        		int return_id = (int) table.getValueAt(selectedRow, 0);
        		int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thông tin bảo hành này?", "Xác nhận xóa vai trò", JOptionPane.YES_NO_OPTION);
        		 if (choice == JOptionPane.YES_OPTION) {
        			 boolean success = ReturnBUS.deleteReturn(return_id);
        			 if(success) {
        				 JOptionPane.showMessageDialog(null, "Xóa thông tin bảo hành thành công.");
        				 loadDanhSachBaoHanh();
        			 } else {
        				 JOptionPane.showMessageDialog(null, "Xóa thông tin bảo hành thất bại.");
        			 }
        		 }
        	} else {
        		JOptionPane.showMessageDialog(null, "Vui lòng chọn một thông tin bảo hành để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        	}
        }
        else if (e.getSource() == btnNhapExcel) {
            // Xử lý khi button "Nhập excel" được nhấn
        	importExcel();
        } else if (e.getSource() == btnXuatExcel) {
            // Xử lý khi button "Xuất excel" được nhấn
        	try {
				exportExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
    
    public void importExcel() {
		try {
			ArrayList<Return> dsdt = new ArrayList<>();
			
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
                	Return rt = new Return();
                    for (Cell cell : row) { 
                    	int columnIndex = cell.getColumnIndex();
                        try {
                        	switch (columnIndex) {
	                         case 0:
	                        	rt.setProduct_serial_id((int) cell.getNumericCellValue());
	                            break;
	                         case 1:
	                        	 rt.setDate_return(cell.getStringCellValue());
	                             break;
	                         case 2:
	                        	 rt.setReason(cell.getStringCellValue());
	                             break;
                           }
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi định dạng dữ liệu, vui lòng kiểm tra lại file excel!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
						}
                    }
                    dsdt.add(rt);
                }
                
            

                
//                 Ghi dữ liệu vào db
					if (ReturnBUS.insertDanhSachDoiTra(dsdt)) {
						loadDanhSachBaoHanh();
						String message = "Đã import dữ liệu từ file excel vào hệ thống thành công!";
						message += "\nNgoại trừ: ";
						message += "\n - Mã sản phẩm đã đổi trả";
						message += "\n - Ngày đổi trả vượt quá 7 ngày";
						JOptionPane.showMessageDialog(null, message, "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
						return;
					} 
					 else {
							JOptionPane.showMessageDialog(null, "Có lỗi khi import dữ liệu từ file excel vào hệ thống!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
							return;
						}
				
    		}
    	} catch (Exception e2) {
    	    // Xử lý ngoại lệ ở đây nếu cần
    	}
	}
    
    public boolean checkHeaderImportExcel (Row row) {
        String[] expectedHeaders = {"product_serial_id", "date_return", "reason"};
        boolean headerMatched = true;
        
        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = row.getCell(i);
            if (cell == null || !cell.getStringCellValue().trim().equals(expectedHeaders[i])) {
            	System.out.println(cell.getStringCellValue());
                headerMatched = false;
                break;
            }
        }
        
        return headerMatched;
	}

    public void exportExcel() throws IOException {
		ArrayList<Return> dsdt= ReturnBUS.getDanhSachReturn();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dsdt.xlsx");
		    XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet("Danh sách tài khoản");
		    
		    // Ghi header
		    XSSFRow headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("return_id");
		    headerRow.createCell(1).setCellValue("product_serial_id");
		    headerRow.createCell(2).setCellValue("date_return");
		    headerRow.createCell(3).setCellValue("reason");
		    headerRow.createCell(4).setCellValue("return_status");
		    
		    // Ghi thông tin đổi trả
		    int rowNum = 1;
		    for (Return dt: dsdt) {
		    	XSSFRow row = sheet.createRow(rowNum++);
		    	row.createCell(0).setCellValue(dt.getReturn_id());
		    	row.createCell(1).setCellValue(dt.getProduct_serial_id());
		    	row.createCell(2).setCellValue(dt.getDate_return());
		    	row.createCell(3).setCellValue(dt.getReason());
		    }
		    
		    wb.write(fileOutputStream);
		    wb.close();
		    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
