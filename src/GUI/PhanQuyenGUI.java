

package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.io.File;
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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.NhanVien;
import DTO.Return;
import BUS.NhanVienBUS;
import BUS.ReturnBUS;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import DTO.Role;
import BUS.RoleBUS;
public class PhanQuyenGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblRole;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTiet;
//    private JButton btnThem;
    private JButton btnSua;
//    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private DefaultTableModel dtmRole;
    
    private static ChiTietPhanQuyenGUI chiTietPhanQuyenGUI;
    
    private Role rl = new Role();
    
	/**
	 * Create the panel.
	 */
	public PhanQuyenGUI() {
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
		
				
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		// ========== Start: Xử lý search ==========
		 txtTimKiem = new JTextField();
	        txtTimKiem.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {               
	                xuLyTimKiem(txtTimKiem.getText());
	            }
	        });
	        txtTimKiem.setMinimumSize(new Dimension(250, 19));
	        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        txtTimKiem.setColumns(10);
	        panel_1.add(txtTimKiem);
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
		// ========== Start: Xử lý làm mới search ==========
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText("");				
				xuLyTimKiem("");
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
		
		btnSua = new JButton("Sửa");
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setIcon(new ImageIcon(absolutePath + "/src/images/icons/edit.png"));
		btnSua.setPreferredSize(new Dimension(0, 40));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setFocusable(false);
		btnSua.setBackground(Color.WHITE);
		pnlTopBottom.add(btnSua);
				
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
		
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		tblRole = new JTable();
		tblRole.setBorder(null);
		tblRole.setSelectionBackground(new Color(232, 57, 95));
		tblRole.setRowHeight(25);
		tblRole.setIntercellSpacing(new Dimension(0, 0));
		tblRole.setFocusable(false);
		
		dtmRole = new DefaultTableModel(new Object[]{"Role_Id", "Role_Name", "Role_Tab_Name"},0);
		tblRole.setModel(dtmRole);
		tblRole.setDefaultEditor(Object.class, null);
		tblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tblRole);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		tblRole.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblRole.getTableHeader().setOpaque(false);
		tblRole.getTableHeader().setBackground(new Color(36,136,203));
		tblRole.getTableHeader().setForeground(new Color(255,255,255));
		tblRole.setRowHeight(25);
		

		loadDanhSachRole();
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		
		
		
		// Sự kiện lắng nghe click
//		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}


	// Load danh sách nhân viên
	public void loadDanhSachRole() {
		dtmRole.setRowCount(0);
		ArrayList<Role> dsrl = RoleBUS.getDanhSachRole();
		 for (Role role : dsrl) {
	            Object[] row = {role.getRole_id(), role.getRole_name(),role.getRole_tab_name()};
	            dtmRole.addRow(row); // Thêm dữ liệu mới
	        }		
	}
	// Xử lý tìm kiếm
		public void xuLyTimKiem(String keyword) {
		    	dtmRole.setRowCount(0);
		        ArrayList<Role> dsrl = RoleBUS.searchRole(keyword);
		        for (Role role : dsrl) {
		            Object[] row = {role.getRole_id(), role.getRole_name(),role.getRole_tab_name()};
		            dtmRole.addRow(row);
		        }
		 }
		public void hienThiGiaoDienSua() {
		    int selectedRow = tblRole.getSelectedRow();
		    if (selectedRow != -1) {
		        int role_id = (int) tblRole.getValueAt(selectedRow, 0);
		        // Lấy thông tin nhóm quyền từ cơ sở dữ liệu dựa trên role_id
		        Role role = RoleBUS.getRoleById(role_id);
		        if (role != null) {
		            // Hiển thị giao diện sửa thông tin nhóm quyền
		            if (chiTietPhanQuyenGUI == null || !chiTietPhanQuyenGUI.isVisible()) {
		                chiTietPhanQuyenGUI = new ChiTietPhanQuyenGUI(role, this);
		            } else {
		                chiTietPhanQuyenGUI.toFront();
		            }
		            chiTietPhanQuyenGUI.setVisible(true);
		            chiTietPhanQuyenGUI.requestFocus();
		        } else {
		            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin nhóm quyền", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhóm quyền để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		}
		@Override
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == btnSua) {
	        	 hienThiGiaoDienSua();
	        }
	         else if (e.getSource() == btnXuatExcel) {
	        	 try {
	 				exportExcel();
	 			} catch (IOException e1) {
	 				// TODO Auto-generated catch block
	 				e1.printStackTrace();
	 			}
	        }
		}

		
		  public boolean checkHeaderImportExcel (Row row) {
		        String[] expectedHeaders = {"role_id", "role_name", "role_tab_name"};
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
				ArrayList<Role> dsrl= RoleBUS.getDanhSachRole();
				try {
					FileOutputStream fileOutputStream = new FileOutputStream("excel/dspq.xlsx");
				    XSSFWorkbook wb = new XSSFWorkbook();
				    XSSFSheet sheet = wb.createSheet("Danh sách phân quyền");
				    
				    // Ghi header
				    XSSFRow headerRow = sheet.createRow(0);
				    headerRow.createCell(0).setCellValue("role_id");
				    headerRow.createCell(1).setCellValue("role_name");
				    headerRow.createCell(2).setCellValue("role_tab_name");
				    
				    // Ghi thông tin đổi trả
				    int rowNum = 1;
				    for (Role rl: dsrl) {
				    	XSSFRow row = sheet.createRow(rowNum++);
				    	row.createCell(0).setCellValue(rl.getRole_id());
				    	row.createCell(1).setCellValue(rl.getRole_name());
				    	row.createCell(2).setCellValue(rl.getRole_tab_name());
				    }			    
				    wb.write(fileOutputStream);
				    wb.close();
				    JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
				    JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
				}
			}	
}

