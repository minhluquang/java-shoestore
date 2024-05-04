

package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.io.File;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.KhuyenMai;
import DTO.NhanVien;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import DTO.Role;
import DTO.TheLoaiDTO;
import BUS.RoleBUS;
import BUS.TheLoaiBUS;
public class QLLoai extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblLoai;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTiet;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private DefaultTableModel dtmLoai;
    
    private static ChiTietLoai chiTietLoai;
    
    private TheLoaiDTO tl = new TheLoaiDTO();
    
	/**
	 * Create the panel.
	 */
	public QLLoai() {
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
		
		
		btnXoa = new JButton("btnXoa");
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
		
		// ========== TABLE DANH  ==========
		tblLoai = new JTable();

		tblLoai.setBorder(null);
		tblLoai.setSelectionBackground(new Color(232, 57, 95));
		tblLoai.setRowHeight(25);
		tblLoai.setIntercellSpacing(new Dimension(0, 0));
		tblLoai.setFocusable(false);
		
		dtmLoai = new DefaultTableModel(new Object[]{"Category_ID", "Category_Name", "Status"},0);
		tblLoai.setModel(dtmLoai);
		tblLoai.setDefaultEditor(Object.class, null);
		tblLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tblLoai);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		pnlCenter.add(scrollPane);
		
		tblLoai.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblLoai.getTableHeader().setOpaque(false);
		tblLoai.getTableHeader().setBackground(new Color(36,136,203));
		tblLoai.getTableHeader().setForeground(new Color(255,255,255));
		tblLoai.setRowHeight(25);
		

		loadDanhSachTheLoai();
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		
		
		
		// Sự kiện lắng nghe click
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
	}


	// Load danh sách nhân viên
	public void loadDanhSachTheLoai() {
		dtmLoai.setRowCount(0);
		ArrayList<TheLoaiDTO> dsl = TheLoaiBUS.getDanhSachTheLoai();	
		 for (TheLoaiDTO tl : dsl) {
	            Object[] row = {tl.getCategory_id(),tl.getCategory_name(),tl.isStatus()};
	            dtmLoai.addRow(row); // Thêm dữ liệu mới
	        }		
	}
	// Xử lý tìm kiếm
		public void xuLyTimKiem(String keyword) {
				dtmLoai.setRowCount(0);
		        ArrayList<TheLoaiDTO> dsl = TheLoaiBUS.searchLoai(keyword);
		        for (TheLoaiDTO tl : dsl) {
		            Object[] row = {tl.getCategory_id(),tl.getCategory_name(),tl.isStatus()};
		            dtmLoai.addRow(row); // Thêm dữ liệu mới
		        }	
		 }
		public void hienThiGiaoDienSua() {
    	    int selectedRow = tblLoai.getSelectedRow();
    	    if (selectedRow != -1) {
    	        int id_cate = (int) tblLoai.getValueAt(selectedRow, 0);    	      
    	        TheLoaiDTO tl = TheLoaiBUS.getTheLoaiByID(selectedRow);
    	        System.out.println("ten :" + id_cate);
    	        if (tl != null) {
    	            // Hiển thị giao diện sửa thông tin khuyến mãi
    	            if (chiTietLoai == null || !chiTietLoai.isVisible()) {
    	            	chiTietLoai = new ChiTietLoai(tl, this);
    	            } else {
    	            	chiTietLoai.toFront();
    	            }
    	            chiTietLoai.setVisible(true);
    	            chiTietLoai.requestFocus();
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin khuyến mãi", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	        }
    	    } else {
    	        JOptionPane.showMessageDialog(null, "Vui lòng chọn một mã khuyến mãi để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	    }
    	}
		@Override
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == btnThem) {
				if (chiTietLoai == null || !chiTietLoai.isVisible()) {
					chiTietLoai = new ChiTietLoai(new TheLoaiDTO(), this);
	   	            } else {
	   	            	chiTietLoai.toFront();
	   	            }
				chiTietLoai.setVisible(true);
				chiTietLoai.requestFocus();
	        } else if (e.getSource() == btnSua) {
	        	 hienThiGiaoDienSua();
	        } else if(e.getSource() == btnXoa) {
	            int selectedRow = tblLoai.getSelectedRow();
	    	    if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
	    	        int cate_id = (int) tblLoai.getValueAt(selectedRow, 0);
	    	        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thể loại này?", "Xác nhận xóa thể loại", JOptionPane.YES_NO_OPTION);
	    	        if (choice == JOptionPane.YES_OPTION) {
	    	            boolean success = TheLoaiBUS.xoaTheLoai(cate_id); // Gọi phương thức xóa từ RoleBUS hoặc RoleDAO
	    	            System.out.println("cate" + cate_id);
	    	            if (success) {
	    	                JOptionPane.showMessageDialog(null, "Xóa thể loại thành công.");
	    	                loadDanhSachTheLoai(); // Sau khi xóa thành công, cập nhật lại danh sách vai trò
	    	            } else {
	    	                JOptionPane.showMessageDialog(null, "Xóa thể loại thất bại.");
	    	            }
	    	        }
	    	    } else {
	    	        JOptionPane.showMessageDialog(null, "Vui lòng chọn một thể loại để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    	    }
	        }
	        else if (e.getSource() == btnNhapExcel) {
	            // Xử lý khi button "Nhập excel" được nhấn
	        } else if (e.getSource() == btnXuatExcel) {
	            // Xử lý khi button "Xuất excel" được nhấn
	        }
		}

}

