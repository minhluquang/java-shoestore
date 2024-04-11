package GUI;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import BUS.ReturnBUS;
import DTO.Return;
import DTO.Role;
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
    static JTextField productNameField, warrantyPeriodField, warrantyReasonField;
    public ReturnGUI() {
    	setBackground(new Color(230, 230, 230));
		setLayout(new BorderLayout(10, 10));
		Font font = new Font(getFont().getName(), Font.PLAIN, 16);
		ArrayList<Return> danhSachBaoHanh = ReturnBUS.getDanhSachReturn();
       
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
		
		JPanel pnlChucVu = new JPanel();
		pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFocusable(false);
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Trạng thái", "Hoạt động", "Ngưng hoạt động"}));
		pnlChucVu.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		 JPanel inputPanel = new JPanel(new GridBagLayout());
	     inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
	     GridBagConstraints gbc = new GridBagConstraints();
	     gbc.gridx = 0;
	     gbc.gridy = 0;
	     gbc.insets = new Insets(5, 5, 5, 5);
	     JLabel productNameLabel = new JLabel("Tên sản phẩm:");
	      inputPanel.add(productNameLabel, gbc);
	      productNameLabel.setFont(font);
	      gbc.gridx = 1;
	      gbc.gridwidth = 2;
	      productNameField = new JTextField(20);
	      inputPanel.add(productNameField, gbc);
	      gbc.gridx = 0;
	      gbc.gridy++;
	      gbc.gridwidth = 1;
	      JLabel warrantyPeriodLabel = new JLabel("Thời gian bảo hành:");
	        inputPanel.add(warrantyPeriodLabel, gbc);
	        warrantyPeriodLabel.setFont(font);
	        gbc.gridx = 1;
	        warrantyPeriodField = new JTextField(20);
	        inputPanel.add(warrantyPeriodField, gbc);
	        gbc.gridx = 0;
	        gbc.gridy++;
	        JLabel reasonLabel = new JLabel("Lý do bảo hành:");
	        reasonLabel.setFont(font);
	        inputPanel.add(reasonLabel, gbc);
	        gbc.gridx = 1;
	        warrantyReasonField = new JTextField(20); 
	        inputPanel.add(warrantyReasonField, gbc);
	       
		txtTmKim = new JTextField();
		txtTmKim.setMinimumSize(new Dimension(250, 19));
		txtTmKim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTmKim.setColumns(10);
		panel_1.add(txtTmKim);
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnTim = new JButton("Làm mới");
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
		
		panel_8.add(inputPanel);
		// ========== TABLE DANH SÁCH NHÂN VIÊN ==========
		table = new JTable();
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		
		dtmReturn = new DefaultTableModel(new Object[]{"Return_ID", "Product_ID", "Date_Return", "Reason"}, 0);
		for (Return returnItem : danhSachBaoHanh) {
		    Object[] rowData = new Object[]{returnItem.getReturn_id(), returnItem.getProduct_id(), returnItem.getDate_return(), returnItem.getReason()};
		    dtmReturn.addRow(rowData);
		}

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
		// Sự kiện lắng nghe click
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
//		btnXoa.addActionListener(this);
		btnNhapExcel.addActionListener(this);
		btnXuatExcel.addActionListener(this);
    }
    
    
    
    
    
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChiTiet) {
			
        } else if (e.getSource() == btnThem) {
           
        } else if (e.getSource() == btnSua) {
        	
        } 
//        else if (e.getSource() == btnXoa) {
//        	int choice = JOptionPane.showConfirmDialog(null, "Xoá thông tin nhân viên có mã nhân viên là NV001", "Xác nhận xoá thông tin nhân viên", JOptionPane.YES_NO_OPTION);
//        	if (choice == JOptionPane.YES_OPTION) {
//        		
//        	} else {
//        		
//        	}
//        }
        else if (e.getSource() == btnNhapExcel) {
            // Xử lý khi button "Nhập excel" được nhấn
        } else if (e.getSource() == btnXuatExcel) {
            // Xử lý khi button "Xuất excel" được nhấn
        }
	}
}
