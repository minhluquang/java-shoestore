package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCap;

public class ChiTietNhaCungCapGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaNCC;
	private JTextField txtDiaChi;
	private JTextField txtTenNcc;
	private NhaCungCapGUI parent;
	private NhaCungCap ncc;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietNhaCungCapGUI frame = new ChiTietNhaCungCapGUI(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void close() {
		int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng không?", "Xác nhận đóng",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	public ChiTietNhaCungCapGUI(NhaCungCap nc, NhaCungCapGUI ncc) {
		this.parent = ncc;
		this.ncc = nc;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 457, 505);
		setLocationRelativeTo(null);
		setTitle("Thông tin nhà cung cấp");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(200, 10));
		pnlRight.setBorder(null);
		pnlRight.setBackground(Color.WHITE);
		contentPane.add(pnlRight);
		pnlRight.setLayout(new BorderLayout(0, 10));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pnlRight.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("Thêm nhà cung cấp");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.setBackground(new Color(36, 136, 203));
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5_1 = new JLabel("");
		panel.add(lblNewLabel_5_1);

		JPanel panel_3 = new JPanel();
		pnlRight.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));

		JLabel lblNewLabel_6 = new JLabel("Mã Nhà Cung Cấp");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);

		txtMaNCC = new JTextField();
		txtMaNCC.setEditable(false);
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNCC.setColumns(10);
		txtMaNCC.setText(NhaCungCapBUS.generrate_Id() + "");

		panel_5.add(txtMaNCC);

		JLabel lblNewLabel_6_1 = new JLabel("Tên Nhà Cung Cấp");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_1);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.WHITE);
		panel_5.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 1, 0, 0));

		txtTenNcc = new JTextField();
		txtTenNcc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNcc.setColumns(10);
		panel_4_1.add(txtTenNcc);

		JLabel lblNewLabel_6_3 = new JLabel("Địa chỉ");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);

		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(100, 19));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDiaChi.setColumns(10);
		panel_5.add(txtDiaChi);

		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_1);

		btnNewButton = new JButton("Thêm");
		btnNewButton.setFocusable(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(ChiTietNhaCungCapGUI.class.getResource("/images/icons/add.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.setLayout(new GridLayout(0, 2, 20, 0));
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Hủy");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(ChiTietNhaCungCapGUI.class.getResource("/images/icons/delete.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_3.add(panel_6, BorderLayout.EAST);

		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(Color.WHITE);
		panel_3.add(panel_6_1, BorderLayout.WEST);
		if (this.ncc.getSupplier_id() != 0) {
			int oldId = this.ncc.getSupplier_id();
			String oldName = this.ncc.getSupplier_name();
			String oldAddress = this.ncc.getSupplier_addresss();
			txtMaNCC.setText(String.valueOf(oldId));
			txtTenNcc.setText(oldName);
			txtDiaChi.setText(oldAddress);
		}

		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			if (ncc.getSupplier_id() == 0) {
				txtMaNCC.setText("" + NhaCungCapBUS.generrate_Id());
				String ten = txtTenNcc.getText();
				String diachi = txtDiaChi.getText();
				if (ten.isEmpty() || diachi.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean success = NhaCungCapBUS.insertPublisher(ten, diachi);
				if (!success) {
					JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công !", "Thông báo",
							JOptionPane.OK_CANCEL_OPTION);
					parent.loadDanhSachNhaCungCap();
					txtTenNcc.setText("");
					txtDiaChi.setText("");
					txtMaNCC.setText("" + NhaCungCapBUS.generrate_Id());
				}
			} else if (ncc.getSupplier_id() != 0 && ncc != null) {
				boolean success = NhaCungCapBUS.updatePublisher(Integer.parseInt(txtMaNCC.getText()),
						txtTenNcc.getText(), txtDiaChi.getText());
				System.out.println(success);
				if (!success) {
					JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp thất bại !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp thành công !", "Thông báo",
							JOptionPane.OK_CANCEL_OPTION);
					parent.loadDanhSachNhaCungCap();
				}
			}

		}

	}
}