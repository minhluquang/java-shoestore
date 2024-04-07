package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class NhapHangGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public NhapHangGUI() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
//		panel.setPreferredSize(new Dimension(1000, 1000));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		panel.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelKhoHang = new JPanel();
		tabbedPane.addTab("Kho Hàng", null, panelKhoHang, null);
		panelKhoHang.setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelKhoHang.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelTop.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setPreferredSize(new Dimension(80, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setPreferredSize(new Dimension(40, 20));
		panel_5.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tồn Kho");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelBot = new JPanel();
		panelKhoHang.add(panelBot, BorderLayout.CENTER);
		panelBot.setLayout(new BorderLayout(0, 0));

		Object data[][] = { { "123", "Nike Air Force 1", "2500" } };
		String column[] = { "M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "T\u1ED3n Kho" };

		table_1 = new JTable(data, column);

		table_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { "123", "Nike Air Force 1", "2500" }, { "124", "Adidas", "100" }, },
				new String[] { "M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "T\u1ED3n Kho" }));
		JScrollPane scrollPane = new JScrollPane(table_1);
		panelBot.add(scrollPane, BorderLayout.CENTER);

		JPanel panelNhapHang = new JPanel();
		tabbedPane.addTab("Nhập Hàng", null, panelNhapHang, null);
		panelNhapHang.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelNhapHang.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Nhập Hàng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 100));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelNhapHang.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Thông Tin Phiếu Nhập");
		lblNewLabel_3.setBounds(2, 2, 441, 20);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Mã SP");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(97, 32, 59, 20);
		panel_2.add(lblNewLabel_4);

		textField_1 = new JTextField();
		textField_1.setBounds(211, 35, 152, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("Tên Sản Phẩm");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(78, 71, 98, 20);
		panel_2.add(lblNewLabel_4_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(211, 74, 152, 20);
		panel_2.add(textField_2);

		JLabel lblNewLabel_4_1_1 = new JLabel("Số Lượng");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_1.setBounds(88, 101, 81, 20);
		panel_2.add(lblNewLabel_4_1_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(211, 104, 152, 20);
		panel_2.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(211, 134, 152, 20);
		panel_2.add(textField_4);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Giá Nhập");
		lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_1_1.setBounds(97, 131, 71, 20);
		panel_2.add(lblNewLabel_4_1_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Nhà Cung Cấp 1", "Nhà Cung Cấp 2", "Nhà Cung Cấp 3" }));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(213, 174, 150, 21);
		panel_2.add(comboBox);

		JLabel lblNewLabel_5 = new JLabel("Nhà Cung Cấp");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(89, 169, 98, 30);
		panel_2.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("Chọn Nhập");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(164, 209, 107, 32);
		panel_2.add(btnNewButton);
	}
}
