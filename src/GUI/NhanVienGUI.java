package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;

public class NhanVienGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaNhanVien;
	private JTextField txtHoVaTen;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtChucVu;
	private JTextField txtSoDienThoai;
	private JTable table;
	private JTextField txtTimKiem;

	/**
	 * Create the panel.
	 */
	public NhanVienGUI() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		panel.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel pnlThongTinNhanVien = new JPanel();
		tabbedPane.addTab("Nhân viên", null, pnlThongTinNhanVien, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		pnlThongTinNhanVien.setLayout(new BorderLayout(0, 20));
		
		JPanel pnlHeader = new JPanel();
		pnlThongTinNhanVien.add(pnlHeader, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		pnlHeader.add(lblNewLabel);
		
		JPanel pnlContent = new JPanel();
		pnlThongTinNhanVien.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(10, 5));
		
		JPanel pnlContent_Title = new JPanel();
		pnlContent_Title.setBackground(new Color(55, 55, 55));
		pnlContent.add(pnlContent_Title, BorderLayout.NORTH);
		pnlContent_Title.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin nhân viên");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		pnlContent_Title.add(lblNewLabel_1);
		
		JPanel pnlContent_Info = new JPanel();
		pnlContent.add(pnlContent_Info, BorderLayout.CENTER);
		pnlContent_Info.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setLabelFor(txtMaNhanVien);
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlContent_Info.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Họ và tên");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2_1);
		
		txtHoVaTen = new JTextField();
		txtHoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHoVaTen.setColumns(10);
		pnlContent_Info.add(txtHoVaTen);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Giới tính");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2_1_1);
		
		JPanel pnlGioiTinh = new JPanel();
		pnlContent_Info.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGioiTinh.add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGioiTinh.add(rdbtnNu);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Chức vụ");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2_1_2);
		
		txtChucVu = new JTextField();
		txtChucVu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtChucVu.setColumns(10);
		pnlContent_Info.add(txtChucVu);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Số điện thoại");
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2_1_3);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoDienThoai.setColumns(10);
		pnlContent_Info.add(txtSoDienThoai);
		
		JLabel lblNewLabel_2_1_3_1 = new JLabel("Trạng thái");
		lblNewLabel_2_1_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlContent_Info.add(lblNewLabel_2_1_3_1);
		
		JComboBox cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
		pnlContent_Info.add(cmbTrangThai);
		
		JPanel pnlContent_Buttons = new JPanel();
		pnlContent.add(pnlContent_Buttons, BorderLayout.EAST);
		pnlContent_Buttons.setLayout(new GridLayout(0, 1, 0, 10));
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThem.setIcon(new ImageIcon("C:\\Users\\lumin\\OneDrive\\Máy tính\\LƯU TRỮ TÀI LIỆU HK2 23-24\\JAVA\\SWING\\Java_ShoesStore\\images\\add-icon.png"));
		pnlContent_Buttons.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSua.setIcon(new ImageIcon("C:\\Users\\lumin\\OneDrive\\Máy tính\\LƯU TRỮ TÀI LIỆU HK2 23-24\\JAVA\\SWING\\Java_ShoesStore\\images\\Pencil-icon.png"));
		pnlContent_Buttons.add(btnSua);
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoa.setIcon(new ImageIcon("C:\\Users\\lumin\\OneDrive\\Máy tính\\LƯU TRỮ TÀI LIỆU HK2 23-24\\JAVA\\SWING\\Java_ShoesStore\\images\\delete-icon.png"));
		btnXoa.setSelectedIcon(new ImageIcon("C:\\Users\\lumin\\OneDrive\\Máy tính\\LƯU TRỮ TÀI LIỆU HK2 23-24\\JAVA\\SWING\\Java_ShoesStore\\images\\delete-icon.png"));
		pnlContent_Buttons.add(btnXoa);
		
		JPanel pnlTable = new JPanel();
		pnlThongTinNhanVien.add(pnlTable, BorderLayout.SOUTH);
		
		Object data[][] = {{"1", "Lữ Quang Minh", "Nam", "Admin", "0931814480", "Hoạt động"}};
		String column[] = {"ID", "Họ và tên", "Giới tính", "Chức vụ", "Số điện thoại", "Trạng thái"};
		pnlTable.setLayout(new BorderLayout(0, 10));
		
		JPanel pnlTable_Top = new JPanel();
		pnlTable.add(pnlTable_Top, BorderLayout.NORTH);
		pnlTable_Top.setLayout(new BorderLayout(0, 5));
		
		JPanel pnlTable_TopSearch = new JPanel();
		pnlTable_Top.add(pnlTable_TopSearch);
		pnlTable_TopSearch.setLayout(new BorderLayout(10, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Tìm kiếm:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlTable_TopSearch.add(lblNewLabel_3, BorderLayout.WEST);
		
		txtTimKiem = new JTextField();
		pnlTable_TopSearch.add(txtTimKiem, BorderLayout.CENTER);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm");
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\lumin\\OneDrive\\Máy tính\\LƯU TRỮ TÀI LIỆU HK2 23-24\\JAVA\\SWING\\Java_ShoesStore\\images\\Search-icon.png"));
		pnlTable_TopSearch.add(btnTimKiem, BorderLayout.EAST);
		
		JPanel pnlTable_TopTitle = new JPanel();
		pnlTable_TopTitle.setBackground(new Color(55, 55, 55));
		pnlTable_Top.add(pnlTable_TopTitle, BorderLayout.NORTH);
		pnlTable_TopTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("Thông tin danh sách");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		pnlTable_TopTitle.add(lblNewLabel_1_1);
		
		JTable table = new JTable(data, column);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "L\u1EEF Quang Minh", "Nam", "Admin", "0931814480", "Ho\u1EA1t \u0111\u1ED9ng"},
			},
			new String[] {
				"ID", "H\u1ECD v\u00E0 t\u00EAn", "Gi\u1EDBi t\u00EDnh", "Ch\u1EE9c v\u1EE5", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		pnlTable.add(scrollPane, BorderLayout.CENTER);
		pnlTable.setVisible(getFocusTraversalKeysEnabled());
		
		JPanel pblPhanQuyen = new JPanel();
		tabbedPane.addTab("Phân quyền", null, pblPhanQuyen, null);
		pblPhanQuyen.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPhanQuyen_Header = new JPanel();
		pblPhanQuyen.add(pnlPhanQuyen_Header, BorderLayout.NORTH);
		pnlPhanQuyen_Header.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblQunLPhn = new JLabel("QUẢN LÝ PHÂN QUYỀN");
		lblQunLPhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhn.setForeground(Color.BLACK);
		lblQunLPhn.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlPhanQuyen_Header.add(lblQunLPhn);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("Quản lý sản phẩm");
		pnlPhanQuyen_Header.add(chckbxNewCheckBox_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1 = new JCheckBox("Quản lý khách hàng");
		pnlPhanQuyen_Header.add(chckbxNewCheckBox_3_1);
		
		JCheckBox chckbxNewCheckBox_2_1 = new JCheckBox("Quản lý nhân viên");
		pnlPhanQuyen_Header.add(chckbxNewCheckBox_2_1);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Quản lý nhập hàng");
		pnlPhanQuyen_Header.add(chckbxNewCheckBox_4);
		
		JButton btnNewButton = new JButton("Lưu");
		pnlPhanQuyen_Header.add(btnNewButton);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));

	}
}
