package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.jcp.xml.dsig.internal.dom.DOMTransform;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.NhanVien;
import DTO.TaiKhoan;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TaiKhoanGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblTaiKhoan;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTietQuyen;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private DefaultTableModel dtmTaiKhoan;
    
    private static ChiTietQuyenGUI chiTietQuyenGUI;
    private static ChiTietTaiKhoanGUI chiTietTaiKhoanGUI;
    private static ChonTaiKhoanGUI chonTaiKhoanGUI;
    
    private TaiKhoan tk = new TaiKhoan();
    private int searchStatus = -1;
    private String searchRole = "";
    
	/**
	 * Create the panel.
	 */
	public TaiKhoanGUI() {
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
		
		JComboBox cmbTrangThai = new JComboBox();
		
		// ========== Start: Xử lý search trạng thái ==========
		cmbTrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				searchStatus = cmbTrangThai.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}
				
				xuLyTimKiem(txtTimKiem.getText(), searchStatus, searchRole);
			}
		});
		// ========== End: Xử lý search trạng thái ==========

		
		cmbTrangThai.setFocusable(false);
		cmbTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Trạng thái", "Hoạt động", "Ngưng hoạt động"}));
		pnlTrangThai.add(cmbTrangThai);
		
		JPanel pnlChucVu = new JPanel();
		pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox cmbQuyen = new JComboBox();
		
		// ========== Start: Xử lý search role ==========
		cmbQuyen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmbQuyen.getSelectedIndex() == 1) {
					searchRole = "admin"; 
				} else if (cmbQuyen.getSelectedIndex() == 2) {
					searchRole = "staff";
				} else {
					searchRole = "";
				}
				
				xuLyTimKiem(txtTimKiem.getText(), searchStatus, searchRole);
			}
		});
		// ========== Start: Xử lý search role ==========
		
		cmbQuyen.setFocusable(false);
		cmbQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbQuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbQuyen.setModel(new DefaultComboBoxModel(new String[] {"Quyền", "Admin", "Nhân viên"}));
		pnlChucVu.add(cmbQuyen);
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtTimKiem = new JTextField();
		
		
		
		// ========== Start: Xử lý search ==========
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Nếu có chọn trạng thái thì lọc luôn
				searchStatus = cmbTrangThai.getSelectedIndex();
				if (searchStatus == 1) {
					searchStatus = 1;
				} else if (searchStatus == 2) {
					searchStatus = 0;
				} else {
					searchStatus = -1;
				}
				
				searchRole = "";
				if (cmbQuyen.getSelectedIndex() == 1) {
					searchRole = "admin";
				} else if (cmbQuyen.getSelectedIndex() == 2) {
					searchRole = "staff";
				} else {
					searchRole = "";
				}
				
				xuLyTimKiem(txtTimKiem.getText(), searchStatus, searchRole);
			}
		});
		// ========== End: Xử lý search ==========
		
		
		
		txtTimKiem.setMinimumSize(new Dimension(250, 19));
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		panel_1.add(txtTimKiem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnTim = new JButton("Làm mới");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText("");
				cmbTrangThai.setSelectedIndex(0);
				cmbQuyen.setSelectedIndex(0);
				xuLyTimKiem("", -1, "");
			}
		});
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
		
		btnChiTietQuyen = new JButton("Quyền");
		btnChiTietQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChiTietQuyen.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
		btnChiTietQuyen.setPreferredSize(new Dimension(150, 40));
		btnChiTietQuyen.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTietQuyen.setFocusable(false);
		btnChiTietQuyen.setBackground(Color.WHITE);
		pnlTopBottom.add(btnChiTietQuyen);
		
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
		
		
		
		// ========== START: TABLE DANH SÁCH TÀI KHOẢN ==========
		tblTaiKhoan = new JTable();
		tblTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xuLyClickTable();
			}
		});
		tblTaiKhoan.setBorder(null);
		tblTaiKhoan.setSelectionBackground(new Color(232, 57, 95));
		tblTaiKhoan.setRowHeight(25);
		tblTaiKhoan.setIntercellSpacing(new Dimension(0, 0));
		tblTaiKhoan.setFocusable(false);
		
		dtmTaiKhoan = new DefaultTableModel(new Object[] {"Mã tài khoản", "Username", "Trạng thái", "Quyền"}, 0);
		tblTaiKhoan.setModel(dtmTaiKhoan);
		tblTaiKhoan.setDefaultEditor(Object.class, null);
		tblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tblTaiKhoan);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		tblTaiKhoan.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblTaiKhoan.getTableHeader().setOpaque(false);
		tblTaiKhoan.getTableHeader().setBackground(new Color(36,136,203));
		tblTaiKhoan.getTableHeader().setForeground(new Color(255,255,255));
		tblTaiKhoan.setRowHeight(25);
		
		loadDanhSachTaiKhoan();
		// ========== END: TABLE DANH SÁCH TÀI KHOẢN ==========

		
		
		// Sự kiện lắng nghe click
		btnChiTietQuyen.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChiTietQuyen) {
			if (tk.getAccountId() > 0) {
				if (chiTietQuyenGUI == null || !chiTietQuyenGUI.isVisible()) {
	            	chiTietQuyenGUI = new ChiTietQuyenGUI(tk.getAccountId());
	            } else {
	            	chiTietQuyenGUI.toFront();
	            }
				chiTietQuyenGUI.setVisible(true);
				chiTietQuyenGUI.requestFocus();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xem chi tiết quyền", "Thông báo lỗi xem chi tiêt quyền tài khoản", JOptionPane.INFORMATION_MESSAGE);
			}
        } else if (e.getSource() == btnThem) {
        	if (chonTaiKhoanGUI == null || !chonTaiKhoanGUI.isVisible()) {
        		chonTaiKhoanGUI = new ChonTaiKhoanGUI(this);
        	} else {
        		chonTaiKhoanGUI.toFront();
        	}
        	chonTaiKhoanGUI.setVisible(true);
        	chonTaiKhoanGUI.requestFocus();
        } else if (e.getSource() == btnSua) {
        	if (tk.getAccountId() > 0) {
        		if (chiTietTaiKhoanGUI == null || !chiTietTaiKhoanGUI.isVisible()) {
            		chiTietTaiKhoanGUI = new ChiTietTaiKhoanGUI(tk, this);
                } else {
                	chiTietTaiKhoanGUI.toFront();
                }
            	chiTietTaiKhoanGUI.setVisible(true);
            	chiTietTaiKhoanGUI.requestFocus();
        	} else {
        		JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa", "Thông báo lỗi sửa thông tin tài khoản", JOptionPane.INFORMATION_MESSAGE);
        	}
        } else if (e.getSource() == btnXoa) {
        	if (tk.getAccountId() > 0) {
        		int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc xoá tài khoản có id: " + tk.getAccountId() + " không?", "Xác nhận xoá tài khoản", JOptionPane.YES_NO_OPTION);
            	if (choice == JOptionPane.YES_OPTION) {
            		if (TaiKhoanBUS.deleteTaiKhoanById(tk.getAccountId()) && NhanVienBUS.deleteNhanVienByAccountId(tk.getAccountId())) {
            			loadDanhSachTaiKhoan();
    					JOptionPane.showMessageDialog(null, "Hệ thống đã xoá thành công tài khoản viên có id: " + tk.getAccountId(), "Thông báo xoá thành công tài khoản", JOptionPane.INFORMATION_MESSAGE);
            		} else {
    					JOptionPane.showMessageDialog(null, "Hệ thống đã xoá thất bại nhân viên có id: " + tk.getAccountId(), "Thông báo xoá thất công tài khoản", JOptionPane.ERROR_MESSAGE);
            		}
            	} 
        	} else {
        		JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xoá", "Thông báo lỗi xoá tài khoản", JOptionPane.INFORMATION_MESSAGE);
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
	
	public void loadDanhSachTaiKhoan() {
		dtmTaiKhoan.setRowCount(0);
		ArrayList<TaiKhoan> dstk = TaiKhoanBUS.getDanhSachTaiKhoan();
		
		for (TaiKhoan tk : dstk) {
			if (tk.getAccountId() == 9999) {
				continue;
			}
			
			String status = "";
			if (tk.getAccountStatus() == 1) {
				status = "Hoạt động";
			} else if (tk.getAccountStatus() == 0) {
				status = "Ngưng hoạt động";
			}
			
			String position = "";
			if (tk.getPosition().equals("staff")) {
				position = "Nhân viên";
			} else if (tk.getPosition().equals("admin")) {
				position = "Quản trị viên";
			}
			
			Object[] row = {tk.getAccountId(), tk.getUsername(), status, position};
			dtmTaiKhoan.addRow(row);
		}
	}
	
	public void xuLyClickTable() {
		int row = tblTaiKhoan.getSelectedRow();
		if (row > -1) {
			tk.setAccountId((int) tblTaiKhoan.getValueAt(row, 0));
			tk.setUsername((String) tblTaiKhoan.getValueAt(row, 1));
			String status = (String) tblTaiKhoan.getValueAt(row, 2);
			if (status.equals("Hoạt động")) {
				tk.setAccountStatus(1);
			} else if (status.equals("Ngưng hoạt động")) {
				tk.setAccountStatus(0);
			}
			
			String position = (String) tblTaiKhoan.getValueAt(row, 3);
			if (position.equals("Nhân viên")) {
				tk.setPosition("staff");
			} else if (position.equals("Quản trị viên")) {
				tk.setPosition("admin");
			}
		}
	}
	
	// Xử lý tìm kiếm
	public void xuLyTimKiem(String keyword, int searchStatus, String searchRole) {
		dtmTaiKhoan.setRowCount(0);
		ArrayList<TaiKhoan> dstk = TaiKhoanBUS.searchTaiKhoan(keyword, searchStatus, searchRole);
		
		for(TaiKhoan tk : dstk) {
			if (tk.getAccountId() == 9999) {
				continue;
			}
			
			String status;
			if (tk.getAccountStatus() == 1) {
				status = "Hoạt động";
			} else {
				status = "Ngưng hoạt động";
			}
			
			String position = "";
			if (tk.getPosition().equals("admin")) {
				position = "Quản trị viên";
			} else if (tk.getPosition().equals("staff")) {
				position = "Nhân viên";
			}
			
			Object[] row = {tk.getAccountId(), tk.getUsername(), status, position};
			dtmTaiKhoan.addRow(row);
		}
	}
	
	public void importExcel() {
		try {
			ArrayList<TaiKhoan> dstk = new ArrayList<>();
			
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
                	TaiKhoan taiKhoan = new TaiKhoan();
                    for (Cell cell : row) { 
                    	int columnIndex = cell.getColumnIndex();
                        try {
                        	switch (columnIndex) {
	                         case 0:
	                           	taiKhoan.setUsername(cell.getStringCellValue());
	                            break;
	                         case 1:
	                        	 taiKhoan.setPassword(cell.getStringCellValue());
	                             break;
	                         case 2:
	                        	 taiKhoan.setAccountStatus((int) cell.getNumericCellValue());
	                             break;
	                         case 3:
	                        	 String position = cell.getStringCellValue();
	                             if (position.equalsIgnoreCase("admin") || position.equalsIgnoreCase("staff")) {
	                                 taiKhoan.setPosition(position);
	                             } else {
	                            	 taiKhoan.setPosition("staff");
	                             }
	                             break;
	                         case 4:
	                        	 taiKhoan.setStaffId((int) cell.getNumericCellValue());
	                        	 break;
                           }
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi định dạng dữ liệu, vui lòng kiểm tra lại file excel!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
						}
                    }
                    dstk.add(taiKhoan);
                }
                
                // Ghi dữ liệu vào db
					if (TaiKhoanBUS.insertDanhSachTaiKhoan(dstk)) {
						loadDanhSachTaiKhoan();
						String message = "Đã import dữ liệu từ file excel vào hệ thống thành công!";
						message += "\nNgoại trừ: ";
						message += "\n - Dữ liệu trùng username";
						message += "\n - Nhân viên đã sở hữu tài khoản";
						message += "\n - Dữ liệu chứa mã nhân viên đã bị xoá";
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
        String[] expectedHeaders = {"username", "password", "account_status", "position", "staff_id"};
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
		ArrayList<TaiKhoan> dstk= TaiKhoanBUS.getDanhSachTaiKhoan();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/dstk.xlsx");
		    XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet("Danh sách tài khoản");
		    
		    // Ghi header
		    XSSFRow headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("account_id");
		    headerRow.createCell(1).setCellValue("username");
		    headerRow.createCell(2).setCellValue("account_status");
		    headerRow.createCell(3).setCellValue("position");
		    
		    // Ghi thông tin nhân viên
		    int rowNum = 1;
		    for (TaiKhoan tk: dstk) {
		    	XSSFRow row = sheet.createRow(rowNum++);
		    	row.createCell(0).setCellValue(tk.getAccountId());
		    	row.createCell(1).setCellValue(tk.getUsername());
		    	row.createCell(2).setCellValue(tk.getAccountStatus());
		    	row.createCell(3).setCellValue(tk.getPosition());
		    }
		    
		    wb.write(fileOutputStream);
		    wb.close();
		    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
