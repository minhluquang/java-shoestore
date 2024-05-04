package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.ChiTietPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuNhap;
import DTO.PhieuNhap;

public class ChiTietPhieuNhapGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	JLabel lblMa;
	JLabel lblNhanVien;
	JLabel lblNCC;
	JLabel lblTime;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//					ChiTietPhieuNhapGUI frame = new ChiTietPhieuNhapGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */

	public ChiTietPhieuNhapGUI(int id) {
		setBackground(new Color(242, 242, 242));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng cửa sổ không?",
						"Xác nhận đóng cửa sổ", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 898, 660);
		setLocationRelativeTo(null);
		setTitle("Chi tiết quyền nhân viên");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));

		JPanel pnlTop = new JPanel();
		contentPane.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(36, 136, 203));
		pnlTop.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin Phiếu Nhập");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(new Color(36, 136, 203));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTop.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(36, 136, 203));
		lblNewLabel_2.setOpaque(true);
		pnlTop.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(242, 242, 242));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 20));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 218, 218, 218, 218 };
		gbl_panel_1.rowHeights = new int[] { 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0 };
		panel_1.setLayout(gbl_panel_1);

		lblMa = new JLabel("");
		lblMa.setBackground(Color.WHITE);
		lblMa.setBorder(
				new TitledBorder(null, "M\u00E3 phi\u1EBFu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lblMa = new GridBagConstraints();
		gbc_lblMa.fill = GridBagConstraints.BOTH;
		gbc_lblMa.insets = new Insets(0, 0, 5, 5);
		gbc_lblMa.gridx = 0;
		gbc_lblMa.gridy = 0;
		panel_1.add(lblMa, gbc_lblMa);

		lblNhanVien = new JLabel("");
		lblNhanVien.setBorder(
				new TitledBorder(null, "Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lblNhanVien = new GridBagConstraints();
		gbc_lblNhanVien.fill = GridBagConstraints.BOTH;
		gbc_lblNhanVien.insets = new Insets(0, 0, 5, 5);
		gbc_lblNhanVien.gridx = 1;
		gbc_lblNhanVien.gridy = 0;
		panel_1.add(lblNhanVien, gbc_lblNhanVien);

		lblNCC = new JLabel("");
		lblNCC.setBorder(
				new TitledBorder(null, "Nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lblNCC = new GridBagConstraints();
		gbc_lblNCC.fill = GridBagConstraints.BOTH;
		gbc_lblNCC.insets = new Insets(0, 0, 5, 5);
		gbc_lblNCC.gridx = 2;
		gbc_lblNCC.gridy = 0;
		panel_1.add(lblNCC, gbc_lblNCC);

		lblTime = new JLabel("");
		lblTime.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u1EDDi gian", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblTime.fill = GridBagConstraints.BOTH;
		gbc_lblTime.gridx = 3;
		gbc_lblTime.gridy = 0;
		panel_1.add(lblTime, gbc_lblTime);

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBorder(null);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);

		defaultTableModel = new DefaultTableModel(
				new Object[] { "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá" }, 0);
		table.setModel(defaultTableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		panel.add(scrollPane, BorderLayout.CENTER);

		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(36, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		loadChiTietPhieuNhap(id);
	}

	public void loadChiTietPhieuNhap(int id) {
		defaultTableModel.setRowCount(0);
		ArrayList<ChiTietPhieuNhap> dsct = ChiTietPhieuNhapBUS.getDanhSachChiTietPhieuNhap(id);
		int rowNum = defaultTableModel.getRowCount();
		for (ChiTietPhieuNhap ct : dsct) {
			int stt = ++rowNum;
			String tenSanPham = SanPhamBUS.getTenSanPhamById(ct.getProduct_id());
			Object[] row = { stt, ct.getProduct_id(), tenSanPham, ct.getQuantity(), ct.getInput_price() };
			defaultTableModel.addRow(row);
		}
		loadThongTinPhieuNhap(id);
	}

	public void loadThongTinPhieuNhap(int id) {
		PhieuNhap pn = PhieuNhapBUS.getPhieuNhapById(id);
		lblMa.setText("" + pn.getReceipt_id());
		lblNCC.setText(NhaCungCapBUS.getTenNhaCungCapById(pn.getSupplier_id()));
		lblNhanVien.setText(PhieuNhapBUS.getTenNhanVienById(pn.getStaff_id()));
		lblTime.setText(pn.getDate());
	}

	public void exportExcel(int id) throws IOException {
		ArrayList<ChiTietPhieuNhap> dsnv = ChiTietPhieuNhapBUS.getDanhSachChiTietPhieuNhap(id);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("excel/ctpn_" + String.valueOf(id) + ".xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Danh sách sản phẩm");

			// Ghi header
			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("receipt_id");
			headerRow.createCell(1).setCellValue("product_id");
			headerRow.createCell(2).setCellValue("product_name");
			headerRow.createCell(3).setCellValue("quantity");
			headerRow.createCell(4).setCellValue("input_price");

			// Ghi thông tin nhân viên
			int rowNum = 1;
			for (ChiTietPhieuNhap nv : dsnv) {
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(nv.getReceipt_id());
				row.createCell(1).setCellValue(nv.getProduct_id());
				row.createCell(2).setCellValue(SanPhamBUS.getTenSanPhamById(nv.getProduct_id()));
				row.createCell(3).setCellValue(nv.getQuantity());
				row.createCell(4).setCellValue(nv.getInput_price());
			}

			wb.write(fileOutputStream);
			wb.close();
			JOptionPane.showMessageDialog(null, "Đã export dữ liệu ra file excel thành công!", "Thông báo thành công",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Export dữ liệu ra file excel thất bại!", "Thông báo thất bại",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
