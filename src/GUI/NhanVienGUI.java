package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.io.File;

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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NhanVienGUI extends JPanel {

	private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField textField;
    private JTable table;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField textField_1;
    private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public NhanVienGUI() {
		setBackground(new Color(230, 230, 230));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(255, 255, 255));
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(20, 0));
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(new Color(255, 255, 255));
		pnlTop.add(pnlSearch, BorderLayout.CENTER);
		pnlSearch.setLayout(new BorderLayout(5, 0));
		
		JPanel pnlLocNangCao = new JPanel();
		pnlLocNangCao.setBackground(new Color(255, 255, 255));
		pnlSearch.add(pnlLocNangCao, BorderLayout.WEST);
		pnlLocNangCao.setLayout(new BorderLayout(2, 0));
		
		JPanel pnlTrangThai = new JPanel();
		pnlLocNangCao.add(pnlTrangThai, BorderLayout.WEST);
		pnlTrangThai.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFocusable(false);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Trạng thái", "Hoạt động", "Ngưng hoạt động"}));
		pnlTrangThai.add(comboBox);
		
		JPanel pnlChucVu = new JPanel();
		pnlLocNangCao.add(pnlChucVu, BorderLayout.EAST);
		pnlChucVu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFocusable(false);
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Chức vụ", "Admin", "Nhân viên"}));
		pnlChucVu.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		pnlSearch.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(250, 19));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		panel_1.add(textField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlSearch.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnTim = new JButton("");
		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setIcon(new ImageIcon(absolutePath + "/src/images/icons/search.png"));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTim.setFocusable(false);
		btnTim.setBackground(new Color(255, 255, 255));
		panel_2.add(btnTim);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(0, 5));
		pnlSearch.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setPreferredSize(new Dimension(0, 5));
		pnlSearch.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JPanel pnlExcel = new JPanel();
		pnlExcel.setBackground(new Color(255, 255, 255));
		pnlTop.add(pnlExcel, BorderLayout.EAST);
		pnlExcel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNhapExcel = new JPanel();
		pnlExcel.add(pnlNhapExcel, BorderLayout.WEST);
		pnlNhapExcel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNhapExcel = new JButton("Nhập excel");
		btnNhapExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhapExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnNhapExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhapExcel.setFocusable(false);
		btnNhapExcel.setBackground(Color.WHITE);
		pnlNhapExcel.add(btnNhapExcel);
		
		JPanel pnlXuatExcel = new JPanel();
		pnlExcel.add(pnlXuatExcel, BorderLayout.CENTER);
		pnlXuatExcel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnXuatExcel = new JButton("Xuất excel");
		btnXuatExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXuatExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
		btnXuatExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXuatExcel.setFocusable(false);
		btnXuatExcel.setBackground(Color.WHITE);
		pnlXuatExcel.add(btnXuatExcel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setPreferredSize(new Dimension(0, 5));
		pnlExcel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setPreferredSize(new Dimension(0, 5));
		pnlExcel.add(lblNewLabel_1_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_3 = new JLabel("");
		pnlTop.add(lblNewLabel_3, BorderLayout.WEST);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 255));
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		Object data[][] = {{"1", "Lữ Quang Minh", "Nam", "Admin", "0931814480", "Hoạt động"}};
		String column[] = {"ID", "Họ và tên", "Giới tính", "Chức vụ", "Số điện thoại", "Trạng thái"};
		
		table = new JTable(data,column);
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "L\u1EEF Quang Minh", "Nam", "Admin", "0931814480", "Ho\u1EA1t \u0111\u1ED9ng"},
			},
			new String[] {
				"Mã NV", "H\u1ECD v\u00E0 t\u00EAn", "Gi\u1EDBi t\u00EDnh", "Ch\u1EE9c v\u1EE5", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
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
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(null);
		pnlRight.setPreferredSize(new Dimension(200, 10));
		pnlRight.setBackground(new Color(255, 255, 255));
		add(pnlRight, BorderLayout.EAST);
		pnlRight.setLayout(new BorderLayout(0, 10));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		pnlRight.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin nhân viên");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_4);
		
		JPanel panel_3 = new JPanel();
		pnlRight.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6 = new JLabel("Họ và tên");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		panel_5.add(textField_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Giới tính");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.WHITE);
		panel_5.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbtnNam);
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNam.setFocusable(false);
		rdbtnNam.setBackground(Color.WHITE);
		panel_4_1.add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbtnNu);
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNu.setFocusable(false);
		rdbtnNu.setBackground(Color.WHITE);
		panel_4_1.add(rdbtnNu);
		
		JLabel lblNewLabel_6_2 = new JLabel("Chức vụ");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		JComboBox cmbChucVu = new JComboBox();
		cmbChucVu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Admin"}));
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbChucVu.setFocusable(false);
		panel_5.add(cmbChucVu);
		
		JLabel lblNewLabel_6_3 = new JLabel("Số điện thoại");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		textField_2 = new JTextField();
		textField_2.setPreferredSize(new Dimension(100, 19));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		panel_5.add(textField_2);
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Trạng thái");
		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2_1);
		
		JComboBox cmbTrangThai = new JComboBox();
		cmbTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setFocusable(false);
		panel_5.add(cmbTrangThai);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_6, BorderLayout.WEST);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_6_1, BorderLayout.EAST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setPreferredSize(new Dimension(10, 160));
		panel_3.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.setBackground(new Color(255, 255, 255));
		panel_7.add(pnlBtns);
		pnlBtns.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnChiTietQuyen = new JButton("Chi tiết quyền");
		btnChiTietQuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiTietQuyenGUI chiTietQuyenGUI = new ChiTietQuyenGUI();
				chiTietQuyenGUI.setVisible(true);
				chiTietQuyenGUI.requestFocus();
			}
		});
		btnChiTietQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChiTietQuyen.setIcon(new ImageIcon(absolutePath + "/src/images/icons/information.png"));
		btnChiTietQuyen.setPreferredSize(new Dimension(0, 30));
		btnChiTietQuyen.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTietQuyen.setFocusable(false);
		btnChiTietQuyen.setBackground(Color.WHITE);
		pnlBtns.add(btnChiTietQuyen);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFocusable(false);
		btnThem.setPreferredSize(new Dimension(0, 30));
		btnThem.setIcon(new ImageIcon(absolutePath + "/src/images/icons/add.png"));
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBackground(new Color(255, 255, 255));
		pnlBtns.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setFocusable(false);
		btnSua.setPreferredSize(new Dimension(0, 30));
		btnSua.setIcon(new ImageIcon(absolutePath + "/src/images/icons/edit.png"));
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(new Color(255, 255, 255));
		pnlBtns.add(btnSua);
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setFocusable(false);
		btnXoa.setPreferredSize(new Dimension(0, 30));
		btnXoa.setIcon(new ImageIcon(absolutePath + "/src/images/icons/delete.png"));
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(new Color(255, 255, 255));
		pnlBtns.add(btnXoa);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_7.add(panel_8, BorderLayout.WEST);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_7.add(panel_9, BorderLayout.EAST);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		panel_7.add(panel_10, BorderLayout.SOUTH);
		
	}

}
