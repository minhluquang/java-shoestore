package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import BUS.ChiTietPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.TheLoaiBUS;
import DTO.ChiTietPhieuNhap;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.SanPhamDTO;

public class NhapHangGUI extends JPanel {
	public String absolutePath = new File("").getAbsolutePath();
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private DefaultTableModel defaultTableModel;
	private JTable table;
	private JTextField txtSoLuong;
	private DefaultTableModel defaultTableModelCT;
	private JTable tableCT;
	private JLabel txtGiaNhap;
	private SanPhamDTO sp = new SanPhamDTO();
	private JLabel lblLoai;
	private JLabel lblTenSp;
	private JLabel lblMa;
	private HashMap<String, String> nccMap = new HashMap<>();
	private JLabel lblTongTien;
	private JLabel lblMaPN;
	private NhanVien staff = NhanVienBUS.getNhanVienByAccountID(MyApp.user.getAccountId());
	private JComboBox cboNCC;

	/**
	 * Create the panel.
	 */
	public NhapHangGUI() {

		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel pnlTop = new JPanel();
		panel.add(pnlTop, BorderLayout.NORTH);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String enteredText = textField.getText();
					ArrayList<SanPhamDTO> arr = PhieuNhapBUS.searchObjectById(enteredText);
					loadDanhSachSanPham(arr);
				}
			}
		});
		textField.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlTop.add(textField);
		textField.setColumns(12);

		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboNCC.setModel(new DefaultComboBoxModel());
				ArrayList<NhaCungCap> n = NhaCungCapBUS.getDanhSachNhaCungCap();
				for (NhaCungCap a : n) {
					if (a.getStatus() == 0)
						continue;
					cboNCC.addItem(a.getSupplier_name());
					nccMap.put(a.getSupplier_name(), "" + a.getSupplier_id());
				}
				textField.setText("");
				loadDanhSachSanPham();
			}
		});
		btnNewButton.setPreferredSize(new Dimension(130, 30));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
		pnlTop.add(btnNewButton);

		JPanel pnlBot = new JPanel();
		pnlBot.setPreferredSize(new Dimension(0, 200));
		panel.add(pnlBot, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlBot = new GridBagLayout();
		gbl_pnlBot.columnWidths = new int[] { 0, 0 };
//		gbl_pnlBot.rowHeights = new int[] { 0 };
		gbl_pnlBot.rowHeights = new int[] { 0, 0 };
		gbl_pnlBot.rowWeights = new double[] { 1.0 };

		pnlBot.setLayout(gbl_pnlBot);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(150, 10));
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(5, 0, 0, 5);
//		gbc_panel_2.gridheight = 2;
		gbc_panel_2.gridheight = 1;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.weightx = 0.5;
		gbc_panel_2.gridy = 0;
		pnlBot.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(300, 100));
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.weightx = 0.5;
		gbc_panel_3.insets = new Insets(5, 11, 0, 5);
//		gbc_panel_3.gridheight = 2;
		gbc_panel_3.gridheight = 1;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		pnlBot.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String giaNhap = txtGiaNhap.getText();
				String soL = txtSoLuong.getText();
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (giaNhap.isEmpty() || soL.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (!giaNhap.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Giá nhập phải là số !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (!soL.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Số lượng phải là số !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1)
					return;

				Object[] values = new Object[5];
				values[0] = table.getValueAt(selectedRow, 0);
				values[1] = table.getValueAt(selectedRow, 2);
				values[2] = txtSoLuong.getText();
				values[3] = txtGiaNhap.getText();
				values[4] = String.format("%.0f", PhieuNhapBUS.getThanhTien(Integer.parseInt((String) values[2]),
						Integer.parseInt((String) values[3])));

				defaultTableModelCT.addRow(values);
				table.clearSelection();
				lblLoai.setText("");
				lblMa.setText("");
				lblTenSp.setText("");
				txtGiaNhap.setText("");
				txtSoLuong.setText("");
				lblTongTien.setText(PhieuNhapBUS.renderTongSoTien(tableCT));
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThem.setPreferredSize(new Dimension(85, 35));
		btnThem.setIcon(new ImageIcon(absolutePath + "/src/images/icons/add.png"));
		GridBagConstraints gbc_btnThem = new GridBagConstraints();
		gbc_btnThem.fill = GridBagConstraints.BOTH;
		gbc_btnThem.insets = new Insets(5, 5, 5, 5);
		gbc_btnThem.gridwidth = 2;
		gbc_btnThem.gridx = 0;
		gbc_btnThem.gridy = 1;
		pnlBot.add(btnThem, gbc_btnThem);

		lblMa = new JLabel("");
		lblMa.setEnabled(false);
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMa.setBorder(new TitledBorder(null, "M\u00E3 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		lblMa.setPreferredSize(new Dimension(100, 35));
		panel_3.add(lblMa);

		lblLoai = new JLabel("");
		lblLoai.setEnabled(false);
		lblLoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoai.setPreferredSize(new Dimension(120, 35));
		lblLoai.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(lblLoai);

		lblTenSp = new JLabel("");
		lblTenSp.setEnabled(false);
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenSp.setPreferredSize(new Dimension(120, 35));
		lblTenSp.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00EAn s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(lblTenSp);

		txtGiaNhap = new JLabel();
		txtGiaNhap.setEnabled(false);
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiaNhap.setPreferredSize(new Dimension(120, 35));
		txtGiaNhap.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Gi\u00E1 nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(txtGiaNhap);

		txtSoLuong = new JTextField();
		txtSoLuong.setBorder(
				new TitledBorder(null, "S\u1ED1 l\u01B0\u1EE3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setPreferredSize(new Dimension(100, 40));
		panel_3.add(txtSoLuong);
		txtSoLuong.setColumns(8);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(30, 10));
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6, BorderLayout.WEST);

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(30, 10));
		panel_4.add(panel_7, BorderLayout.EAST);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(4, 1, 0, 2));

		lblMaPN = new JLabel("");
		lblMaPN.setPreferredSize(new Dimension(100, 35));
		lblMaPN.setBorder(new TitledBorder(null, "M\u00E3 phi\u1EBFu nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		lblMaPN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaPN.setText(PhieuNhapBUS.generate_Id() + "");
		panel_8.add(lblMaPN);

		lblTongTien = new JLabel("0.0");
		lblTongTien.setBorder(new TitledBorder(null, "T\u1ED5ng ti\u1EC1n(VN\u0110)", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblTongTien);

		cboNCC = new JComboBox();
		cboNCC.setBorder(
				new TitledBorder(null, "Nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cboNCC.setModel(new DefaultComboBoxModel());
		cboNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboNCC.setPreferredSize(new Dimension(29, 20));
		ArrayList<NhaCungCap> n = NhaCungCapBUS.getDanhSachNhaCungCap();
		for (NhaCungCap a : n) {
			if (a.getStatus() == 0)
				continue;
			cboNCC.addItem(a.getSupplier_name());
			nccMap.put(a.getSupplier_name(), "" + a.getSupplier_id());
		}
		panel_8.add(cboNCC);

		JLabel lblNV = new JLabel(staff.getFull_name());
		lblNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNV.setBorder(
				new TitledBorder(null, "Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.add(lblNV);

		JPanel panel_10 = new JPanel();
		panel_4.add(panel_10, BorderLayout.SOUTH);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.SOUTH);
		panel_5.setPreferredSize(new Dimension(0, 150));
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_9.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(10);
		panel_5.add(panel_9, BorderLayout.NORTH);
		panel_9.setPreferredSize(new Dimension(0, 75));

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaDong();
			}
		});
		btnXoa.setPreferredSize(new Dimension(100, 40));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setIcon(new ImageIcon(absolutePath + "/src/images/icons/delete.png"));
		panel_9.add(btnXoa);

		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(absolutePath + "/src/images/icons/edit.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableCT.getSelectedRow();
				if (tableCT.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không có sản phẩm nào !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (rowSelected < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int index = findRowByValue(table, 0, tableCT.getValueAt(rowSelected, 0));
				table.setRowSelectionInterval(index, index);
				int selectedRow = table.getSelectedRow();
				lblMa.setText(String.valueOf(table.getValueAt(selectedRow, 0)));
				lblLoai.setText((String) table.getValueAt(selectedRow, 1));
				lblTenSp.setText((String) table.getValueAt(selectedRow, 2));
				txtSoLuong.setText((String) tableCT.getValueAt(rowSelected, 2));
				txtGiaNhap.setText((String) tableCT.getValueAt(rowSelected, 3));
				xoaDong();
			}
		});
		btnSua.setPreferredSize(new Dimension(100, 40));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(btnSua);
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 0, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panel_11.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		flowLayout_2.setVgap(20);
		panel_5.add(panel_11, BorderLayout.CENTER);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableCT.getModel();
				model.setRowCount(0);
			}
		});
		btnHuy.setPreferredSize(new Dimension(100, 40));
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(btnHuy);

		JButton btnNhaphang = new JButton("Nhập Hàng");
		btnNhaphang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCT.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không có sản phẩm nào !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String formattedDate = currentDate.format(formatter);
				System.out.println(formattedDate);
				int receipt_id = PhieuNhapBUS.generate_Id();
				int idNCC = Integer.parseInt(nccMap.get(cboNCC.getSelectedItem()));
//		============ TẠO PHIẾU NHẬP ===============
				boolean success_GoodsReceipt = PhieuNhapBUS.create_GoodsReceipts(receipt_id, formattedDate, idNCC,
						staff.getStaffId());
				if (!success_GoodsReceipt) {
					JOptionPane.showMessageDialog(null, "Tạo phiếu nhập thất bại !", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
//		============ TẠO CÁC CHI TIẾT ===============		
				int rowNum = tableCT.getRowCount();
				int colNum = tableCT.getColumnCount();
				for (int dong = 0; dong < rowNum; dong++) {

					int product_id = Integer.parseInt(String.valueOf(tableCT.getValueAt(dong, 0)));
					int quantity = Integer.parseInt(String.valueOf(tableCT.getValueAt(dong, 2)));
					int input_price = Integer.parseInt(String.valueOf(tableCT.getValueAt(dong, 3)));

					boolean success = ChiTietPhieuNhapBUS.create_GoodsReceipts_Details(product_id, quantity, receipt_id,
							input_price);

					if (!success) {
						JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập thất bại !", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

//					============ TẠO SERI TRONG BẢNG PRODUCT_DETAIL ===============
					for (int i = 0; i < quantity; i++) {
						boolean success_CT = ChiTietPhieuNhapBUS.insertProductDetail(product_id);
						if (!success_CT) {
							JOptionPane.showMessageDialog(null, "Tạo sản phẩm trong product_detail thất bại !",
									"Thông báo", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				if (PhieuNhapBUS.update_Total_Price(receipt_id)) {
					JOptionPane.showMessageDialog(null, "Thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}

				loadDanhSachSanPham();
				reset();
				try {
					xuatPdf(receipt_id);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNhaphang.setPreferredSize(new Dimension(140, 40));
		btnNhaphang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(btnNhaphang);
//		=========== Table Chi Tiết ==============
		tableCT = new JTable();
		tableCT.setFocusable(false);
		tableCT.setSelectionForeground(Color.WHITE);
		tableCT.setBorder(null);
		tableCT.setSelectionBackground(new Color(232, 57, 95));
		tableCT.setIntercellSpacing(new Dimension(0, 0));

		defaultTableModelCT = new DefaultTableModel(
				new Object[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành Tiền" }, 0);
		defaultTableModelCT.setRowCount(0);
		tableCT.setModel(defaultTableModelCT);
		tableCT.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tableCT.getTableHeader().setOpaque(false);
		tableCT.getTableHeader().setBackground(new Color(36, 136, 203));
		tableCT.getTableHeader().setForeground(new Color(255, 255, 255));
		tableCT.setRowHeight(25);

		JScrollPane scrollPaneCT = new JScrollPane(tableCT);
		scrollPaneCT.setBorder(null);
		scrollPaneCT.setBackground(Color.WHITE);
		scrollPaneCT.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCT.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scrollPaneCT);

//		===========Table Sản Phẩm==============
		table = new JTable();
		table.setFocusable(false);
		table.setSelectionForeground(Color.WHITE);
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));

		defaultTableModel = new DefaultTableModel(
				new Object[] { "Mã sản phẩm", "Loại", "Tên sản phẩm", "Xuất xứ", "Tồn kho" }, 0);

		table.setModel(defaultTableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, BorderLayout.CENTER);

		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		Object[] row = { "1", "Chạy bộ", "Nike", "10000", "15" };
		defaultTableModel.addRow(row);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if (selectedRow != -1) {
					lblMa.setText(String.valueOf(table.getValueAt(selectedRow, 0)));
					lblLoai.setText((String) table.getValueAt(selectedRow, 1));
					lblTenSp.setText((String) table.getValueAt(selectedRow, 2));
					String giaBan = PhieuNhapBUS.getInPutPriceByProductId(lblMa.getText());
					String giaNhap = PhieuNhapBUS.tinhGiaNhap(Integer.parseInt(giaBan));
					txtGiaNhap.setText(giaNhap);
					txtSoLuong.setText("");
				}
			}
		});

//		Goi Ham
		loadDanhSachSanPham();

	}

	public static void main(String[] args) {
		NhapHangGUI a = new NhapHangGUI();
		JFrame f = new JFrame();
		f.getContentPane().add(a);
		f.setVisible(true);
	}

	public void loadDanhSachSanPham() {
		ArrayList<SanPhamDTO> arr = SanPhamBUS.getDanhSachSanPham();
		defaultTableModel.setRowCount(0);
		for (SanPhamDTO s : arr) {
			String tenTheLoai = TheLoaiBUS.getTheLoaiByID(s.getCategory_id()).getCategory_name();
			Object[] row = { s.getProduct_id(), tenTheLoai, s.getProduct_name(), s.getCountry(), s.getQuantity() };
			defaultTableModel.addRow(row);
		}
	}

	public void loadDanhSachSanPham(ArrayList<SanPhamDTO> arr) {
		defaultTableModel.setRowCount(0);
		for (SanPhamDTO s : arr) {
			String tenTheLoai = TheLoaiBUS.getTheLoaiByID(s.getCategory_id()).getCategory_name();
			Object[] row = { s.getProduct_id(), tenTheLoai, s.getProduct_name(), s.getCountry(), s.getQuantity() };
			defaultTableModel.addRow(row);
		}
	}

	public int findRowByValue(JTable table, int columnIndex, Object key) {
		for (int i = 0; i < table.getRowCount(); i++) {
			Object value = table.getValueAt(i, columnIndex);
			if (value != null && value.equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public void xoaDong() {
		int rowSelected = tableCT.getSelectedRow();
		if (tableCT.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Không có sản phẩm nào !", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (rowSelected < 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa !", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultTableModel model = (DefaultTableModel) tableCT.getModel();
		model.removeRow(rowSelected);
		if (tableCT.getRowCount() == 0) {
			lblTongTien.setText("0.0");
		}
	}

	public void reset() {
		lblMaPN.setText(PhieuNhapBUS.generate_Id() + "");
		lblTongTien.setText("0.0");
		defaultTableModelCT.setRowCount(0);
	}

	public static void xuatPdf(int receipt_id) throws FileNotFoundException {
		String path = "pdf/goodsreceipt_" + receipt_id + ".pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);
		PhieuNhap pn = PhieuNhapBUS.getPhieuNhapById(receipt_id);
		String dateString = pn.getDate();

		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

		SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = "";
		try {
			Date date = originalFormat.parse(dateString);
			formattedDate = newFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Paragraph storeTitle = new Paragraph("Cua hang ban giay Shopgiay88").setBold().setFontSize(16);
		storeTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph storeAddress = new Paragraph("DC: 273 An Duong Vuong, P.3, Q.5, Tp. HCM");
		storeAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
		Paragraph phoneNumberAddress = new Paragraph("SDT: 028.392.44.690");
		phoneNumberAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

		Paragraph invoiceTitle = new Paragraph("HOA DON NHAP HANG").setBold().setFontSize(16).setMarginTop(20);
		invoiceTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

		document.add(storeTitle);
		document.add(storeAddress);
		document.add(phoneNumberAddress);
		document.add(invoiceTitle);

		// bill info
		float twocol = 285f;
		float twocol150 = twocol + 150f;
		float twocolumnWidth[] = { twocol150, twocol };

		Table table = new Table(twocolumnWidth);
		table.setMarginTop(20);
		table.addCell(new Cell().add(new Paragraph("Ngay tao: " + formattedDate)).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(new Paragraph("Ma so: " + receipt_id).setBorder(Border.NO_BORDER)));
		table.addCell(new Cell()
				.add(new Paragraph(
						"Nhan vien: " + NhanVienBUS.getNhanVienByAccountID(MyApp.user.getAccountId()).getFull_name()))
				.setBorder(Border.NO_BORDER));

		document.add(table);

		// bill detail
		float threecol = 190f;
		float threeColumnWidth[] = { threecol, threecol, threecol };
		Table tableBillDetail = new Table(threeColumnWidth);
		tableBillDetail.setMarginTop(20);

		// header
		Cell cellHeader1 = new Cell().add(new Paragraph("Ten san pham")).setBorder(new SolidBorder(1));
		Cell cellHeader2 = new Cell().add(new Paragraph("So luong")).setBorder(new SolidBorder(1));
		Cell cellHeader3 = new Cell().add(new Paragraph("Don gia")).setBorder(new SolidBorder(1));

		tableBillDetail.addCell(cellHeader1);
		tableBillDetail.addCell(cellHeader2);
		tableBillDetail.addCell(cellHeader3);

		// body
		// ROW 1
		ArrayList<ChiTietPhieuNhap> arr = ChiTietPhieuNhapBUS.getDanhSachChiTietPhieuNhap(receipt_id);
		for (ChiTietPhieuNhap p : arr) {
			Cell cellBody1 = new Cell();
			Cell cellBody2 = new Cell();
			Cell cellBody3 = new Cell();
			cellBody1.add(new Paragraph(SanPhamBUS.getTenSanPhamById(p.getProduct_id()))).setBorder(new SolidBorder(1));
			cellBody2.add(new Paragraph(p.getQuantity() + "")).setBorder(new SolidBorder(1));
			cellBody3.add(new Paragraph(p.getInput_price() + "")).setBorder(new SolidBorder(1));

			cellBody2.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
			cellBody3.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);

			tableBillDetail.addCell(cellBody1);
			tableBillDetail.addCell(cellBody2);
			tableBillDetail.addCell(cellBody3);
		}

		document.add(tableBillDetail);

		// Phan bottom
		Table tableBottom = new Table(twocolumnWidth);
		tableBottom.setMarginTop(20);

		tableBottom.addCell(new Cell(1, 2).add(new LineSeparator(new DottedLine())));

		tableBottom.addCell(new Cell().add(new Paragraph("TONG TIEN")).setBorder(Border.NO_BORDER));
		tableBottom.addCell(new Cell().add(new Paragraph(pn.getTotal_price() + "")).setBorder(Border.NO_BORDER));

		document.add(tableBottom);

		document.close();
	}
}
